package org.smartmobiletech.euriskoapp

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import org.smartmobiletech.euriskoapp.Utility.TIMER_INTERVAL
import org.smartmobiletech.euriskoapp.activities.login.LoginActivity
import org.smartmobiletech.euriskoapp.ui.detailsfragment.DetailsViewModel
import org.smartmobiletech.euriskoapp.ui.listview.ListViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var listModel: ListViewModel
    private lateinit var detailsModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listModel = ViewModelProvider(this).get(ListViewModel::class.java)
        detailsModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_timer), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        listModel.selectedRow().observe(this, {
            val args = Bundle()
            val json = GsonBuilder().create().toJson(it)
            args.putString("rowdata", json)
            navController.navigate(R.id.nav_details, args)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_logout) {
            alertBox(this)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun alertBox(context: Context) {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("Confirm logout?")
            setPositiveButton("Yes") { _, _ ->
                val intent = Intent(context, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                AppPreferences.clear()
                finish()
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }

//    override fun onBackPressed() {
//        val builder = AlertDialog.Builder(this)
//        with(builder) {
//            setTitle("Confirm exit?")
//            setPositiveButton("Yes") { _, _ ->
//                finish()
//            }
//            setNegativeButton("No") { dialog, _ ->
//                dialog.dismiss()
//            }
//            show()
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}