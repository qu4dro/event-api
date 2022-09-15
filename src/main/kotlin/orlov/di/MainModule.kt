package orlov.di

import org.koin.dsl.bind
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import orlov.di.DatabaseInfo.dbName
import orlov.di.DatabaseInfo.mongoPassword
import orlov.security.hashing.HashingService
import orlov.security.hashing.SHA256HashingService
import orlov.security.token.JwtTokenService
import orlov.security.token.TokenService

object DatabaseInfo {
    val mongoPassword: String = System.getenv("MONGO_PASSWORD")
    val dbName = "event"
}

val mainModule = module {
    single {
        KMongo.createClient(
            connectionString = "mongodb+srv://peterorlov:${mongoPassword}@cluster0.msoqxar.mongodb.net/${dbName}?retryWrites=true&w=majority"
        ).coroutine.getDatabase(dbName)
    }
    single { SHA256HashingService() } bind HashingService::class
    single { JwtTokenService() } bind TokenService::class

}