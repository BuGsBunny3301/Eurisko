package org.smartmobiletech.euriskoapp.activities.login

import android.content.Intent
import android.icu.util.LocaleData
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.smartmobiletech.euriskoapp.AppPreferences
import org.smartmobiletech.euriskoapp.MainActivity
import org.smartmobiletech.euriskoapp.R
import org.smartmobiletech.euriskoapp.modules.User
import org.smartmobiletech.euriskoapp.room.RoomDb
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

//import org.smartmobiletech.euriskoapp.room.RoomDb

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var edtName : EditText
    private lateinit var edtPass : EditText
    private lateinit var btnLogin : Button
    private lateinit var rememberMe : CheckBox
    private lateinit var users : ArrayList<User>
    private lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

//        getUser()
        getUsers()
        initViews()
    }

    private fun getUser() {
        viewModel.getUser(this, "user").observe(this, {
            user = it
            Toast.makeText(this@LoginActivity, it.username, Toast.LENGTH_LONG).show()
        })
    }

    private fun initViews() {
        edtName = findViewById(R.id.edt_username)
        edtPass = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btn_login)
        rememberMe = findViewById(R.id.check_remember)

        edtName.setText(AppPreferences.username)
        edtPass.setText(AppPreferences.pass)

        var notFound = true

        btnLogin.setOnClickListener {
            for (user in users) {
                if (user.username == edtName.text.toString() && user.password == edtPass.text.toString()) {
                    if(rememberMe.isChecked) {
                        AppPreferences.username = user.username
                        AppPreferences.pass = user.password
                    }
                    if(user.isFirst) {
                        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                        val date = sdf.format(Date())
                        viewModel.updateDate(this, user.username, date, false)
                        AppPreferences.date = date
                    } else
                        AppPreferences.date = user.date.toString()

                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    this.finish()
                    notFound = false
                }
            }
            if(notFound)
                Toast.makeText(this, "Wrong username or password", Toast.LENGTH_LONG).show()
        }
    }

    private fun getUsers() {
        viewModel.getUsers(this).observe(this, {
            users = ArrayList()
            users.addAll(it)
        })
    }
}