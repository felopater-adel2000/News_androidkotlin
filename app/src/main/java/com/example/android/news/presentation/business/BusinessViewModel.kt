package com.example.android.news.presentation.business

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.news.app.State
import com.example.android.news.app.printMessage
import com.example.android.news.domain.Article
import com.example.android.news.domain.usrcase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
@HiltViewModel
class BusinessViewModel @Inject constructor(val useCase: NewsUseCase) : ViewModel()
{
    private val _visibilityState = MutableLiveData<State>()
    val visibilityState: LiveData<State> = _visibilityState

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    init {
        viewModelScope.launch {
            _visibilityState.value = State.Loading
            _articles.value = useCase.businessArticles()
            _visibilityState.value = State.Success
        }
    }

}