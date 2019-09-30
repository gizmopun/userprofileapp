package com.example.android.marsrealestate.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_USER_URL = "http://10.0.2.2:3000/"

private val moshiUser = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofitUser = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshiUser))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_USER_URL)
        .build()

interface UserApiService {
    /**
     * Returns a Coroutine [Deferred] [List] of [Mars1Property] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("user_profiles")
    fun getUsers():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<List<UserProperty>>

}

object UserApi {
    val retrofitUserService : UserApiService by lazy { retrofitUser.create(UserApiService::class.java) }
}