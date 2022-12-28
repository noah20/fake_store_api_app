package com.sol.fakestoreapiapp.framework.repo

import com.sol.fakestoreapiapp.framework.remote.data.ProductDataResponse
import com.sol.fakestoreapiapp.framework.remote.interfaces.FakeStoreRepository
import com.sol.fakestoreapiapp.framework.remote.services.NetworkService
import javax.inject.Inject


class FakeStoreRepositoryImp @Inject constructor(private  val remote:NetworkService): FakeStoreRepository {

    override suspend fun getProducts(): List<ProductDataResponse> {

        return remote.getProducts()
    }

    override suspend fun getProductDetails(id: Int): ProductDataResponse {

        return remote.getProductDetails(id)

    }


}