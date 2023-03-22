package com.next.goldentime.util

import com.next.goldentime.App
import com.next.goldentime.repository.case.CaseStaticRepository
import com.next.goldentime.repository.disease.DiseaseStaticRepository
import com.next.goldentime.repository.profile.ProfileStoreRepository
import com.next.goldentime.repository.profile.profileStore
import com.next.goldentime.repository.sos.SOSStaticRepository
import com.next.goldentime.usecase.article.ArticleUseCase
import com.next.goldentime.usecase.patient.PatientUseCase
import com.next.goldentime.usecase.profile.ProfileUseCase
import com.next.goldentime.usecase.rescue.RescueUseCase

fun generatePatientUseCase(): PatientUseCase {
    return PatientUseCase(
        sosRepository = SOSStaticRepository(),
        profileRepository = ProfileStoreRepository(App.context.profileStore)
    )
}

fun generateRescueUseCase(sosId: Int): RescueUseCase {
    return RescueUseCase(
        sosRepository = SOSStaticRepository(),
        diseaseRepository = DiseaseStaticRepository(),
        caseRepository = CaseStaticRepository(),
        sosId = sosId
    )
}

fun generateProfileUseCase(): ProfileUseCase {
    return ProfileUseCase(
        profileRepository = ProfileStoreRepository(App.context.profileStore),
        diseaseRepository = DiseaseStaticRepository()
    )
}

fun generateArticleUseCase(): ArticleUseCase {
    return ArticleUseCase(
        diseaseRepository = DiseaseStaticRepository(),
        caseRepository = CaseStaticRepository()
    )
}