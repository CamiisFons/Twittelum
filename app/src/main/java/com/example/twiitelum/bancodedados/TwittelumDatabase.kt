package com.example.twiitelum.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import caelum.com.twittelumapp.bancodedados.TweetDao
import caelum.com.twittelumapp.modelo.Tweet


@Database(entities = [Tweet::class], version = 1)
abstract class TwittelumDatabase : RoomDatabase() {
    abstract fun getTweetDao(): TweetDao

    companion object {
        private var database: TwittelumDatabase? = null
        fun getDatabase(context: Context): TwittelumDatabase {
            return database ?: criaDatabase(context).also { database = it }
        }

        private fun criaDatabase(context: Context): TwittelumDatabase {
            return Room
                    .databaseBuilder(context, TwittelumDatabase::class.java, "twittelum-db")
                    .allowMainThreadQueries()
                    .build()
        }
    }
}