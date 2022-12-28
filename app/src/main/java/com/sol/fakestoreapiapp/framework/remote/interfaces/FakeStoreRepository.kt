package com.sol.fakestoreapiapp.framework.remote.interfaces

import com.sol.fakestoreapiapp.framework.remote.data.ProductDataResponse

interface FakeStoreRepository {

    suspend fun getProducts() : List<ProductDataResponse>

    suspend fun getProductDetails(id:Int) :ProductDataResponse

}