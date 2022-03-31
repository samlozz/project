package com.example.darts
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlayerDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_details)
        val player1Name: TextView = findViewById(R.id.startingScore)
        val player2Name: TextView = findViewById(R.id.player2Name)
        player1Name.text = playerOneName
        player2Name.text = playerTwoName
        val actionButtonSave: FloatingActionButton = findViewById(R.id.save)
        // This code 'listens' for the Save button being clicked
        actionButtonSave.setOnClickListener {
            val textViewPlayer1 = findViewById(R.id.startingScore) as TextView
            val textViewPlayer2 = findViewById(R.id.player2Name) as TextView
            playerOneName = textViewPlayer1.text.toString()
            playerTwoName = textViewPlayer2.text.toString()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val  itemToHide = menu.findItem (R.id.mainmenu_addplayers)
        itemToHide?.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mainmenu_settings -> {
                startActivity(Intent(this, Settings::class.java))
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