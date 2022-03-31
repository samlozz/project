package com.example.darts

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

var playerOneName = "New Player 1"
var playerTwoName = "New Player 2"
var startingScore = "301"
var currentPlayerScore: Int = 0
var currentScoreBeforeSaving: Int = 0

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  set the view to be displayed to the user
        //  R means resource file, layout means look in the layout folder, activity_main is the
        // design file to use in the layout folder
        setContentView(R.layout.activity_main)

        // Intialise the variable to be used to hold the current throw value
        var currentThrowValue: Int = 0

        // Initialise the variables used to hold the information on whether the
        // Double or Treble control has been activated
        val switchDouble: Switch = findViewById(R.id.switch_double)
        val switchTreble: Switch = findViewById(R.id.switch_treble)

        // Initialise the variable that holds the Save button displayed at the bottom
        // of the screen
        val actionButtonSave: FloatingActionButton = findViewById(R.id.save)

        // Initialise the variables that hold the text boxes used to display each
        // player's individual dart scores
        val player1Name: TextView = findViewById(R.id.player_one_name)
        val player2Name: TextView = findViewById(R.id.player_two_name)
        val startingScore1: TextView = findViewById(R.id.player_one_current_score)
        val startingScore2: TextView = findViewById(R.id.player_two_current_score)
        player1Name.text = playerOneName
        player2Name.text = playerTwoName
        startingScore1.text = startingScore
        startingScore2.text = startingScore

        var currentScoreTextView: TextView = findViewById(R.id.player_one_throw_total)
        val currentThrow2TextView: TextView = findViewById(R.id.player_two_throw_total)

        var currentThrowTextView: TextView = findViewById(R.id.player_one_current_throw)

        val player1Throw2: TextView = findViewById(R.id.player_one_current_throw_2)
        val player1Throw3: TextView = findViewById(R.id.player_one_current_throw_3)
        val player2Throw1: TextView = findViewById(R.id.player_two_current_throw)
        val player2Throw2: TextView = findViewById(R.id.player_two_current_throw_2)
        val player2Throw3: TextView = findViewById(R.id.player_two_current_throw_3)

        //readPlayerJSONFile()

        //  setOnClickListener for switchDouble contains the code that is activated
        //  when the user presses the Double control.
        //  This control is there to make it easier for the user to record their
        //  score, rather than having to add it up in their head.
        switchDouble.setOnClickListener {
            if (switchDouble.isChecked) {
                switchTreble.isChecked = false
                currentThrowTextView.text = (currentThrowValue * 2).toString()
                updateCurrentScore(currentThrowValue, currentScoreTextView)
            } else {
                if (!switchTreble.isChecked) {
                    currentThrowTextView.text = currentThrowValue.toString()
                    updateCurrentScore(currentThrowValue * -1, currentScoreTextView)
                }
            }
        }

        //  setOnClickListener for switchTreble contains the code that is activated
        //  when the user presses the Treble control.
        //  This control is there to make it easier for the user to record their
        //  score, rather than having to add it up in their head.
        switchTreble.setOnClickListener {
            if (switchTreble.isChecked) {
                switchDouble.isChecked = false
                currentThrowTextView.text = (currentThrowValue * 3).toString()
                updateCurrentScore(currentThrowValue * 2, currentScoreTextView)
            } else {
                if (!switchDouble.isChecked) {
                    currentThrowTextView.text = currentThrowValue.toString()
                    updateCurrentScore(currentThrowValue * -2, currentScoreTextView)
                }
            }
        }

        // This block of code intialises the variables use to hold the
        // various score buttons.  As you press a button, the score for the
        // appropriate player will change automatically.
        // There are buttons for each of the twenty options possible with a single dart
        // plus the option to hit the 25 outer bull ring, and the 50 inner bull ring.
        // There is also the option to record a zero score for a dart, should the player miss.
        val score1: Button = findViewById(R.id.button1)
        val score2: Button = findViewById(R.id.button2)
        val score3: Button = findViewById(R.id.button3)
        val score4: Button = findViewById(R.id.button4)
        val score5: Button = findViewById(R.id.button5)
        val score6: Button = findViewById(R.id.button6)
        val score7: Button = findViewById(R.id.button7)
        val score8: Button = findViewById(R.id.button8)
        val score9: Button = findViewById(R.id.button9)
        val score10: Button = findViewById(R.id.button10)
        val score11: Button = findViewById(R.id.button11)
        val score12: Button = findViewById(R.id.button12)
        val score13: Button = findViewById(R.id.button13)
        val score14: Button = findViewById(R.id.button14)
        val score15: Button = findViewById(R.id.button15)
        val score16: Button = findViewById(R.id.button16)
        val score17: Button = findViewById(R.id.button17)
        val score18: Button = findViewById(R.id.button18)
        val score19: Button = findViewById(R.id.button19)
        val score20: Button = findViewById(R.id.button20)
        val score25: Button = findViewById(R.id.button25)
        val score50: Button = findViewById(R.id.button50)
        val score0: Button = findViewById(R.id.button_no_score)

        // Each of these onClickListeners acts in a similar fashion to the Double & Treble code above.
        // The program 'listens' for a particular button to be clicked, and executes the code
        // contained in its method if it is.
        score1.setOnClickListener {
            currentThrowTextView.text = getString(R.string._1)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 1
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score2.setOnClickListener {
            currentThrowTextView.text = getString(R.string._2)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 2
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score3.setOnClickListener {
            currentThrowTextView.text = getString(R.string._3)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 3
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score4.setOnClickListener {
            currentThrowTextView.text = getString(R.string._4)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 4
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score5.setOnClickListener {
            currentThrowTextView.text = getString(R.string._5)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 5
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score6.setOnClickListener {
            currentThrowTextView.text = getString(R.string._6)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 6
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score7.setOnClickListener {
            currentThrowTextView.text = getString(R.string._7)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 7
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score8.setOnClickListener {
            currentThrowTextView.text = getString(R.string._8)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 8
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score9.setOnClickListener {
            currentThrowTextView.text = getString(R.string._9)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 9
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score10.setOnClickListener {
            currentThrowTextView.text = getString(R.string._10)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 10
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score11.setOnClickListener {
            currentThrowTextView.text = getString(R.string._11)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 11
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score12.setOnClickListener {
            currentThrowTextView.text = getString(R.string._12)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 12
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score13.setOnClickListener {
            currentThrowTextView.text = getString(R.string._13)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 13
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score14.setOnClickListener {
            currentThrowTextView.text = getString(R.string._14)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 14
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score15.setOnClickListener {
            currentThrowTextView.text = getString(R.string._15)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 15
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score16.setOnClickListener {
            currentThrowTextView.text = getString(R.string._16)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 16
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score17.setOnClickListener {
            currentThrowTextView.text = getString(R.string._17)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 17
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score18.setOnClickListener {
            currentThrowTextView.text = getString(R.string._18)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 18
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score19.setOnClickListener {
            currentThrowTextView.text = getString(R.string._19)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 19
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score20.setOnClickListener {
            currentThrowTextView.text = getString(R.string._20)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 20
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score25.setOnClickListener {
            currentThrowTextView.text = getString(R.string._25)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 25
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score50.setOnClickListener {
            currentThrowTextView.text = getString(R.string._50)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 50
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        score0.setOnClickListener {
            currentThrowTextView.text = getString(R.string._no_score)
            currentScoreBeforeSaving = currentThrowValue
            currentThrowValue = 0
            updateCurrentScore(currentThrowValue, currentScoreTextView)
            resetSwitches(switchDouble, switchTreble)
        }

        // This code 'listens' for the Save button being clicked
        actionButtonSave.setOnClickListener {

            when (currentThrowTextView.id) {
                R.id.player_one_current_throw -> currentThrowTextView = player1Throw2
                R.id.player_one_current_throw_2 -> currentThrowTextView = player1Throw3
                R.id.player_one_current_throw_3 -> {
                    currentThrowTextView = player2Throw1
                    currentScoreTextView = currentThrow2TextView
                    currentPlayerScore = 0
                }
                R.id.player_two_current_throw -> currentThrowTextView = player2Throw2
                R.id.player_two_current_throw_2 -> currentThrowTextView = player2Throw3
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val  itemToHide = menu.findItem (R.id.mainmenu_play)
        itemToHide?.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mainmenu_settings -> {
                startActivity(Intent(this, Settings::class.java))
                true
            }
            R.id.mainmenu_addplayers -> {
                startActivity(Intent(this, PlayerDetails::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        super.onOptionsItemSelected(item)
    }

    // This method resets the Double & Treble buttons to their initial states.
    private fun resetSwitches(switchButton1 : Switch, switchButton2 : Switch){
        switchButton1.isChecked = false
        switchButton2.isChecked=false
    }

    private fun updateCurrentScore(currentThrowValue: Int, currentScoreTextView: TextView){
        currentPlayerScore += currentThrowValue
        currentScoreTextView.text = currentPlayerScore.toString()

    }

/*    private fun readPlayerJSONFile(){
        try{
            val obj = JSONObject(loadJSONFromAsset())
            val player: JSONObject = obj.getJSONObject("players")
            val name = player.getString("firstName")
        }
        catch(ex:Exception){
            ex.printStackTrace()
        }
        finally{

        }
    }

    fun loadJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val `is`: InputStream = assets.open("players.xml")
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }*/
}