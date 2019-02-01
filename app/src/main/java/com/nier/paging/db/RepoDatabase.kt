package com.nier.paging.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nier.paging.model.Repo

/**
 * Created by fgd
 * Date 2019/1/25
 * Database schema that holds the list of repos.
 */
@Database(
        entities = [Repo::class],
        version = 1,
        exportSchema = false
)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun reposDao(): RepoDao

    companion object {

        @Volatile
        private var INSTANCE: RepoDatabase? = null

        fun getInstance(context: Context): RepoDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        RepoDatabase::class.java, "Github.db")
                        .build()
    }
}
 
