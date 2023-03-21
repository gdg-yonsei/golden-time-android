package com.next.goldentime.data.fixtures

import com.next.goldentime.repository.profile.Profile

val profileA = Profile(
    name = "A",
    birthDate = "2000",
    height = 180,
    weight = 75,
    bloodType = "A",
    allergies = "",
    medications = "",
    medicalNotes = "",
    diseases = listOf(1)
)

val profileB = Profile(
    name = "B",
    birthDate = "2000",
    height = 160,
    weight = 48,
    bloodType = "O",
    allergies = "",
    medications = "",
    medicalNotes = "",
    diseases = listOf(2)
)