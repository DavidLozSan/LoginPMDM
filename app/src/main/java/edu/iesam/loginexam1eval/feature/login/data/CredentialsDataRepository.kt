package edu.iesam.loginexam1eval.feature.login.data

import edu.iesam.loginexam1eval.feature.login.data.local.CredentialsXmlLocalDataSource
import edu.iesam.loginexam1eval.feature.login.domain.CredentialsRepository
import edu.iesam.loginexam1eval.feature.login.domain.UserCredentials

class CredentialsDataRepository(private val local: CredentialsXmlLocalDataSource) :
    CredentialsRepository {

    override fun getSavedCredentials(): UserCredentials? {
        return local.getSavedCredentials()
    }

    override fun save(credentials: UserCredentials) {
        local.save(credentials)
    }

    override fun clear() {
        local.clear()
    }

}