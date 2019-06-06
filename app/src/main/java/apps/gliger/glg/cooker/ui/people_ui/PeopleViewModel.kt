package apps.gliger.glg.cooker.ui.people_ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import apps.gliger.glg.cooker.model.PeopleResponse
import apps.gliger.glg.cooker.model.Person
import apps.gliger.glg.cooker.model.Result
import apps.gliger.glg.cooker.network.RetrofitFactry
import apps.gliger.glg.cooker.repository.Repository
import apps.gliger.glg.cooker.repository.RepositoryImpl

class PeopleViewModel(private val repository: Repository): ViewModel() {

    fun getPeopleFromCloud(){
        repository.getPeopleFromCloud()
    }

    fun getPerson():LiveData<Person>{
        return repository.getPerson()
    }

}