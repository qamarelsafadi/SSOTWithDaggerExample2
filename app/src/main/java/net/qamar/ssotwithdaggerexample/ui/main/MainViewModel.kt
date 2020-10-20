package net.qamar.ssotwithdaggerexample.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.qamar.ssotwithdaggerexample.data.model.Movie
import net.qamar.ssotwithdaggerexample.data.model.SearchResult
import net.qamar.ssotwithdaggerexample.util.SingleEvent
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    var toastMsg: LiveData<SingleEvent<String>> = repository.toastMsg


    init {
        Log.e("qmr",repository.toString())
    }


    fun getMovies(): LiveData<List<Movie>> {
        return repository.getMoviesList()
    }
}