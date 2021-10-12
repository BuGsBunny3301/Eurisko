package org.smartmobiletech.euriskoapp.modules

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
class User constructor(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "username")
        val username: String?,
        @ColumnInfo(name = "password")
        val password: String?,
        @ColumnInfo(name = "date")
        val date: String?,
        @ColumnInfo(name = "isFirst")
        val isFirst: Boolean
)
