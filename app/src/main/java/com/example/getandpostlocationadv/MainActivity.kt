package com.example.getandpostlocationadv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter
    private lateinit var info: ArrayList<String>
    private lateinit var addName: Button
    //an object from APIInterface to call the method
    var apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        info =  arrayListOf()
        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(info)
        addName = findViewById(R.id.addName)

        getAllUsers()
    }

    private fun getAllUsers() {
        apiInterface?.getUser()?.enqueue(object : Callback<List<UsersData.UsersDataItem>> {
            override fun onResponse(call: Call<List<UsersData.UsersDataItem>>,
                response: Response<List<UsersData.UsersDataItem>>
            ) {
                for (User in response.body()!!) {
                    val fullInfo = "Name: ${User.name} \nLocation: ${User.location} "
                    info.add(fullInfo)
                }
                rvMain.adapter = rvAdapter
                rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
                rvMain.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<UsersData.UsersDataItem>>, t: Throwable) {
                Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun addnew(view: android.view.View) {
        intent = Intent(applicationContext, NewUser::class.java)
        startActivity(intent)
    }
}