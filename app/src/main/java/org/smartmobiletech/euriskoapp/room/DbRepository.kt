package org.smartmobiletech.euriskoapp.room

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.smartmobiletech.euriskoapp.modules.User

class DbRepository {

    companion object {
        var roomDb: RoomDb? = null
        var user: LiveData<User>? = null

        private fun initDb(context: Context): RoomDb {
            return RoomDb.getDataClient(context)
        }

        fun insertData(context: Context, user: User) {
            roomDb = initDb(context)

            CoroutineScope(IO).launch {
                roomDb!!.userDao().insertData(user)
            }
        }

        fun getUser(context: Context, user: String): LiveData<User> {
            roomDb = initDb(context)
            return roomDb!!.userDao().getUser(user)
        }

        fun getUsers(context: Context): LiveData<List<User>> {
            roomDb = initDb(context)
            return roomDb!!.userDao().getUsers()
        }

        fun updateDate(context: Context, username: String, date: String, isFirst: Boolean) {
            roomDb = initDb(context)
            CoroutineScope(IO).launch {
                roomDb!!.userDao().updateDate(username, date, isFirst)
            }
        }
    }

}