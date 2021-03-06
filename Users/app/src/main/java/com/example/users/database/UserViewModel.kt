package com.example.users.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class UserViewModel(database: UserDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val db = database

    private var _users = MutableLiveData<List<User>>()

    val users: LiveData<List<User>>
        get() = _users

    //Add user to table
    fun addToFavorites(user: User) {
        uiScope.launch {
            addUserToDb(user)
        }
    }

    private suspend fun addUserToDb(user: User) {
        withContext(Dispatchers.IO) {
            db.insert(user)
        }
    }

    //Get the latest user from table
    fun getLatestUser() {
        uiScope.launch {
            getLatestUserFromDb()
        }
    }

    private suspend fun getLatestUserFromDb(): User? {
        return withContext(Dispatchers.IO) {
            val user: User? = db.getLatestUser()
            user
        }
    }

    //Get list of all favorite users
    fun getAllFavoriteUsers() {
        uiScope.launch {
            _users.value = getAllFavoriteUsersFromDb()
        }
    }

    private suspend fun getAllFavoriteUsersFromDb(): List<User>? {
        return withContext(Dispatchers.IO) {
            val usersList = db.getAllUsers()
            usersList
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel() // Job would cancel all coroutines
    }
}
