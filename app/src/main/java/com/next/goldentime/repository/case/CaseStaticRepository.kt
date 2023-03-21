package com.next.goldentime.repository.case

import com.next.goldentime.data.fixtures.caseA
import com.next.goldentime.data.fixtures.caseB
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class CaseStaticRepository : CaseRepository {
    override fun listCases() = flow {
        delay(500)

        emit(listOf(caseA, caseB))
    }

    override fun getCase(id: Int) = flow {
        delay(500)

        when (id) {
            1 -> emit(caseA)
            2 -> emit(caseB)
        }
    }
}