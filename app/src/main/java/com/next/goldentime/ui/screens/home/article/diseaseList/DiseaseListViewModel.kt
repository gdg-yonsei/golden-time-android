package com.next.goldentime.ui.screens.home.article.diseaseList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.next.goldentime.usecase.article.ArticleUseCase
import com.next.goldentime.usecase.profile.ProfileUseCase
import com.next.goldentime.util.generateArticleUseCase
import com.next.goldentime.util.generateProfileUseCase
import kotlinx.coroutines.flow.map

class DiseaseListViewModel(
    private val articleUseCase: ArticleUseCase = generateArticleUseCase(),
    private val profileUseCase: ProfileUseCase = generateProfileUseCase()
) : ViewModel() {
    private val _diseases = articleUseCase.listDiseases()
    private val _myDiseases = profileUseCase.watchDiseases()

    val diseases = _diseases.asLiveData()
    val myDiseases = _myDiseases.map { diseases -> diseases.map { it.id } }.asLiveData()

    val setDiseases = profileUseCase::setDiseases
}