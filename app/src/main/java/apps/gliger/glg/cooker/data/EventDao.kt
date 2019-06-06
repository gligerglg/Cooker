package apps.gliger.glg.cooker.data

import androidx.lifecycle.LiveData
import androidx.room.*
import apps.gliger.glg.cooker.model.Event

@Dao
interface EventDao {
    @Insert
    fun addEvent(event:Event)

    @Delete
    fun deleteEvent(event: Event)

    @Update
    fun updateEvent(event: Event)

    @Query("Select * from eventTbl")
    fun getAllEvents():LiveData<List<Event>>

}