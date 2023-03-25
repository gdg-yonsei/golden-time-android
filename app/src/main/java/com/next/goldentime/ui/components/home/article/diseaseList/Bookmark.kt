package com.next.goldentime.ui.components.home.article.diseaseList

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.repository.disease.Disease
import com.next.goldentime.ui.components.common.button.IconButton
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction1

@Composable
fun Bookmark(
    disease: Disease,
    myDiseases: List<Int>,
    setDiseases: KSuspendFunction1<List<Int>, Unit>
) {
    val composeScope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(false) }

    fun addMyDisease(id: Int) {
        composeScope.launch {
            loading = true
            setDiseases(myDiseases.plus(id))
        }
    }

    fun removeMyDisease(id: Int) {
        composeScope.launch {
            loading = true
            setDiseases(myDiseases.minus(id))
        }
    }

    LaunchedEffect(myDiseases) { loading = false }

    /**
     * Content
     */
    if (loading) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            strokeWidth = 2.dp
        )
    } else {
        when (myDiseases.contains(disease.id)) {
            true ->
                IconButton(icon = Icons.Outlined.Bookmark, size = 24) {
                    removeMyDisease(disease.id)
                }
            false ->
                IconButton(icon = Icons.Outlined.BookmarkBorder, size = 24) {
                    addMyDisease(disease.id)
                }
        }
    }
}