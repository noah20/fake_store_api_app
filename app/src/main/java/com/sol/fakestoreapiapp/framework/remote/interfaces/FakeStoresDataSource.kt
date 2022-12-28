package com.sol.fakestoreapiapp.framework.remote.interfaces

import com.sol.fakestoreapiapp.framework.remote.data.ProductDataResponse


interface FakeStoresDataSource {

    suspend fun getProducts() :List<ProductDataResponse>

    suspend fun getProductDetails(productId:Int) :ProductDataResponse


}