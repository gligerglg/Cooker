package apps.gliger.glg.cooker.ui.main_menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import apps.gliger.glg.cooker.model.Event
import apps.gliger.glg.cooker.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainMenuViewModel(application: Application) : AndroidViewModel(application) {
    var repository: Repository = Repository(application)
    private lateinit var _eventList: LiveData<List<Event>>
    var ioScope = CoroutineScope(Dispatchers.IO)

    init {
        ioScope.launch { _eventList = repository.getAllEvents() }
    }

    val eventList: LiveData<List<Event>>
        get() = _eventList

    fun getEventById(id: Long): LiveData<Event> {
        return repository.getEventById(id)
    }

    fun addNewEvent(event: Event) {
        viewModelScope.launch {
            repository.addEvent(event)
        }
        /*ioScope.launch {
            repository.addEvent(event)
        }*/
    }

    fun removeEvent(event: Event) {
        viewModelScope.launch { repository.removeEvent(event) }
//        ioScope.launch { repository.removeEvent(event) }
    }

    fun updateEvent(event: Event) {
        viewModelScope.launch { repository.updateEvent(event) }
//        ioScope.launch { repository.updateEvent(event) }
    }

}