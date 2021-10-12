package org.smartmobiletech.euriskoapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.smartmobiletech.euriskoapp.modules.User

@Database(entities = [(User::class)], version = 1, exportSchema = false)
abstract class RoomDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDb? = null

        @Synchronized
        fun getDataClient(context: Context): RoomDb {
            if(INSTANCE == null)
                INSTANCE = Room
                        .databaseBuilder(context, RoomDb::class.java, "app.db")
                        .fallbackToDestructiveMigration()
                        .build()
            return INSTANCE!!
        }
    }
}