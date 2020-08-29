package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // The current word
    private var _word = MutableLiveData<String>()

    // The current score
    private var _score = MutableLiveData<Int>()

    private var _eventGameFinish = MutableLiveData<Boolean>()

    private var _currentTime = MutableLiveData<Long>()

    val score: LiveData<Int>
        get() = _score

    val word: LiveData<String>
        get() = _word

    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    val currentTime: LiveData<Long>
        get() = _currentTime

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private  var timer: CountDownTimer

    companion object {
        //when game is over
        const val DONE = 0L

        //number of milliseconds in a second
        const val ONE_SECOND = 1000L

        //total time of the game
        const val COUNTDOWN_TIME = 60000L
    }


    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            //gameFinished()
            //_eventGameFinish.value =
            if (currentTime.value!! > DONE) {
                resetList()
            }

        } else if (currentTime.value == DONE) {
            _eventGameFinish.value = true
        } else {
            _word.value = wordList.removeAt(0)
        }

    }

    fun onSkip() {
        _score.value = score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = score.value?.plus(1)
        nextWord()
    }


    init {
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
        _score.value = 0
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            //what should happen on each tick
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished
            }

            //when the timer finishes
            override fun onFinish() {
                _eventGameFinish.value = true
            }
        }
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

}