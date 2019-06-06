package apps.gliger.glg.cooker.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import apps.gliger.glg.cooker.data.FoodDatabase
import apps.gliger.glg.cooker.data.FoodDatabaseImpl
import apps.gliger.glg.cooker.model.Event
import apps.gliger.glg.cooker.model.PeopleResponse
import apps.gliger.glg.cooker.model.Person
import apps.gliger.glg.cooker.model.Result
import apps.gliger.glg.cooker.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryImpl(private val foodDatabase: FoodDatabase, private val networkService: NetworkService) : Repository {

    private val _networkStatus = MutableLiveData<Boolean>()
    private lateinit var _eventList: LiveData<List<Event>>
    private lateinit var _people: LiveData<Person>

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _eventList = foodDatabase.EventAccess().getAllEvents()
            _people = foodDatabase.PeopleAccess().getPerson()
        }
    }

    override fun getAllEvents(): LiveData<List<Event>> {
        return _eventList
    }

    override suspend fun addEvent(event: Event) {
        foodDatabase.EventAccess().addEvent(event)
    }

    override suspend fun removeEvent(event: Event) {
        foodDatabase.EventAccess().deleteEvent(event)
    }

    override suspend fun updateEvent(event: Event) {
        foodDatabase.EventAccess().updateEvent(event)
    }

    override fun getPerson(): LiveData<Person> {
        return _people
    }

    override fun getPeopleFromCloud() {
        var peopleResponse: PeopleResponse? = null
        CoroutineScope(Dispatchers.Main).launch {
            peopleResponse = networkService.getPeople().await()
        }

        CoroutineScope(Dispatchers.IO).launch {
            peopleResponse?.let {
                savePeopleData(it.results[0])
            }
        }
    }

    override suspend fun addPerson(person: Person) {
        foodDatabase.PeopleAccess().upsertPerson(person)
    }

    override fun networkStatus(): LiveData<Boolean> {
        return _networkStatus
    }


    override fun setNetworkStatus(isConnected: Boolean) {
        _networkStatus.value = isConnected
    }

    private suspend fun savePeopleData(result: Result): Person {
        val person = setUpPersonData(result)
        addPerson(person)
        return person
    }

    private fun setUpPersonData(result: Result): Person {
        return Person(
            0,
            result.name.first,
            result.name.last,
            result.name.title,
            result.gender,
            result.location,
            result.email,
            result.dob.date,
            result.dob.age,
            result.login,
            result.phone,
            result.cell,
            result.picture.large
        )
    }
}