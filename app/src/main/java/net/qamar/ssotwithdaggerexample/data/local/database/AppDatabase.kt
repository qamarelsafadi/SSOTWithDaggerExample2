package net.qamar.ssotwithdaggerexample.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.qamar.ssotwithdaggerexample.data.local.dao.MovieDao
import net.qamar.ssotwithdaggerexample.data.model.Movie


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao?

    companion object {
        private const val DATABASE_DB = "database.db"
        private var appDatabase: AppDatabase? = null
        fun getInstance(context: Context?): AppDatabase? {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(
                    context!!,
                    AppDatabase::class.java, DATABASE_DB
                ).allowMainThreadQueries().build()
            }
            return appDatabase
        }
    }
}