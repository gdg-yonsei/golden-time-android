package com.next.goldentime.data

import com.next.goldentime.repository.disease.Article

private val articleFixtureA = Article(
    1,
    "Article A",
    "Subtitle for article a",
    "This is overview for article a",
    listOf("Do 1", "Do 2")
)

private val articleFixtureB = Article(
    2,
    "Article B",
    "Subtitle for article b",
    "This is overview for article b",
    listOf("Do 1", "Do 2")
)

private val articleFixtureC = Article(
    3,
    "Article C",
    "Subtitle for article c",
    "This is overview for article c",
    listOf("Do 1", "Do 2")
)

val articleFixtures = listOf(articleFixtureA, articleFixtureB, articleFixtureC)