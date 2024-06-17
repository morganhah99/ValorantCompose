package com.example.valorantcompose.di

import com.example.valorantcompose.data.remote.ValorantService
import com.example.valorantcompose.data.remote.ValorantServiceDetails
import com.example.valorantcompose.data.repository.AgentRepository
import com.example.valorantcompose.data.repository.AgentRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            .baseUrl(ValorantServiceDetails.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideApiEndpoints(retrofit: Retrofit): ValorantService {
        return retrofit.create(ValorantService::class.java)
    }

    @Provides
    fun provideRepository(valorantService: ValorantService): AgentRepository {
        return AgentRepositoryImpl(valorantService)
    }
}