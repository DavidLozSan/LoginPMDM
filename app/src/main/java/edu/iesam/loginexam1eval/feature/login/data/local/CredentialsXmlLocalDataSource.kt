package edu.iesam.loginexam1eval.feature.login.data.local

import android.content.Context
import com.google.gson.Gson
import edu.iesam.loginexam1eval.feature.login.domain.UserCredentials

class CredentialsXmlLocalDataSource(private val context: Context) {

    private val sharedPref = context.getSharedPreferences(
        "credentials-storage", Context.MODE_PRIVATE
    )

    private val gson = Gson()

    fun save(credentials: UserCredentials) {
        val editor = sharedPref.edit()
        editor.putString("user_credentials", gson.toJson(credentials))
        editor.apply()
    }

    fun getSavedCredentials(): UserCredentials? {
        return sharedPref.getString("user_credentials", null)?.let { userCredentialsJson ->
            gson.fromJson(userCredentialsJson, UserCredentials::class.java)
        }
    }

    fun clear() {
        sharedPref.edit().clear().apply()
    }
}