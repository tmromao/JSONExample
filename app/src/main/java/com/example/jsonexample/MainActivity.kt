package com.example.jsonexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jsonexample.model.UserModelClass
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instance of users list using the data model class.
        val usersList: ArrayList<UserModelClass> = ArrayList()

        try {
            // As we have JSON object, so we are getting the object
            //Here we are calling a Method which is returning the JSON object
            val obj = JSONObject(getJSONFromAssets()!!)
            //fetch JSONArray named users by using getJSONArray
            val usersArray = obj.getJSONArray("users")

            // Get the users data using for loop i.e.
            for (i in 0 until usersArray.length()) {
                //Create a JSONObject for fetching single users data
                val user = usersArray.getJSONObject(i)
                // Fetch if store it in variable
                val id = user.getInt("id")
                val name = user.getString("name")
                val email = user.getString("email")
                val gender = user.getString("gender")
                val weight = user.getDouble("weight")
                val height = user.getInt("height")

                // create a objet for getting phone numbers data from JSONObject
                val phone = user.getJSONObject("phone")
                // fetch mobile number
                val mobile = phone.getString("mobile")
                // fetch office number
                val office = phone.getString("office")

                //Now add all the variable to the data model class and the data model class to the array list.
                val userDetails =
                    UserModelClass(id, name, email, gender, weight, height, mobile, office)

                // add the details in the list
                usersList.add(userDetails)
            }
        } catch (e: JSONException) {
            // exception
            e.printStackTrace()
        }
    }

    /**
     * Method to load the JSON from the Assets file and return the object
     */
    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUserJSONFile = assets.open("Users.json")
            val size = myUserJSONFile.available()
            val buffer = ByteArray(size)
            myUserJSONFile.read(buffer)
            myUserJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}