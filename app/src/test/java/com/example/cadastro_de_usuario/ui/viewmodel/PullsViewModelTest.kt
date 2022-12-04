package com.example.cadastro_de_usuario.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cadastro_de_usuario.data.repository.PullsRepository
import com.example.cadastro_de_usuario.domain.converter.PullsRepoConverter
import com.example.cadastro_de_usuario.domain.vo.ListPullsVO
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class PullsViewModelTest {

    private val repository: PullsRepository = mockk(relaxed = true)
    private val converter: PullsRepoConverter = mockk(relaxed = true)
    private val viewModel = PullsViewModel(repository, converter)

    @ExperimentalCoroutinesApi
    val testDispatcher = UnconfinedTestDispatcher()

    @Before
    @ExperimentalCoroutinesApi
    fun setup() { Dispatchers.setMain(testDispatcher) }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun whenFetchInformation_verifyObserverChangesOther() = runBlocking {
        val observer: Observer<List<ListPullsVO>> = mockk(relaxed = true)

        viewModel.listRepository.observeForever(observer)
        viewModel.fetchInformation("spring-projects", "spring-boot")

        verify { observer.onChanged(any()) }
    }
}
