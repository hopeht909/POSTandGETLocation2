package com.example.getandpostlocationadv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spanned
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.toSpanned
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewUser : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var Location: EditText
    lateinit var savebtn: Button
    lateinit var buttonGET: Button
    lateinit var tvLocation: TextView
    lateinit var edName: EditText

    //an object from APIInterface to call the method
    var apiInterface = APIClient().getClient()?.create(APIInterface::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        name = findViewById(R.id.editTextName)
        Location = findViewById(R.id.editTextLoc)
        savebtn = findViewById(R.id.button)
        buttonGET = findViewById(R.id.buttonGET)
        tvLocation = findViewById(R.id.tvLocation)
        edName = findViewById(R.id.edName)

        savebtn.setOnClickListener {
            if (name.text.isNotEmpty() && Location.text.isNotEmpty()) {
                addSingleuser()
            } else {
                Toast.makeText(applicationContext, "please fill all the info", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        buttonGET.setOnClickListener {
            val edName = edName.text.toString()
            getLocation(edName)
        }
    }

    private fun getLocation(edName: String) {
        apiInterface?.getUser()?.enqueue(object : Callback<List<UsersData.UsersDataItem>> {
            override fun onResponse(
                call: Call<List<UsersData.UsersDataItem>>,
                response: Response<List<UsersData.UsersDataItem>>
            ) {
                for (User in response.body()!!) {
                    if (User.name.equals(edName)){
                        tvLocation.text = "Location: ${User.location}"
                    }
                }

            }

            override fun onFailure(call: Call<List<UsersData.UsersDataItem>>, t: Throwable) {
                Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun addSingleuser() {
        val nameIN = name.text.toString()
        val locationIN = Location.text.toString()
        apiInterface?.addUser(UsersData.UsersDataItem(locationIN,nameIN))?.enqueue(object : Callback<UsersData.UsersDataItem?> {
            override fun onResponse(
                call: Call<UsersData.UsersDataItem?>,
                response: Response<UsersData.UsersDataItem?>
            ) {
                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show();
            }

            override fun onFailure(call: Call<UsersData.UsersDataItem?>, t: Throwable) {
                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
            }

        })
    }

    fun viewUsers(view: android.view.View) {
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}