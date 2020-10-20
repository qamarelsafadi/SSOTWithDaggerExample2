package net.qamar.ssotwithdaggerexample.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.qamar.ssotwithdaggerexample.data.local.dao.MovieDao
import net.qamar.ssotwithdaggerexample.data.model.Movie
import net.qamar.ssotwithdaggerexample.data.remote.main.MainApi
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val mainApi: MainApi,
    private val movieDao: MovieDao,
    private val application: Application
) {

    init {
        if(movieDao!=null)
            Log.e("qmr","hey database from repository")
    }
    fun getMoviesList(): LiveData<List<Movie>> {
        val result = mainApi.movieList()
        result.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!it!!.isSuccessful) {
                    Log.e("qmr", "not respond")
                } else {
                    Log.e("qmr", it.body().toString())
                    if (it.body()!!.search != null)

                        movieDao.insert(it.body()!!.search!!)
                }

            }, { t: Throwable? -> Log.e("qmr", t!!.message.toString()) })


        return movieDao.all
    }




}