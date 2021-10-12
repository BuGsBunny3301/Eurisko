package org.smartmobiletech.euriskoapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import org.smartmobiletech.euriskoapp.modules.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(user: User): Void

    @Query("UPDATE User SET date=:date, isFirst=:isFirst WHERE username = :username")
    suspend fun updateDate(username: String, date: String, isFirst: Boolean): Void

    @Query("SELECT * FROM User")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE username = :username")
    fun getUser(username: String?): LiveData<User>
}