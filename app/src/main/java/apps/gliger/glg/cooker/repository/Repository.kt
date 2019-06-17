package apps.gliger.glg.cooker.repository

import androidx.lifecycle.LiveData
import apps.gliger.glg.cooker.model.Event
import apps.gliger.glg.cooker.model.PeopleResponse
import apps.gliger.glg.cooker.model.Person
import kotlinx.coroutines.Deferred

interface Repository {
    //Room-Person
    suspend fun addPerson(person: Person)
    fun getPerson(): LiveData<Person>

    //Room-Event
    fun getAllEvents():LiveData<List<Event>>
    suspend fun addEvent(event:Event)
    suspend fun removeEvent(event: Event)
    suspend fun updateEvent(event: Event)

    //Network Call
    fun getPeopleFromCloud()

    //Service
    fun setNetworkStatus(isConnected:Boolean)
    fun networkStatus(): LiveData<Boolean>
}