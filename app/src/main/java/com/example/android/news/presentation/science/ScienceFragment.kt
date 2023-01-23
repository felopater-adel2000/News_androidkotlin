package com.example.android.news.presentation.science

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
import com.example.android.news.databinding.FragmentScienceBinding
import com.example.android.news.presentation.ArticleAdapter
import com.example.android.news.presentation.OnArticleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScienceFragment : Fragment()
{
    private val viewModel: ScienceViewModel by viewModels()
    private lateinit var binding: FragmentScienceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_science, container, false)

        viewModel.visibilityState.observe(viewLifecycleOwner, Observer { changeState(it) })

        val adapter = ArticleAdapter(OnArticleClickListener { sendUrlIntent(it.url!!) })
        binding.rvScienceArticlesElement.adapter = adapter
        viewModel.article.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
        return binding.root
    }

    private fun changeState(it: State)
    {
        when(it)
        {
            State.Loading -> {
                binding.progressBarScience.visibility = View.VISIBLE
                binding.rvScienceArticlesElement.visibility = View.GONE
            }
            else -> {
                binding.progressBarScience.visibility = View.GONE
                binding.rvScienceArticlesElement.visibility = View.VISIBLE
            }
        }
    }


}