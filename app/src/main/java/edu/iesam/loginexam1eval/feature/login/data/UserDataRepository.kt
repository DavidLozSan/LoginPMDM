package edu.iesam.loginexam1eval.feature.login.data

import android.util.Log
import edu.iesam.loginexam1eval.feature.login.data.local.LoginXmlLocalDataSource
import edu.iesam.loginexam1eval.feature.login.domain.User
import edu.iesam.loginexam1eval.feature.login.domain.UserRepository

class UserDataRepository(private val local: LoginXmlLocalDataSource) : UserRepository {

    override fun signUp(user: User): Boolean {
        val localUser = local.findById(user.name)
        if (localUser == null) {
            local.save(user)
            return true
        }
        return false
    }

    override fun signIn(user: User): Boolean {
        val localUser = local.findById(user.name)
        if (localUser != null && localUser.password.equals(user.password)) {
            return true
        }
        return false
    }

    override fun deleteAccount(user: User): Boolean {
        val localUser = local.findById(user.name)
        if (localUser != null && localUser.password.equals(user.password)) {
            local.deleteById(user.name)
            return true
        }
        Log.d("@dev", "Ese usuario no esta registrado")
        return false
    }
}