package org.smartmobiletech.euriskoapp.ui.listview

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.smartmobiletech.euriskoapp.modules.User
import org.smartmobiletech.euriskoapp.modules.UserData
import org.smartmobiletech.euriskoapp.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {

    lateinit var userData: List<UserData>

    private val mutableSelectedItem = MutableLiveData<UserData>()
    fun selectedRow(): LiveData<UserData> = mutableSelectedItem

    fun getData(context: Context): LiveData<List<UserData>> {

        userData = ArrayList()
        val api = ApiInterface.create()
        val call = api.getData()

        val allData: MutableLiveData<List<UserData>> = MutableLiveData()

        call.enqueue(object : Callback<List<UserData>> {
            override fun onResponse(call: Call<List<UserData>>?, response: Response<List<UserData>>?) {

                if(response?.body() != null) {
                    userData = response.body()!!
                    allData.value = userData
                }
            }

            override fun onFailure(call: Call<List<UserData>>?, t: Throwable?) {
                Toast.makeText(context, "failed to fetch data", Toast.LENGTH_LONG).show()
            }
        })
        return allData
    }


    fun itemClicked(row: UserData) {
        mutableSelectedItem.value = row
    }

}