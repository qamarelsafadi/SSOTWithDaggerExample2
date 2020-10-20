package net.qamar.ssotwithdaggerexample.data.remote.main

import io.reactivex.Flowable
import io.reactivex.Single
import net.qamar.ssotwithdaggerexample.data.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET


interface MainApi {
        @GET("/?s=Batman&page=1&apikey=96099fe3")
        fun movieList(): Single<Response<SearchResult>>

}