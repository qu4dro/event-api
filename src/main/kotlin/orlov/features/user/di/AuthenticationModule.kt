package orlov.features.user.di

import org.koin.dsl.bind
import org.koin.dsl.module
import orlov.features.user.db.UserDao
import orlov.features.user.db.UserDaoImpl
import orlov.features.user.repository.UserRepository
import orlov.features.user.repository.UserRepositoryImpl


val authenticationModule = module {
    single { UserDaoImpl(get()) } bind UserDao::class
    single { UserRepositoryImpl(get()) } bind UserRepository::class
}