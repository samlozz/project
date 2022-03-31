package com.example.darts
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val actionButtonSave: FloatingActionButton = findViewById(R.id.save)
        // This code 'listens' for the Save button being clicked
        actionButtonSave.setOnClickListener {
            var startingScorevalue = findViewById(R.id.startingScore) as TextView
            startingScore = startingScorevalue.text.toString()

            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val  itemToHide = menu.findItem (R.id.mainmenu_settings)
        itemToHide?.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.mainmenu_addplayers -> {
                startActivity(Intent(this, PlayerDetails::class.java))
                true
            }
            R.id.mainmenu_play -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        super.onOptionsItemSelected(item)
    }

}