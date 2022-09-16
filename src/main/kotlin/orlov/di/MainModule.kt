package orlov.di

import org.koin.dsl.bind
import org.koin.dsl.module
import orlov.data.users.UsersService
import orlov.data.users.UsersServiceImpl
import orlov.security.hashing.HashingService
import orlov.security.hashing.SHA256HashingService
import orlov.security.token.JwtTokenService
import orlov.security.token.TokenService

val mainModule = module {
    single { SHA256HashingService() } bind HashingService::class
    single { JwtTokenService() } bind TokenService::class
    single { UsersServiceImpl() } bind UsersService::class

}