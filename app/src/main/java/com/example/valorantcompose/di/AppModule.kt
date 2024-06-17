package com.example.valorantcompose.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.example.valorantcompose.data.remote.AgentService
import com.example.valorantcompose.data.remote.AgentServiceDetails
import com.example.valorantcompose.data.repository.local.AgentDataStoreRepository
import com.example.valorantcompose.data.repository.local.AgentDataStoreRepositoryImpl
import com.example.valorantcompose.data.repository.remote.AgentRepository
import com.example.valorantcompose.data.repository.remote.AgentRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val gson = Gson()
        val gsonConverterFactory = GsonConverterFactory.create(gson)

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(AgentServiceDetails.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideApiEndpoints(retrofit: Retrofit): AgentService {
        return retrofit.create(AgentService::class.java)
    }

    @Provides
    fun provideRepository(agentService: AgentService): AgentRepository {
        return AgentRepositoryImpl(agentService)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.dataStoreFile("settings.preferences_pb") }
        )
    }

    @Provides
    @Singleton
    fun provideAgentDataStoreRepository(
        dataStore: DataStore<Preferences>
    ): AgentDataStoreRepository {
        return AgentDataStoreRepositoryImpl(dataStore)
    }
}