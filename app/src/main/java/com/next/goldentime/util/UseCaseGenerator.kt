package com.next.goldentime.util

import com.next.goldentime.App
import com.next.goldentime.repository.case.CaseAPIRepository
import com.next.goldentime.repository.disease.DiseaseAPIRepository
import com.next.goldentime.repository.location.LocationFusedRepository
import com.next.goldentime.repository.profile.ProfileStoreRepository
import com.next.goldentime.repository.profile.profileStore
import com.next.goldentime.repository.sos.SOSAPIRepository
import com.next.goldentime.usecase.article.ArticleUseCase
import com.next.goldentime.usecase.patient.PatientUseCase
import com.next.goldentime.usecase.profile.ProfileUseCase
import com.next.goldentime.usecase.rescue.RescueUseCase

fun generatePatientUseCase(): PatientUseCase {
    return PatientUseCase(
        sosRepository = SOSAPIRepository(),
        profileRepository = ProfileStoreRepository(App.context.profileStore),
        locationRepository = LocationFusedRepository(App.context)
    )
}

fun generateRescueUseCase(sosId: Int): RescueUseCase {
    return RescueUseCase(
        sosRepository = SOSAPIRepository(),
        diseaseRepository = DiseaseAPIRepository(),
        caseRepository = CaseAPIRepository(),
        locationRepository = LocationFusedRepository(App.context),
        sosId = sosId
    )
}

fun generateProfileUseCase(): ProfileUseCase {
    return ProfileUseCase(
        profileRepository = ProfileStoreRepository(App.context.profileStore),
        diseaseRepository = DiseaseAPIRepository()
    )
}

fun generateArticleUseCase(): ArticleUseCase {
    return ArticleUseCase(
        diseaseRepository = DiseaseAPIRepository(),
        caseRepository = CaseAPIRepository()
    )
}
