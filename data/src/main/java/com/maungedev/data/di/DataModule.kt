package com.maungedev.data.di

import androidx.room.Room
import com.maungedev.data.repository.AuthRepositoryImpl
import com.maungedev.data.repository.EventRepositoryImpl
import com.maungedev.data.repository.UserRepositoryImpl
import com.maungedev.data.source.local.EventTechDatabase
import com.maungedev.data.source.local.LocalDataSource
import com.maungedev.data.source.remote.RemoteDataSource
import com.maungedev.data.source.remote.service.AuthService
import com.maungedev.data.source.remote.service.EventService
import com.maungedev.data.source.remote.service.UserService
import com.maungedev.domain.repository.AuthRepository
import com.maungedev.domain.repository.EventRepository
import com.maungedev.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            EventTechDatabase::class.java,
            "EventTech.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
    factory {
        get<EventTechDatabase>().userDao()
    }
    factory {
        get<EventTechDatabase>().eventDao()
    }

}

val serviceModule = module {
    factory {
        AuthService()
    }
    factory {
        EventService()
    }
    factory {
        UserService()
    }
}

val dataSourceModule = module {
    single {
        LocalDataSource(get(),get())
    }
    single {
        RemoteDataSource(get(),get(),get())
    }
}

val repositoryModule = module {
    single<AuthRepository>{
        AuthRepositoryImpl(get(),get())
    }
    single<EventRepository>{
        EventRepositoryImpl(get(),get())
    }
    single<UserRepository>{
        UserRepositoryImpl(get(),get())
    }
}