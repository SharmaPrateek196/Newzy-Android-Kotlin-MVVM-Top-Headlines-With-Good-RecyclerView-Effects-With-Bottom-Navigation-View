package com.example.newzy.ui.headlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newzy.R
import com.example.newzy.network.WebService
import com.example.newzy.repositories.HeadlinesRepository
import com.stone.vega.library.VegaLayoutManager
import kotlinx.android.synthetic.main.fragment_headlines.*

//This is the fragment for the first tab in the bottom navigation bar
class HeadlinesFragment : Fragment() {

    private lateinit var factory: HeadlinesViewModelFactory
    private lateinit var viewModel: HeadlinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        return inflater.inflate(
            R.layout.fragment_headlines,
            container,
            false
        )

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = WebService()
        val repository =
            HeadlinesRepository(api)

        factory = HeadlinesViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(HeadlinesViewModel::class.java)

        viewModel.getHeadlines()

        viewModel.listOfArticles.observe(viewLifecycleOwner, Observer { listOfArticles ->
            recycler_view_headlines.also {
                it.setOnFlingListener(null)
                it.layoutManager = VegaLayoutManager()
                it.setHasFixedSize(true)
                it.adapter = HeadlinesAdapter(listOfArticles, context)
            }
        })
    }

}