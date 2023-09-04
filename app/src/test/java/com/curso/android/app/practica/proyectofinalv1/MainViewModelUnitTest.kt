package com.curso.android.app.practica.proyectofinalv1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.curso.android.app.practica.proyectofinalv1.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModelUnitTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialValue() = runTest {
        val text1Value = viewModel.textCompare.value?.text1
        assertEquals("", text1Value)

        val text2Value = viewModel.textCompare.value?.text2
        assertEquals("", text2Value)

        val resultValue = viewModel.textCompare.value?.result
        assertEquals("", resultValue)
    }

    @Test
    fun mainViewModel_EmptyInputCompare() = runTest {
        launch {
            val text1 = (viewModel.textCompare.value?.text1 ?: "")
            val text2 = (viewModel.textCompare.value?.text2 ?: "")
            viewModel.compareInputs(text1, text2)
        }

        advanceUntilIdle()
        val value = viewModel.textCompare.value?.result
        assertEquals("Resultado: No se han ingresado datos para comparar", value)
    }

    @Test
    fun mainViewModel_DifferentInputCompare() = runTest {
        launch {
            viewModel.textCompare.value?.text1 = "Mi texto 1"
            val text1 = (viewModel.textCompare.value?.text1 ?: "")
            val text2 = (viewModel.textCompare.value?.text2 ?: "")
            viewModel.compareInputs(text1, text2)
        }

        advanceUntilIdle()
        val value = viewModel.textCompare.value?.result
        assertEquals("Resultado: Los textos son diferentes.", value)
    }

    @Test
    fun mainViewModel_EqualsInputCompare() = runTest {
        launch {
            viewModel.textCompare.value?.text1 = "Mi texto"
            viewModel.textCompare.value?.text2 = "Mi texto"
            val text1 = (viewModel.textCompare.value?.text1 ?: "")
            val text2 = (viewModel.textCompare.value?.text2 ?: "")
            viewModel.compareInputs(text1, text2)
        }

        advanceUntilIdle()
        val value = viewModel.textCompare.value?.result
        assertEquals("Resultado: Los textos son iguales.", value)
    }
}

