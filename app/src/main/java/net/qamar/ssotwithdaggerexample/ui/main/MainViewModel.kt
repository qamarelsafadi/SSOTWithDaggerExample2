package net.qamar.ssotwithdaggerexample.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.qamar.ssotwithdaggerexample.data.model.Movie
import net.qamar.ssotwithdaggerexample.data.model.SearchResult
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {


    init {
        Log.e("qmr",repository.toString())
    }


    fun getMovies(): LiveData<List<Movie>> {
        return repository.getMoviesList()
    }
}