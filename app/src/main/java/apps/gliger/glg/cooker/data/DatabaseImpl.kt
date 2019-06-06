package apps.gliger.glg.cooker.data

import android.content.Context
import androidx.room.*
import apps.gliger.glg.cooker.model.Event
import apps.gliger.glg.cooker.model.Person

@Database(entities = [Event::class,Person::class], version = 1)
abstract class FoodDatabaseImpl : RoomDatabase() {
    abstract fun EventAccess(): EventDao
    abstract fun PeopleAccess(): PeopleDao

    companion object {

        @Volatile private var INSTANCE: FoodDatabaseImpl? = null
        private val LOCK = Any()

        fun get(context: Context): FoodDatabaseImpl {
            synchronized(LOCK){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FoodDatabaseImpl::class.java, "FOODDB")
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}

