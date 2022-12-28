package com.sol.fakestoreapiapp.ui.product_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.sol.fakestoreapiapp.R
import com.sol.fakestoreapiapp.databinding.FragmentProductDetailsBinding
import com.sol.fakestoreapiapp.domain.viewmodel.HomeViewModel
import com.sol.fakestoreapiapp.framework.model.getData
import com.sol.fakestoreapiapp.framework.model.getThrowable
import com.sol.fakestoreapiapp.framework.remote.data.ProductDataResponse
import com.sol.fakestoreapiapp.utils.doAfterImageLoaded
import com.sol.fakestoreapiapp.utils.showLoading
import com.sol.fakestoreapiapp.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var mainBinding: FragmentProductDetailsBinding
    private val mViewModel:HomeViewModel by viewModels()
    private  var  productId:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainBinding = FragmentProductDetailsBinding.inflate(inflater,container,false)
        productId = ProductDetailsFragmentArgs.fromBundle(requireArguments()).productId
        initObserver()
        initActions()
        getProductDetails()

        return mainBinding.root

    }

    private fun initActions() {

        mainBinding.clEmpty.ivReload.setOnClickListener{

            getProductDetails()
            mainBinding.clEmpty.showLoading(false)

        }

    }

    private fun initObserver() {

        mViewModel.productDetails.observe(viewLifecycleOwner){



            val product = it.getData()
            if(it.isSuccessful()&& product != null){

                mainBinding.clEmpty.root.visibility = View.GONE

                updateProductData(product)

            }else if(it.isFailed()){
                mainBinding.clEmpty.root.visibility = View.VISIBLE
                mainBinding.clEmpty.showLoading(true)
                showMessage(mViewModel.getErrorMessage(it.getThrowable()))
            }



        }
    }

    private fun updateProductData(product: ProductDataResponse) {

        mainBinding.tvCategoryValue.text = getString(R.string.str_category,product.category)
        mainBinding.tvTitle.text = product.title
        mainBinding.rate.rating = (product.rating.rate).toFloat()
        mainBinding.rate.isEnabled = false
        mainBinding.tvRateCount.text = getString(R.string.str_rate_count , product.rating.count.toString()  )
        Glide.with(this)
            .load(product.image)
            .doAfterImageLoaded {
                mainBinding.progressBar.visibility = View.GONE
            }.into(mainBinding.imageView)
        mainBinding.tvDescription.text = product.description

        mainBinding.tvPrice.text = getString(R.string.str_amount , String.format(Locale.ENGLISH,"%.2f",product.price ))

    }

    private fun getProductDetails(){

        mViewModel.getProductDetails(productId)

    }



}