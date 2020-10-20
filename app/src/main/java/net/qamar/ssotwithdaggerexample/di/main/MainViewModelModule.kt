package net.qamar.ssotwithdaggerexample.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.qamar.ssotwithdaggerexample.di.ViewModelKey
import net.qamar.ssotwithdaggerexample.ui.main.MainViewModel

@Module
abstract class MainViewModelModule {

@Binds
@IntoMap
@ViewModelKey(MainViewModel::class)
abstract fun getBindMainViewModel(mainViewModel: MainViewModel):ViewModel

}