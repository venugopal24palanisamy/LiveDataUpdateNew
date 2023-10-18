package com.venu.livedataupdate.roomDatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.venu.livedataupdate.roomDatabase.entitys.UserData


@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: UserData)

    @Query("SELECT * from user_table")
     fun getAllUserDetails(): LiveData<List<UserData>>

}