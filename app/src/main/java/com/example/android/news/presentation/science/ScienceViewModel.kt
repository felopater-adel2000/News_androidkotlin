package com.example.android.news.presentation.science

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.news.app.State
import com.example.android.news.domain.Article
import com.example.android.news.domain.usrcase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScienceViewModel @Inject constructor(val useCase: NewsUseCase) : ViewModel()
{
    private val _visibilityState = MutableLiveData<State>()
    val visibilityState: LiveData<State>
        get() = _visibilityState

    private val _articles = MutableLiveData<List<Article>>()
    val article: LiveData<List<Article>>
        get() = _articles

    init {
        viewModelScope.launch {
            _visibilityState.value = State.Loading

            _articles.value = useCase.scienceArticles()
            _visibilityState.value = State.Success

        }
    }
}