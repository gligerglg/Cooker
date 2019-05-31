package apps.gliger.glg.cooker.repository

import android.content.Context
import androidx.lifecycle.LiveData
import apps.gliger.glg.cooker.data.EventDao
import apps.gliger.glg.cooker.data.FoodDatabase
import apps.gliger.glg.cooker.data.PeopleDao
import apps.gliger.glg.cooker.model.Event
import apps.gliger.glg.cooker.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository(val context: Context) {

    private lateinit var eventDao: EventDao
    private lateinit var peopleDao: PeopleDao
    private var database: FoodDatabase = FoodDatabase.get(context)

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

}