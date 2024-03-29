/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = " https://android-kotlin-fun-mars-server.appspot.com/"
private const val BASE_USER_URL = "http://10.0.2.2:3000/"


/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
private val moshiUser = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

private val retrofitUser = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshiUser))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_USER_URL)
        .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface MarsApiService {
    /**
     * Returns a Coroutine [Deferred] [List] of [Mars1Property] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("realestate")
    fun getProperties():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<List<Mars1Property>>
}

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

    //all user Profiles
    @POST("user_profiles")
    fun createUserProfile(@Body request: RequestBody): Deferred<UserProperty>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MarsApi {
    val retrofitService : MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
    val retrofitUserService : UserApiService by lazy { retrofitUser.create(UserApiService::class.java) }
}
