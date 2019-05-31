package apps.gliger.glg.cooker.model

import androidx.room.*
import java.lang.ref.SoftReference

data class Food(var id: Long, var foodName: String, var cooking_time: Long)

@Entity(tableName = "eventTbl")
data class Event(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "eventId")
    var id: Long?,
    @ColumnInfo(name = "eventTitle")
    var eventTitle: String,
    @ColumnInfo(name = "eventStatus")
    var status: String,
    @ColumnInfo(name = "foodList")
    var foodList: String
) {
    constructor(title: String, status: String) : this(null, title, status, "FOOD")
}

@Entity(tableName = "person_tbl")
data class Person(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var firstName: String,
    var lastName: String,
    var title: String,
    var gender: String,
    @Embedded
    var location: Location,
    var email: String,
    var date: String,
    var age: Int,
    @Embedded
    var login: Login,
    var phone:String,
    var cell:String,
    var image:String
)