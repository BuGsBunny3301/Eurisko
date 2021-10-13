package org.smartmobiletech.euriskoapp.activities.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.smartmobiletech.euriskoapp.AppPreferences
import org.smartmobiletech.euriskoapp.MainActivity
import org.smartmobiletech.euriskoapp.R
import org.smartmobiletech.euriskoapp.activities.login.LoginActivity
import org.smartmobiletech.euriskoapp.activities.login.LoginViewModel
import org.smartmobiletech.euriskoapp.modules.User

class SplashScreen : AppCompatActivity() {

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        viewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)

        viewModel.getUsers(this).observe(this, {
            if(it.isEmpty()) {
                viewModel.insertData(this, User(0, "user", "user", "0", true))
                viewModel.insertData(this, User(1, "user2", "user2", "0", true))
            }
        })


        if(AppPreferences.username == "" && AppPreferences.pass == "") {
            Handler().postDelayed({
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }, 2000)
        }
        else
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }, 2000)
    }
}