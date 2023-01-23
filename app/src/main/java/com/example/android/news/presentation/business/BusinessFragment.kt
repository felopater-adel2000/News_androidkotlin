package com.example.android.news.presentation.business

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
import com.example.android.news.databinding.FragmentBusinessBinding
import com.example.android.news.presentation.ArticleAdapter
import com.example.android.news.presentation.OnArticleClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BusinessFragment : Fragment()
{

    private val viewModel: BusinessViewModel by viewModels()
    private lateinit var binding: FragmentBusinessBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_business, container, false)
        binding.viewModel = viewModel

        viewModel.visibilityState.observe(viewLifecycleOwner, Observer { changeState(it)})

        val adapter = ArticleAdapter(OnArticleClickListener {sendUrlIntent(it.url!!)})
        binding.rvBusinessArticlesElement.adapter = adapter
        viewModel.articles.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })

        return binding.root
    }

    private fun changeState(state: State)
    {
        when(state)
        {
            State.Loading -> {
                binding.progressBarBusiness.visibility = View.VISIBLE
                binding.rvBusinessArticlesElement.visibility = View.GONE
            }
            else -> {
                binding.progressBarBusiness.visibility = View.GONE
                binding.rvBusinessArticlesElement.visibility = View.VISIBLE
            }
        }
    }


}