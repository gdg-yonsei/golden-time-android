package com.next.goldentime.repository.disease

import com.next.goldentime.data.fixtures.diseaseA
import com.next.goldentime.data.fixtures.diseaseB
import kotlinx.coroutines.flow.flow

class DiseaseStaticRepository : DiseaseRepository {
    override fun getDiseases(ids: List<Int>?) = flow {
        emit(listOf(diseaseA, diseaseB))
    }

    override fun getDisease(id: Int) = flow {
        when (id) {
            1 -> emit(diseaseA)
            2 -> emit(diseaseB)
        }
    }

    override fun getManual(diseaseId: Int) = flow {
        when (diseaseId) {
            1 -> emit(diseaseA.manual)
            2 -> emit(diseaseB.manual)
        }
    }
}