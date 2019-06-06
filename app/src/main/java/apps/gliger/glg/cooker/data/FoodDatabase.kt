package apps.gliger.glg.cooker.data

interface FoodDatabase {
    fun EventAccess(): EventDao
    fun PeopleAccess(): PeopleDao
}