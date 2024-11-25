package edu.iesam.loginexam1eval.core

import android.app.Application
import edu.iesam.loginexam1eval.core.di.LocalModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class LoginExam1Eval : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LoginExam1Eval)
            modules(
                LocalModule().module
            )
        }
    }
}