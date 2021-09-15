package com.shaw.kisanseva

import retrofit2.http.GET
import retrofit2.http.Url

interface SchemeService2 {


    @GET
    fun getInfo(@Url url:String):retrofit2.Call<ArrayList<SchemesDesc>>
}