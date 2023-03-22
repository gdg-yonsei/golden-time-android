package com.next.goldentime.ui.screens.home.article.caseDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.usecase.article.ArticleUseCase
import com.next.goldentime.util.generateArticleUseCase

class CaseDetailViewModel(
    private val caseId: Int,
    private val articleUseCase: ArticleUseCase = generateArticleUseCase()
) : ViewModel() {
    private val _case = articleUseCase.getCase(caseId)

    val case = _case.asLiveData()
}

class CaseDetailViewModelFactory(
    private val caseId: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CaseDetailViewModel::class.java)) {
            return CaseDetailViewModel(caseId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}