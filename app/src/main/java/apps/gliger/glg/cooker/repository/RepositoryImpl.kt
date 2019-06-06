package apps.gliger.glg.cooker.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import apps.gliger.glg.cooker.data.EventDao
import apps.gliger.glg.cooker.data.FoodDatabaseImpl
import apps.gliger.glg.cooker.data.PeopleDao
import apps.gliger.glg.cooker.model.Event
import apps.gliger.glg.cooker.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl(val context: Context):Repository{

    private lateinit var eventDao: EventDao
    private lateinit var peopleDao: PeopleDao
    private var database: FoodDatabaseImpl = FoodDatabaseImpl.get(context)
    private val _networkStatus= MutableLiveData<Boolean>()

    init {
        eventDao = database.EventAccess()
        peopleDao = database.PeopleAccess()
    }

    override fun getAllEvents(): LiveData<List<Event>> {
        return eventDao.getAllEvents()
    }

    fun getEventById(id: Long): LiveData<Event> {
        return eventDao.getEventById(id)
    }

    override suspend fun addEvent(event: Event) {
        withContext(Dispatchers.IO){ eventDao.addEvent(event)}
    }

    override suspend fun removeEvent(event: Event) {
        withContext(Dispatchers.IO){eventDao.deleteEvent(event)}
    }

    override suspend fun updateEvent(event: Event) {
        withContext(Dispatchers.IO){eventDao.updateEvent(event)}
    }

    override fun getPerson():LiveData<Person>{
        return peopleDao.getPerson()
    }

    override suspend fun addPerson(person: Person){
        withContext(Dispatchers.IO){
            peopleDao.upsertPerson(person)
        }
    }

    val networkStatus : LiveData<Boolean>
        get() = _networkStatus

    fun setNetworkStatus(isConnected:Boolean){
        _networkStatus.value = isConnected
    }
}