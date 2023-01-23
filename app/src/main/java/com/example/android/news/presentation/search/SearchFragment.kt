package com.example.android.news.presentation.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.news.R
import com.example.android.news.app.State
import com.example.android.news.app.sendUrlIntent
import com.example.android.news.databinding.FragmentSearchBinding
import com.example.android.news.presentation.ArticleAdapter
import com.example.android.news.presentation.MainActivity
import com.example.android.news.presentation.OnArticleClickListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment()
{

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.viewModel = viewModel
        val adapter = ArticleAdapter(OnArticleClickListener { sendUrlIntent(it.url!!) })
        binding.rvSearched.adapter = adapter

        viewModel.visibilityState.observe(viewLifecycleOwner, Observer { changeState(it) })
        viewModel.articles.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })



        return binding.root
    }

    private fun changeState(state: State)
    {
        when(state)
        {
            State.Loading -> {
                binding.pbSearch.visibility = View.VISIBLE
                binding.rvSearched.visibility = View.GONE
            }
            else -> {
                binding.pbSearch.visibility = View.GONE
                binding.rvSearched.visibility = View.VISIBLE
            }
        }
    }

}