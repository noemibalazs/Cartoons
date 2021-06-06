package com.urban.androidhomework.di

import com.urban.androidhomework.landing.MainActivity
import com.urban.androidhomework.app.UrbanHomeWorkApp
import com.urban.androidhomework.details.CharacterDetailActivity
import com.urban.androidhomework.details.CharacterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class, PreferencesModule::class])
interface AppComponent {

    fun inject(app: UrbanHomeWorkApp)

    fun inject(mainActivity: MainActivity)

    fun inject(characterDetailActivity: CharacterDetailActivity)

    fun inject(characterFragment: CharacterFragment)
}