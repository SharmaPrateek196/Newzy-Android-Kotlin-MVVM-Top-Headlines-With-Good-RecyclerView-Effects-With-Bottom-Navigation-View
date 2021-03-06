package com.example.newzy.ui.headlines

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newzy.R
import com.example.newzy.dataObjects.NewsArticle
import com.example.newzy.databinding.ItemHeadlineBinding

//Adapter for the Headlines' Recycler-View
class HeadlinesAdapter(
    private val articles: List<NewsArticle>,
    private val context: Context?
) : RecyclerView.Adapter<HeadlinesAdapter.HeadlinesViewHolder>(){

    override fun getItemCount() = articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HeadlinesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_headline,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HeadlinesViewHolder, position: Int) {
        holder.itemHeadlineBinding.article = articles[position]
        holder.itemView.setOnClickListener { handleItemClick(holder.itemView, articles[position]) }
    }

    inner class HeadlinesViewHolder(
        val itemHeadlineBinding: ItemHeadlineBinding
    ) : RecyclerView.ViewHolder(itemHeadlineBinding.root)

    //Method for opening browser on clicking on a recycler-view item
    private fun handleItemClick(
        itemView: View,
        newsArticle: NewsArticle
    ) {
        val uriUrl: Uri? = Uri.parse(newsArticle.url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(context!!, launchBrowser, null)
    }
}