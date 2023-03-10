package com.next.goldentime.data.fixtures

import com.next.goldentime.repository.user.User

val userA = User(
    name = "A",
    birthDate = "2000",
    height = 180.0,
    weight = 75.0,
    bloodType = "A",
    allergies = "",
    medications = "",
    medicalNotes = "",
    diseases = listOf(1)
)

val userB = User(
    name = "B",
    birthDate = "2000",
    height = 180.0,
    weight = 75.0,
    bloodType = "O",
    allergies = "",
    medications = "",
    medicalNotes = "",
    diseases = listOf(2)
)