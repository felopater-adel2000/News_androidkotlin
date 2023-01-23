package com.example.android.news.presentation.sports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.android.news.R
import com.example.android.news.app.State
import com.example.android.news.app.sendUrlIntent
import com.example.android.news.databinding.FragmentSportsBinding
import com.example.android.news.presentation.ArticleAdapter
import com.example.android.news.presentation.OnArticleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsFragment : Fragment()
{
    private val viewModel: SportsViewModel by viewModels()
    lateinit var binding: FragmentSportsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sports, container, false)


        viewModel.visibilityState.observe(viewLifecycleOwner, Observer { changeState(it)})

        val adapter = ArticleAdapter(OnArticleClickListener {sendUrlIntent(it.url!!)})
        binding.rvSportsArticlesElement.adapter = adapter
        viewModel.articles.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })

        return binding.root
    }

    private fun changeState(state: State)
    {
        when(state)
        {
            State.Loading -> {
                binding.progressBarSports.visibility = View.VISIBLE
                binding.rvSportsArticlesElement.visibility = View.GONE
            }
            else -> {
                binding.progressBarSports.visibility = View.GONE
                binding.rvSportsArticlesElement.visibility = View.VISIBLE
            }
        }
    }


}