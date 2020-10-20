package net.qamar.ssotwithdaggerexample.util

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import net.qamar.ssotwithdaggerexample.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return  DaggerAppComponent.builder().application(this).build()
    }


}