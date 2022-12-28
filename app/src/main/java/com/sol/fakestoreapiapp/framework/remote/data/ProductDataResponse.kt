package com.sol.fakestoreapiapp.framework.remote.data

import java.io.Serializable

data class ProductDataResponse(

    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String

):Serializable{

}