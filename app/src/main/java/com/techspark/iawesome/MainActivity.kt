package com.techspark.iawesome

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPref()
    }

    private fun checkPref() {

        if (!PreferenceManager.getDefaultSharedPreferences(this).contains("gender"))
            startSettingsActivity()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_settings -> startSettingsActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startSettingsActivity() {

        startActivity(Intent(this, SettingsActivity::class.java))
    }
}
