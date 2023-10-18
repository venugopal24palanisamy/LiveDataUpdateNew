package com.venu.livedataupdate.repository

import androidx.lifecycle.LiveData
import com.venu.livedataupdate.roomDatabase.dao.UserDao
import com.venu.livedataupdate.roomDatabase.entitys.UserData

class Repository(val stdao: UserDao) {
    suspend fun insertUser(temple: UserData) = stdao.addUser(temple)
    fun getAllUserDetails(): LiveData<List<UserData>> = stdao.getAllUserDetails()
}