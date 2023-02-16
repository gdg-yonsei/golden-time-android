package com.next.goldentime.model.profile

import java.util.UUID

data class Profile(
    val name: String = UUID.randomUUID().toString()
)