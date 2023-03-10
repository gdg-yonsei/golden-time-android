package com.next.goldentime.data.fixtures

import com.next.goldentime.repository.disease.Disease
import com.next.goldentime.repository.disease.Step

val diseaseA = Disease(
    id = 1,
    title = "Disease A",
    subtitle = "Disease A Subtitle",
    overview = "Disease A Overview",
    manual = listOf(
        Step(
            title = "First manual for A",
            description = "First description for A",
        ),
        Step(
            title = "Second manual for A",
            description = "Second description for A",
        ),
    )
)

val diseaseB = Disease(
    id = 2,
    title = "Disease B",
    subtitle = "Disease B Subtitle",
    overview = "Disease B Overview",
    manual = listOf(
        Step(
            title = "First manual for B",
            description = "First description for B",
        ),
        Step(
            title = "Second manual for B",
            description = "Second description for B",
        ),
    )
)