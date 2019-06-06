package apps.gliger.glg.cooker.ui.location_ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import apps.gliger.glg.cooker.model.Person
import apps.gliger.glg.cooker.repository.Repository
import apps.gliger.glg.cooker.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationUIViewModel(val repository: Repository):ViewModel() {
    private lateinit var _person : LiveData<Person>

    init {
        CoroutineScope(Dispatchers.IO).launch { _person = repository.getPerson() }
    }

    val person:LiveData<Person>
        get() = _person

}