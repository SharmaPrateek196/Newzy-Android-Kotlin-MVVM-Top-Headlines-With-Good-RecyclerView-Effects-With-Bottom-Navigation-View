package com.example.newzy.ui.sources

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newzy.R
import com.example.newzy.dataObjects.Source
import com.example.newzy.databinding.ItemSourceBinding

//Adapter for the Sources' Recycler-View
class SourcesAdapter(
    private val sources: List<Source>,
    private val context: Context?
) : RecyclerView.Adapter<SourcesAdapter.SourcesViewHolder>(){

    override fun getItemCount() = sources.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SourcesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_source,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        holder.itemSourceBinding.source = sources[position]
        holder.itemView.setOnClickListener { handleItemClick(holder.itemView, sources[position]) }
    }

    inner class SourcesViewHolder(
        val itemSourceBinding: ItemSourceBinding
    ) : RecyclerView.ViewHolder(itemSourceBinding.root)

    private fun handleItemClick(
        itemView: View,
        source: Source
    ) {
        val uriUrl: Uri? = Uri.parse(source.url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        ContextCompat.startActivity(context!!, launchBrowser, null)
    }

}