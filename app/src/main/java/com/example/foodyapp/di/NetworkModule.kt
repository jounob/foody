package com.example.foodyapp.di

import com.example.foodyapp.data.network.FoodRecipesApi
import com.example.foodyapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): FoodRecipesApi {
        return retrofit.create(FoodRecipesApi::class.java)
    }

//    @Singleton
//    @Provides
//    fun providesRepository(recipeDataSource: RemoteDataSource): Repository{
//        return Repository(recipeDataSource)
//    }

//    @Singleton
//    @Provides
//    fun provideDataSource()
}

//@Module
//@InstallIn(SingletonComponent::class)
//class NetworkModule {
//    @Provides
//    fun provideHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .readTimeout(15, TimeUnit.SECONDS)
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .build()
//    }
//
//    @Provides
//    fun provideConverterFactory(): GsonConverterFactory {
//        return GsonConverterFactory.create()
//    }
//
//    @FoodyApp
//    @Provides
//    fun provideRetrofitInstance(
//        okHttpClient: OkHttpClient,
//        gsonConverterFactory: GsonConverterFactory
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(gsonConverterFactory)
//            .build()
//    }
//
//    @Provides
//    fun provideApiService(retrofit: Retrofit): FoodRecipesApi {
//        return retrofit.create(FoodRecipesApi::class.java)
//    }
//
//        @Provides
//        fun providesRepository(recipeDataSource: RemoteDataSource): Repository{
//            return Repository(recipeDataSource)
//        }
//
//        @Provides
//        fun provideDataSource(service: FoodRecipesApi) : RemoteDataSource{
//            return RemoteDataSource(service)
//        }
//}
//
