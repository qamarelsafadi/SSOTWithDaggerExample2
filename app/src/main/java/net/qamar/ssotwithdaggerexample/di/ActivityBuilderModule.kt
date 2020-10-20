package net.qamar.ssotwithdaggerexample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.qamar.ssotwithdaggerexample.di.main.MainRepositoryModule
import net.qamar.ssotwithdaggerexample.di.main.MainViewModelModule
import net.qamar.ssotwithdaggerexample.ui.main.MainActivity


//only activity contribution
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(  modules = [MainRepositoryModule::class , MainViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity


}