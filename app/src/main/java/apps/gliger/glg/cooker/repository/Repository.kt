package apps.gliger.glg.cooker.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import apps.gliger.glg.cooker.data.EventDao
import apps.gliger.glg.cooker.data.FoodDatabase
import apps.gliger.glg.cooker.data.PeopleDao
import apps.gliger.glg.cooker.model.Event
import apps.gliger.glg.cooker.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean

class Repository private constructor(context: Context){

    private lateinit var eventDao: EventDao
    private lateinit var peopleDao: PeopleDao
    private var database: FoodDatabase = FoodDatabase.get(context)
    private val _networkStatus= MutableLiveData<Boolean>()

    companion object {
        var INSTANCE: Repository?=null

        fun getInstance(context: Context) : Repository {
            if(INSTANCE==null) {
                INSTANCE = Repository(context)
            }
            return INSTANCE!!
        }
    }

    init {
        eventDao = database.EventAccess()
        peopleDao = database.PeopleAccess()
    }

    fun getAllEvents(): LiveData<List<Event>> {
        return eventDao.getAllEvents()
    }

    fun getEventById(id: Long): LiveData<Event> {
        return eventDao.getEventById(id)
    }

    suspend fun addEvent(event: Event) {
        withContext(Dispatchers.IO){ eventDao.addEvent(event)}
    }

    suspend fun removeEvent(event: Event) {
        withContext(Dispatchers.IO){eventDao.deleteEvent(event)}
    }

    suspend fun updateEvent(event: Event) {
        withContext(Dispatchers.IO){eventDao.updateEvent(event)}
    }

    fun getPerson():LiveData<Person>{
        return peopleDao.getPerson()
    }

    suspend fun addPerson(person: Person){
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