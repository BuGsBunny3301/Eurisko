package org.smartmobiletech.euriskoapp

import android.content.Context
import android.content.SharedPreferences
import org.smartmobiletech.euriskoapp.modules.User

object AppPreferences {

    private lateinit var prefrences: SharedPreferences
    private const val NAME = "myapp"
    private const val MODE = Context.MODE_PRIVATE

    fun init(context: Context) {
        prefrences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun clear() {
        prefrences.edit {
            it.clear()
        }
    }

    var username: String
        get() = prefrences.getString("username", "")!!

        set(value) = prefrences.edit {
            it.putString("username", value)
        }

    var pass: String
        get() = prefrences.getString("pass", "")!!

        set(value) = prefrences.edit {
            it.putString("pass", value)
        }

    var date: String
        get() = prefrences.getString("date", "")!!

        set(value) = prefrences.edit {
            it.putString("date", value)
        }
    var watch: Long
        get() = prefrences.getLong("watch", 0)

        set(value) = prefrences.edit {
            it.putLong("watch", value)
        }
}