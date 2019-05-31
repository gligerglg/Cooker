package apps.gliger.glg.cooker.ui.people_ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import apps.gliger.glg.cooker.data.FoodDatabase
import apps.gliger.glg.cooker.model.PeopleResponse
import apps.gliger.glg.cooker.model.Person
import apps.gliger.glg.cooker.model.Result
import apps.gliger.glg.cooker.network.RetrofitFactry
import apps.gliger.glg.cooker.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PeopleViewModel(application: Application): AndroidViewModel(application) {

    private val http = RetrofitFactry.getService()
    private val repository = Repository.getInstance(application)

    suspend fun getPeopleList():PeopleResponse?{
        try {
            return http.getPeople().await()
        }catch (e:Exception){
        }
        return null
    }

    suspend fun savePeopleData(result: Result):Person{
        val person = setUpPersonData(result)
        repository.addPerson(person)
        return person
    }

    fun setUpPersonData(result: Result):Person{
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

    fun getPerson():LiveData<Person>{
        return repository.getPerson()
    }

}