package org.smartmobiletech.euriskoapp.activities.splash

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.smartmobiletech.euriskoapp.modules.User
import org.smartmobiletech.euriskoapp.room.DbRepository

class SplashScreenViewModel: ViewModel() {

    fun insertData(context: Context, user: User) {
        DbRepository.insertData(context, user)
    }


    fun getUsers(context: Context): LiveData<List<User>> {
        return  DbRepository.getUsers(context)
    }

}