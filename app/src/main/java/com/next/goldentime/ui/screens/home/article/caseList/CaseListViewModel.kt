package com.next.goldentime.ui.screens.home.article.caseList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.next.goldentime.usecase.article.ArticleUseCase
import com.next.goldentime.util.generateArticleUseCase

class CaseListViewModel(
    private val articleUseCase: ArticleUseCase = generateArticleUseCase()
) : ViewModel() {
    private val _cases = articleUseCase.listCases()

    val cases = _cases.asLiveData()
}