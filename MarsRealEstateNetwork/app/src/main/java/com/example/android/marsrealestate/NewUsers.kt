package com.example.android.marsrealestate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.overview.OverviewViewModel
import kotlinx.android.synthetic.main.activity_new_users.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject


class NewUsers : AppCompatActivity() {
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }
    //internal lateint var genderCombo Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_users)
    }
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    fun getUserPropertiesDeferred(view: View) {

        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = MarsApi.retrofitUserService.getUsers()
            //var getPropertiesDeferred = MarsApi.retrofitService.getProperties()
            try {
                // Await the completion of our Retrofit request
                var listResult = getPropertiesDeferred.await()


                // Display the new value in the text view.
                //showTotalUsers.text =  "Total Users : ${listResult.size} Users "
                //_uproperties.value = listResult
            } catch (e: Exception) {
                //showTotalUsers.text =  "Failure: ${e.message}"
            }
        }
    }

    fun selectedComboBoxGender(view: View) {
        val myToast = Toast.makeText(this, "Selected Value : ${spinnerGender.selectedItem.toString()} ", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun funDate(view: View) {

    }
    fun createNewUserDeferred(view: View) {

        val json = JSONObject()

        val first_name = editTextFirstName.text
        val last_name = editTextLastName.text
        val email = editTextEmailId.text
        val img_src = "https://img.icons8.com/ios-filled/50/000000/head-profile.png"

        val gendr = spinnerGender.selectedItem.toString()
        json.put("first_name", first_name)
        json.put("last_name", last_name)
        json.put("img_src", img_src)
        json.put("gendr", gendr)
        json.put("email", email)
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), json.toString())
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = MarsApi.retrofitUserService.createUserProfile(requestBody)
            //var getPropertiesDeferred = MarsApi.retrofitService.getProperties()
            try {
                // Await the completion of our Retrofit request
                var listResult = getPropertiesDeferred.await()

                // Display the new value in the text view.
                //showTotalUsers.text =  "New User : ${listResult.id} Created "

                val myToast = Toast.makeText(this@NewUsers, "New User ID : ${listResult.id.toString()} Saved!!!! ", Toast.LENGTH_LONG)
                myToast.show()

                val resetFirstNameTextView = findViewById<TextView>(R.id.editTextFirstName)
                resetFirstNameTextView.text = ""

                val reseteditTextLastName = findViewById<TextView>(R.id.editTextLastName)
                reseteditTextLastName.text = "".toString()
                val reseteditTextEmailId = findViewById<TextView>(R.id.editTextEmailId)
                reseteditTextEmailId.text = "".toString()

                //_uproperties.value = listResult
            } catch (e: Exception) {
                val myToast = Toast.makeText(this@NewUsers, "New User Id : ${"Failure: ${e.message}"} Saved! ", Toast.LENGTH_LONG)
                myToast.show()
            }
        }
    }
}
