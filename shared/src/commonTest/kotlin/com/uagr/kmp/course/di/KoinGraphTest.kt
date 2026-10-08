package com.uagr.kmp.course.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.data.network.datasource.register.RegisterNetworkDataSource
import com.uagr.kmp.course.data.network.datasource.register.RegisterNetworkDataSourceImpl
import com.uagr.kmp.course.data.repository.register.RegisterRepositoryImpl
import com.uagr.kmp.course.domain.repository.register.RegisterRepository
import com.uagr.kmp.course.domain.usecase.login.LoginUseCase
import com.uagr.kmp.course.domain.usecase.register.RegisterUseCase
import com.uagr.kmp.course.domain.usecase.register.ValidateRegisterFormUseCase
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginViewModel
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterViewModel
import com.uagr.kmp.course.presentation.ui.welcome.viewmodel.WelcomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin
import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class KoinGraphTest {
    @Test
    fun generatedApplicationResolvesScreensAndInterfaceBindings() {
        val client = HttpClient(MockEngine { respond("{}") })
        val dataStore = object : DataStore<Preferences> {
            override val data = MutableStateFlow(emptyPreferences())

            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences {
                data.value = transform(data.value)
                return data.value
            }
        }
        try {
            initKoin()
            val koin = getKoin()
            // Replace platform resources so this test needs no Android context or server.
            koin.loadModules(listOf(module {
                single<HttpClient> { client }
                single<DataStore<Preferences>> { dataStore }
            }))

            assertIs<RegisterNetworkDataSourceImpl>(koin.get<RegisterNetworkDataSource>())
            assertIs<RegisterRepositoryImpl>(koin.get<RegisterRepository>())
            assertIs<RegisterUseCase>(koin.get<RegisterUseCase>())
            assertIs<ValidateRegisterFormUseCase>(koin.get<ValidateRegisterFormUseCase>())
            assertIs<LoginUseCase>(koin.get<LoginUseCase>())
            assertIs<RegisterViewModel>(koin.get<RegisterViewModel>())
            assertIs<LoginViewModel>(koin.get<LoginViewModel>())
            assertIs<WelcomeViewModel>(koin.get<WelcomeViewModel>())
            assertNotSame(koin.get<RegisterRepository>(), koin.get<RegisterRepository>())
            assertNotSame(koin.get<RegisterNetworkDataSource>(), koin.get<RegisterNetworkDataSource>())
            assertSame(koin.get<AppDataStore>(), koin.get<AppDataStore>())
            assertSame(koin.get<CoroutineDispatcher>(), koin.get<CoroutineDispatcher>())
        } finally {
            stopKoin()
            client.close()
        }
    }
}
