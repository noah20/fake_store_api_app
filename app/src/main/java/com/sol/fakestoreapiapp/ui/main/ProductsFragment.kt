package com.sol.fakestoreapiapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sol.fakestoreapiapp.databinding.FragmentProductsBinding
import com.sol.fakestoreapiapp.domain.viewmodel.HomeViewModel
import com.sol.fakestoreapiapp.framework.model.getData
import com.sol.fakestoreapiapp.framework.remote.data.ProductDataResponse
import com.sol.fakestoreapiapp.utils.showLoading
import com.sol.fakestoreapiapp.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment(), ProductsListAdapter.OnItemClick {

    private lateinit var mBinding: FragmentProductsBinding

    private  val  mAdapter = ProductsListAdapter(this)

    private  val mViewModel:HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mBinding = FragmentProductsBinding.inflate(inflater,container,false)
        initRecycler()
        initActions()
        initObserver()
        mViewModel.getProducts()


        return mBinding.root

    }

    private fun initObserver() {

        mViewModel.products.observe(viewLifecycleOwner){

            if(it.isSuccessful()){

                mBinding.clEmpty.root.visibility = View.GONE

                val data = it.getData() ?: emptyList()

                if(data.isNotEmpty()){

                    mAdapter.updateData(data)

                }

            }else if(it.isFailed()){
                mBinding.clEmpty.root.visibility = View.VISIBLE
                mBinding.clEmpty.showLoading(true)
                showMessage("SomeThing Went Wrong..!")
            }


        }

    }

    private fun initRecycler() {


        mBinding.recyclerView.adapter = mAdapter


    }

    private fun initActions() {

        mBinding.clEmpty.ivReload.setOnClickListener{
            mViewModel.getProducts()
            mBinding.clEmpty.showLoading(false)
        }

    }

    override fun onItemClick(item: ProductDataResponse) {

        findNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(item.id))

    }


}