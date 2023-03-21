package com.next.goldentime.data.fixtures

import com.next.goldentime.repository.case.Case
import com.next.goldentime.repository.case.SimpleCase
import com.next.goldentime.repository.case.Step

val caseA = Case(
    id = 1,
    title = "Case A",
    subtitle = "Case A Subtitle",
    overview = "Case A Description",
    symptoms = listOf("Symptom A", "Symptom B"),
    causes = "Case A Causes and Preventions",
    manual = listOf(
        Step(
            title = "First step for A",
            description = "First step description for A",
        ),
        Step(
            title = "Second step for A",
            description = "Second step description for A",
            videoUrl = "https://youtu.be/UGE13GR9_CU"
        ),
    )
)

val caseASimplified = SimpleCase(
    id = 1,
    title = "Case A",
    subtitle = "Case A Subtitle",
)

val caseB = Case(
    id = 2,
    title = "Case B",
    subtitle = "Case B Subtitle",
    overview = "Case B Description",
    symptoms = listOf("Symptom C", "Symptom D"),
    causes = "Case B Causes and Preventions",
    manual = listOf(
        Step(
            title = "First step for B",
            description = "First step description for B",
        ),
        Step(
            title = "Second step for B",
            description = "Second step description for B",
            videoUrl = "https://youtu.be/UGE13GR9_CU"
        ),
    )
)

val caseBSimplified = SimpleCase(
    id = 1,
    title = "Case B",
    subtitle = "Case B Subtitle",
)