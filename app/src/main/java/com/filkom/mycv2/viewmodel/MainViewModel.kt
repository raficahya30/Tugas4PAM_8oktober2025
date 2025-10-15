package com.filkom.mycv2.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filkom.mycv2.data.UserData
import com.filkom.mycv2.data.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(private val context: Context) : ViewModel() {
    private val userPrefs = UserPreferences(context)

    private val _userData = MutableStateFlow(UserData())
    val userData: StateFlow<UserData> = _userData

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            userPrefs.getUser().collect {
                _userData.value = it
            }
        }
    }

    fun registerUser(nim: String, nama: String, email: String, alamat: String, password: String) {
        viewModelScope.launch {
            userPrefs.saveUser(nim, nama, email, alamat, password)
            _userData.value = UserData(nim, nama, email, alamat, password)
        }
    }

    suspend fun login(email: String, password: String): Boolean {
        val saved = userPrefs.getUser().first()
        return saved.email == email && saved.password == password
    }
}
