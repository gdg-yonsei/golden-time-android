package com.next.goldentime.repository.disease

import com.next.goldentime.data.fixtures.diseaseA
import com.next.goldentime.data.fixtures.diseaseB
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class DiseaseStaticRepository : DiseaseRepository {
    override fun listDiseases() = flow {
        delay(500)

        emit(listOf(diseaseA, diseaseB))
    }

    override fun getDisease(id: Int) = flow {
        delay(500)

        when (id) {
            1 -> emit(diseaseA)
            2 -> emit(diseaseB)
        }
    }
}