package com.example.randomnumberguessinggame

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    companion object {
        private const val CEIL_LIMIT = 1000
    }

    private var numberToGuess = Random.nextInt(1, CEIL_LIMIT)
    private var numberOfTries = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helpView.text = "${helpView.text} $CEIL_LIMIT"

        userGuessView.setOnFocusChangeListener { view, _ ->
            if (!view.hasFocus()) {
                onNumberInput()
            }
        }
        resetGame()
    }

    private fun resetGame() {
        resetNumberToGuess()
        resetNumberOfTries()
        resetUI()
    }

    private fun resetUI() {
        resetUserGuessView()
        resetHintView()
    }

    private fun resetNumberToGuess() {
        numberToGuess = Random.nextInt(1, CEIL_LIMIT)
    }

    private fun resetNumberOfTries() {
        numberOfTries = 0
    }

    private fun resetUserGuessView() {
        userGuessView.text.clear()
        userGuessView.isEnabled = true
    }

    private fun resetHintView() {
        hintView.text = ""
        hintView.setTextColor(Color.BLACK)
    }

    private fun updateUserGuessView(userGuessNumber: Int) {
        if (userGuessNumber == numberToGuess) {
            userGuessView.isEnabled = false
        }
        userGuessView.text.clear()
    }

    private fun updateHintView(userGuessNumber : Int) {
        if (userGuessNumber < numberToGuess) {
            hintView.text = "Hint: It is higher than $userGuessNumber!"
        } else if (userGuessNumber > numberToGuess) {
            hintView.text = "Hint: It is lower than $userGuessNumber!"
        } else {
            hintView.text = "You guessed! It was $userGuessNumber! You made $numberOfTries tries"
            hintView.setTextColor(Color.GREEN)
        }
    }

    private fun onNumberInput() {
        val userGuessNumber = userGuessView.text.toString().toInt()

        if (userGuessNumber > CEIL_LIMIT) {
            hintView.text = "Your number must be bellow $CEIL_LIMIT"
            resetUserGuessView()
            return
        }

        numberOfTries++
        updateHintView(userGuessNumber)
        updateUserGuessView(userGuessNumber)
    }

    fun onPlayAgainButtonClick(view : View) {
        resetGame()
    }


}
