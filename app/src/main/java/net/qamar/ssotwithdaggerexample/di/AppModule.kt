package net.qamar.ssotwithdaggerexample.di

import android.app.Application
import android.util.Log
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import net.qamar.ssotexample.util.MoshiArrayListJsonAdapter
import net.qamar.ssotwithdaggerexample.data.local.database.AppDatabase
import net.qamar.ssotwithdaggerexample.util.BaseApplication
import net.qamar.ssotwithdaggerexample.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

companion object{

    @Provides
    fun getString(): String{
        return "test"
    }

    @Singleton
    @Provides
    fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(MoshiArrayListJsonAdapter.FACTORY)
            .build()!!
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(moshi: Moshi): Retrofit {
        if(moshi!=null)
            Log.e("qmr","hey from Moshi")

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


    @Singleton
    @Provides
    fun getDataBase(application:Application):AppDatabase{

        return AppDatabase.getInstance(application)!!
    }





}


}