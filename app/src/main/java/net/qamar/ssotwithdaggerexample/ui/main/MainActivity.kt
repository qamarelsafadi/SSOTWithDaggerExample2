package net.qamar.ssotwithdaggerexample.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.qamar.ssotwithdaggerexample.R
import net.qamar.ssotwithdaggerexample.util.ToolsUtil
import net.qamar.ssotwithdaggerexample.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var test: String

    @Inject
    lateinit var movieRecyclerAdapter: MovieRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel::class.java)
        initiateRecyclerView()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        showProgressBar(true)
        viewModel.getMovies().observe(this, Observer {
            showProgressBar(false)
            movieRecyclerAdapter.setMovies(it)
        })
        viewModel.toastMsg.observe(this, Observer { event ->
            ToolsUtil.showToast(event?.getContentIfNotHandledOrReturnNull().toString(), this)
        })

    }

    private fun showProgressBar(isVisible: Boolean) {
        if (isVisible)
            progress_bar.visibility = View.VISIBLE
        else
            progress_bar.visibility = View.GONE

    }

    private fun initiateRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = movieRecyclerAdapter

    }
}