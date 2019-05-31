package apps.gliger.glg.cooker.ui.location_ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import apps.gliger.glg.cooker.model.Person
import apps.gliger.glg.cooker.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationUIViewModel(application: Application):AndroidViewModel(application) {
    val repository = Repository.getInstance(application)
    private lateinit var _person : LiveData<Person>

    init {
        CoroutineScope(Dispatchers.IO).launch { _person = repository.getPerson() }
    }

    val person:LiveData<Person>
        get() = _person

}