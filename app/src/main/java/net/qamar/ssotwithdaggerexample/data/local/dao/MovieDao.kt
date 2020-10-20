package net.qamar.ssotwithdaggerexample.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.qamar.ssotwithdaggerexample.data.model.Movie


@Dao
interface MovieDao{
    @get:Query("select * from Movie")
    val all: LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(t: Movie?)

    @Insert(onConflict =OnConflictStrategy.REPLACE  )
    fun insert(t: List<Movie>)
}