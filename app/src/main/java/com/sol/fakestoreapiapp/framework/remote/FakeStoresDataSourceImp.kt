package com.sol.fakestoreapiapp.framework.remote

import com.sol.fakestoreapiapp.framework.remote.data.ProductDataResponse
import com.sol.fakestoreapiapp.framework.remote.interfaces.FakeStoresDataSource
import com.sol.fakestoreapiapp.framework.remote.services.NetworkService
import javax.inject.Inject


class FakeStoresDataSourceImp @Inject constructor(private val networkService:NetworkService): FakeStoresDataSource {

    override suspend fun getProducts(): List<ProductDataResponse> {

        return networkService.getProducts()
    }


    override suspend fun getProductDetails(productId:Int): ProductDataResponse {

        return networkService.getProductDetails(productId)

    }


}