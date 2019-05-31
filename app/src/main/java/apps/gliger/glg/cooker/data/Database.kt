package apps.gliger.glg.cooker.data

import android.content.Context
import androidx.room.*
import apps.gliger.glg.cooker.model.Event
import apps.gliger.glg.cooker.model.Person

@Database(entities = [Event::class,Person::class], version = 1)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun EventAccess(): EventDao
    abstract fun PeopleAccess(): PeopleDao

    companion object {
        private var INSTANCE: FoodDatabase? = null

        fun get(context: Context): FoodDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, FoodDatabase::class.java, "FOODDB")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}

