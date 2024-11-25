package edu.iesam.loginexam1eval.feature.login.data

import edu.iesam.loginexam1eval.feature.login.data.local.LoginXmlLocalDataSource
import edu.iesam.loginexam1eval.feature.login.domain.User
import edu.iesam.loginexam1eval.feature.login.domain.UserRepository
import org.koin.core.annotation.Single

@Single
class UserDataRepository(private val local: LoginXmlLocalDataSource) : UserRepository {

    override fun register(user: User): Boolean {
        val localUser = local.findById(user.id)
        if (localUser == null) {
            local.save(user)
            return true
        }
        return false
    }
}