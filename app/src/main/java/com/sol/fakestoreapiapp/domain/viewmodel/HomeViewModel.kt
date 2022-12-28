package com.sol.fakestoreapiapp.domain.viewmodel

import androidx.lifecycle.*
import com.sol.fakestoreapiapp.framework.model.ResultWrapper
import com.sol.fakestoreapiapp.framework.model.getThrowable
import com.sol.fakestoreapiapp.framework.remote.data.ProductDataResponse
import com.sol.fakestoreapiapp.framework.remote.interfaces.FakeStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo:FakeStoreRepository):ViewModel() {

    private val _products = MutableLiveData<ResultWrapper<List<ProductDataResponse>>>()
    val products : LiveData<ResultWrapper<List<ProductDataResponse>>> = _products

    private val _productDetails = MutableLiveData<ResultWrapper<ProductDataResponse>>()
    val productDetails : LiveData<ResultWrapper<ProductDataResponse>> = _productDetails


    fun getProducts() {

        viewModelScope.launch(Dispatchers.IO) {
            _products.postValue(ResultWrapper.Loading)
            try {

                val data = repo.getProducts()
                _products.postValue(ResultWrapper.Success(data))

            }catch (e:Exception){

                _products.postValue(ResultWrapper.Error(e))

            }
        }

    }



    fun getProductDetails(productId:Int){

        viewModelScope.launch(Dispatchers.IO) {
            _productDetails.postValue(ResultWrapper.Loading)
            try {

                val data = repo.getProductDetails(productId)
                _productDetails.postValue(ResultWrapper.Success(data))

            }catch (e:Exception){

                _productDetails.postValue(ResultWrapper.Error(e))

            }
        }


    }

    fun getErrorMessage(throwable: Throwable?): String {

        return "SomeThing Went Wrong..! ${throwable?.message}"

    }


}