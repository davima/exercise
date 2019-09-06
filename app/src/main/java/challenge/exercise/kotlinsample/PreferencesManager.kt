package challenge.exercise.kotlinsample

import android.content.Context
import android.util.Log

class PreferencesManager(val context: Context) {

    init {
        instance = this
    }

    companion object {
        var instance: PreferencesManager? = null
        private const val DEFAULT_PREF = "default"

        fun isAnswered(question: String): Boolean {
            Log.d("asd", "instance: $instance")
            return instance?.context?.getSharedPreferences(DEFAULT_PREF, Context.MODE_PRIVATE)?.getBoolean(question, false) ?: false
        }

        fun setAnswered(question: String) {
            instance?.context?.getSharedPreferences(DEFAULT_PREF, Context.MODE_PRIVATE)?.edit()?.putBoolean(question, true)?.apply()
        }
    }

}