package com.example.newzy.ui.sources

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
import com.example.newzy.repositories.SourcesRepository
import com.stone.vega.library.VegaLayoutManager
import kotlinx.android.synthetic.main.fragment_sources.*

//This is the fragment for the second tab in the bottom navigation bar
class SourcesFragment : Fragment() {

    private lateinit var factory: SourcesViewModelFactory
    private lateinit var viewModel: SourcesViewModel
    private lateinit var sourcesRepository : SourcesRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        return inflater.inflate(
            R.layout.fragment_sources,
            container,
            false
        )

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = WebService()
        val repository =
            SourcesRepository(api)

        factory = SourcesViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(SourcesViewModel::class.java)

        viewModel.getSources()

        viewModel.listOfSources.observe(viewLifecycleOwner, Observer { listOfSources ->
            recycler_view_sources.also {
                it.setOnFlingListener(null)
                it.layoutManager = VegaLayoutManager()
                it.setHasFixedSize(true)
                it.adapter = SourcesAdapter(listOfSources, context)
            }
        })
    }

}