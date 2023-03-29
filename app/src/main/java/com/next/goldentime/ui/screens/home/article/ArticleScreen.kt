package com.next.goldentime.ui.screens.home.article

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.home.article.articleList.ArticleListScreen
import com.next.goldentime.ui.screens.home.article.caseDetail.CaseDetailScreen
import com.next.goldentime.ui.screens.home.article.caseList.CaseListScreen
import com.next.goldentime.ui.screens.home.article.diseaseDetail.DiseaseDetailScreen
import com.next.goldentime.ui.screens.home.article.diseaseList.DiseaseListScreen

@Composable
fun ArticleScreen(model: ArticleViewModel = viewModel()) {
    val navController = rememberNavController()

    fun navigateToDiseaseList() {
        navController.navigate(ArticleScreen.DiseaseList.route)
    }

    fun navigateToDiseaseDetail(diseaseId: Int) {
        navController.navigate(
            ArticleScreen.DiseaseDetail.route.replace(
                "{diseaseId}",
                "$diseaseId"
            )
        )
    }

    fun navigateToCaseList() {
        navController.navigate(ArticleScreen.CaseList.route)
    }

    fun navigateToCaseDetail(caseId: Int) {
        navController.navigate(
            ArticleScreen.CaseDetail.route.replace(
                "{caseId}",
                "$caseId"
            )
        )
    }

    fun navigateBack() {
        navController.navigateUp()
    }

    /**
     * Content
     */
    NavHost(navController = navController, startDestination = ArticleScreen.ArticleList.route) {
        composable(ArticleScreen.ArticleList.route) {
            ArticleListScreen(
                showDiseaseList = ::navigateToDiseaseList,
                showCaseList = ::navigateToCaseList
            )
        }
        composable(ArticleScreen.DiseaseList.route) {
            DiseaseListScreen(
                navigateBack = ::navigateBack,
                showDetail = ::navigateToDiseaseDetail,
            )
        }
        composable(ArticleScreen.DiseaseDetail.route) {
            val diseaseId = it.arguments?.getString("diseaseId")?.toInt() ?: 0

            DiseaseDetailScreen(
                diseaseId = diseaseId,
                navigateBack = ::navigateBack,
                showCaseDetail = ::navigateToCaseDetail,
            )
        }
        composable(ArticleScreen.CaseList.route) {
            CaseListScreen(
                navigateBack = ::navigateBack,
                showDetail = ::navigateToCaseDetail,
            )
        }
        composable(ArticleScreen.CaseDetail.route) {
            val caseId = it.arguments?.getString("caseId")?.toInt() ?: 0

            CaseDetailScreen(
                caseId = caseId,
                navigateBack = ::navigateBack
            )
        }
    }
}

private sealed class ArticleScreen(val route: String) {
    object ArticleList : ArticleScreen("article")
    object DiseaseList : ArticleScreen("article/disease")
    object DiseaseDetail : ArticleScreen("article/disease/{diseaseId}")
    object CaseList : ArticleScreen("article/case")
    object CaseDetail : ArticleScreen("article/case/{caseId}")
}