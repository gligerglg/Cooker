package apps.gliger.glg.cooker.data

import android.content.Context

interface FoodDatabase {
    fun get(context: Context):FoodDatabaseImpl
}