package br.com.rocketseat.nextlevelweek.plantmanager.di

import android.content.Context
import androidx.room.Room
import br.com.rocketseat.nextlevelweek.plantmanager.api.PlantApi
import br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.migrations.PlantAppDbMigrations
import br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb.PlantDao
import br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb.PlantDatabase
import br.com.rocketseat.nextlevelweek.plantmanager.repositories.PlantDbRepository
import br.com.rocketseat.nextlevelweek.plantmanager.repositories.PlantManagerRepository
import br.com.rocketseat.nextlevelweek.plantmanager.repositories.UserDbRepository
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object PlantManagerModule {

    private const val BASE_URL = "http://192.168.0.216:3000/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.HOURS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): PlantApi = retrofit.create(PlantApi::class.java)

    @Singleton
    @Provides
    fun providesRepository(apiService: PlantApi): PlantManagerRepository = PlantManagerRepository(apiService)

    @Singleton
    @Provides
    fun providerUserDbRepository(@ApplicationContext context: Context): UserDbRepository = UserDbRepository(context)

    @Singleton
    @Provides
    fun providerPlantRoom(@ApplicationContext context: Context): PlantDatabase =
        Room.databaseBuilder(
            context,
            PlantDatabase::class.java,
            "plants_db.db"
        ).addMigrations(PlantAppDbMigrations.migration2To3).build()

    @Singleton
    @Provides
    fun providerPlantDao(plantDatabase: PlantDatabase): PlantDao = plantDatabase.plantDao()

    @Singleton
    @Provides
    fun providerPlantDbRepository(plantDao: PlantDao): PlantDbRepository = PlantDbRepository(plantDao)
}