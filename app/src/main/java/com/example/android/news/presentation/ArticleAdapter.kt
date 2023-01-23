package com.example.android.news.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.news.databinding.ArticleElementBinding
import com.example.android.news.domain.Article
import com.example.android.news.generated.callback.OnClickListener

class ArticleAdapter(val onArticleClickListener: OnArticleClickListener) : ListAdapter<Article, RecyclerView.ViewHolder>(ArticleDiffUtil())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        return ArticleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        (holder as ArticleViewHolder).bind(getItem(position), onArticleClickListener)
    }
}

class ArticleDiffUtil : DiffUtil.ItemCallback<Article>()
{
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean
    {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean
    {
        return oldItem.url.equals(newItem.url)
    }

}

class ArticleViewHolder(val binding: ArticleElementBinding) : RecyclerView.ViewHolder(binding.root)
{
    companion object{
        fun create(parent: ViewGroup): ArticleViewHolder
        {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ArticleElementBinding.inflate(inflater, parent, false)
            return ArticleViewHolder(binding)
        }
    }

    fun bind(article: Article, onClickListener: OnArticleClickListener)
    {
        binding.article = article
        binding.onClickArticle = onClickListener
        binding.executePendingBindings()
    }
}

class OnArticleClickListener(val clickListener: (article: Article) -> Unit)
{
    fun onClick(article: Article)
    {
        clickListener(article)
    }
}