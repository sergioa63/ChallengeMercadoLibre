package com.example.sergio.challenge.presentation.list

import com.example.sergio.challenge.CorutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListProductosViewModelTest {


    @get: Rule
    private val coroutineTestRule = CorutineTestRule()

    @Before
    fun setUp() {
    }


    @Test
    fun `cargar lista documentos vacia` () = coroutineTestRule.testDispacher.runBlockingTest {

    }
}