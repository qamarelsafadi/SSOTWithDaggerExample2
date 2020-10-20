package net.qamar.ssotwithdaggerexample.di.main

import android.app.Application
import dagger.Module
import dagger.Provides
import net.qamar.ssotwithdaggerexample.data.local.dao.MovieDao
import net.qamar.ssotwithdaggerexample.data.local.database.AppDatabase
import net.qamar.ssotwithdaggerexample.data.remote.main.MainApi
import net.qamar.ssotwithdaggerexample.ui.main.MainRepository
import net.qamar.ssotwithdaggerexample.ui.main.MovieRecyclerAdapter
import retrofit2.Retrofit


@Module
abstract class MainRepositoryModule {

    companion object {

        @Provides
        fun getMainApi(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }
        @Provides
        fun movieDao(appDatabase: AppDatabase) : MovieDao{
            return appDatabase.movieDao!!
        }

        @Provides
        fun adapter(): MovieRecyclerAdapter {
            return MovieRecyclerAdapter()
        }
        @Provides
        fun repository(mainApi: MainApi, movieDao: MovieDao , application: Application ): MainRepository {
            return MainRepository(
                mainApi,
                movieDao,
                application
            )
        }



    }
}