package com.next.goldentime.data.fixtures

import com.next.goldentime.repository.disease.Disease

val diseaseA = Disease(
    id = 1,
    title = "Disease A",
    subtitle = "Disease A Subtitle",
    description = "Disease A Description",
    cases = listOf(caseASimplified, caseBSimplified)
)

val diseaseB = Disease(
    id = 2,
    title = "Disease B",
    subtitle = "Disease B Subtitle",
    description = "Disease B Overview",
    cases = listOf(caseASimplified, caseBSimplified)
)