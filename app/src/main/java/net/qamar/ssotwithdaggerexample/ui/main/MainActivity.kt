package net.qamar.ssotwithdaggerexample.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import net.qamar.ssotwithdaggerexample.R
import net.qamar.ssotwithdaggerexample.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var test : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel::class.java)
        subscribeObserver()
    }

    private fun subscribeObserver(){
        viewModel.getMovies().observe(this, Observer {
            for (i in it.indices)
                Log.e("qmrItemsTitle", "${it[i].title}")

        })

    }
}