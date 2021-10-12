package org.smartmobiletech.euriskoapp.activities.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.smartmobiletech.euriskoapp.modules.User
import org.smartmobiletech.euriskoapp.room.DbRepository

//import org.smartmobiletech.euriskoapp.room.RoomDb

class LoginViewModel : ViewModel() {

//    private var roomDb: RoomDb? = null
//
//    private fun initDb(context: Context): RoomDb {
////        return RoomDb.getDataClient(context)
//    }
//
    fun getUser(context: Context, user: String): LiveData<User> {
        return DbRepository.getUser(context, user)
    }

    fun getUsers(context: Context): LiveData<List<User>> {
        return  DbRepository.getUsers(context)
    }

    fun updateDate(context: Context, username: String, date: String, isFirst: Boolean) {
        DbRepository.updateDate(context, username, date, isFirst)
    }

}