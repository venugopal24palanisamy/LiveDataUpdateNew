package com.venu.livedataupdate.roomDatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.venu.livedataupdate.roomDatabase.dao.UserDao
import com.venu.livedataupdate.roomDatabase.entitys.UserData

@Database(entities = [UserData::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        private var INSTANCE: UserDataBase? = null
        fun getInstance(context: Context): UserDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDataBase::class.java,
                        "UserData.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}