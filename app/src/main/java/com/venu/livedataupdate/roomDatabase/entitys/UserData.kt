package com.venu.livedataupdate.roomDatabase.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "UserName")
    val userName: String,

    @ColumnInfo(name = "Location")
    val location: String,


    @ColumnInfo(name = "MobileNumber")
    val mobileNumber: String,
)

