package pl.paullettuce.daznrecruitmenttask.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.paullettuce.daznrecruitmenttask.data.remote.EventsApiService
import pl.paullettuce.daznrecruitmenttask.data.utils.RetrofitInstance

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    fun provideEventsApiService(): EventsApiService =
        RetrofitInstance.retrofit.create(EventsApiService::class.java)

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
        context.getSystemService()!!
}