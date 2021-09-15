package com.shaw.kisanseva

import retrofit2.http.GET


interface SchemeService {
    @GET("agrititle")
    fun getTitle():retrofit2.Call<ArrayList<Schemes>>
}