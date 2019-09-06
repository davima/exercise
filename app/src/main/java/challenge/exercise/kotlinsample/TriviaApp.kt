package challenge.exercise.kotlinsample

import android.app.Application

class TriviaApp: Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesManager(applicationContext)
    }
}