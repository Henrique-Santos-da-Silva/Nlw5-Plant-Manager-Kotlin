package br.com.rocketseat.nextlevelweek.plantmanager.di

import android.content.Context
import androidx.room.Room
import br.com.rocketseat.nextlevelweek.plantmanager.api.PlantApi
import br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.UserDao
import br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.UserDatabase
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
    fun providerRoomDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "users_db.db"
        ).build()

    @Singleton
    @Provides
    fun providerUserDao(userDatabase: UserDatabase): UserDao = userDatabase.userDao()

    @Singleton
    @Provides
    fun providerUserDbRepository(userDao: UserDao): UserDbRepository = UserDbRepository(userDao)

}