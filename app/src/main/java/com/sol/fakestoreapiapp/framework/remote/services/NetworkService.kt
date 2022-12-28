package com.sol.fakestoreapiapp.framework.remote.services

import com.sol.fakestoreapiapp.framework.remote.data.ProductDataResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {

    @GET("products")
    suspend fun getProducts() : List<ProductDataResponse>

    @GET("products/{product_id}")
    suspend fun getProductDetails (@Path("product_id") id:Int) : ProductDataResponse

}