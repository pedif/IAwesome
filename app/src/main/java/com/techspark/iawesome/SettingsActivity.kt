package com.techspark.iawesome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.techspark.iawesome.database.AwesomeDatabase
import kotlin.concurrent.thread

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            findPreference<ListPreference>("gender").also {
                it?.onPreferenceChangeListener = androidx.preference.Preference.OnPreferenceChangeListener { pref, value ->

                    if(it?.entry !=value)
                        thread(start= true) {
                            AwesomeDatabase.getInstance(activity!!.applicationContext).clearAllTables()
                        }
                    return@OnPreferenceChangeListener true
                }
            }
        }
    }
}