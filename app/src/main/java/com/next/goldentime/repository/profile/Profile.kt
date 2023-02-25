package com.next.goldentime.repository.profile

import java.util.*

data class Profile(
    val name: String = UUID.randomUUID().toString()
)