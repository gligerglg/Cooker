package apps.gliger.glg.cooker.ui.main_menu

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import apps.gliger.glg.cooker.model.Event
import apps.gliger.glg.cooker.repository.Repository
import apps.gliger.glg.cooker.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainMenuViewModel(val repository: Repository) : ViewModel() {
    private var _eventList: LiveData<List<Event>> = repository.getAllEvents()

    val eventList: LiveData<List<Event>>
        get() = _eventList

    fun addNewEvent(event: Event) {
        viewModelScope.launch {
            repository.addEvent(event)
        }
    }

    fun removeEvent(event: Event) {
        viewModelScope.launch { repository.removeEvent(event) }
    }

    fun updateEvent(event: Event) {
        viewModelScope.launch { repository.updateEvent(event) }
    }

}