package com.venu.livedataupdate

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venu.livedataupdate.repository.Repository
import com.venu.livedataupdate.roomDatabase.entitys.UserData
import kotlinx.coroutines.launch

class MainViewModel(private var repository: Repository) : ViewModel() {

    val allUserDetails: LiveData<List<UserData>> = repository.getAllUserDetails()
    fun insertUser(name: String, mobileNumber: String, location: String) {
        viewModelScope.launch {
            repository.insertUser(
                UserData(
                    0,
                    name,
                    location,
                    mobileNumber
                )
            )
        }
    }
}