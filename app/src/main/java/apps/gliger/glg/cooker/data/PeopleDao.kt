package apps.gliger.glg.cooker.data

import androidx.lifecycle.LiveData
import androidx.room.*
import apps.gliger.glg.cooker.model.Person
import apps.gliger.glg.cooker.model.Result

@Dao
interface PeopleDao {
    @Query("Select * from person_tbl limit 1")
    fun getPerson():LiveData<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertPerson(person: Person)

}