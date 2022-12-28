package com.sol.fakestoreapiapp.ui.main

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sol.fakestoreapiapp.R
import com.sol.fakestoreapiapp.databinding.ProductItemLayoutBinding
import com.sol.fakestoreapiapp.framework.remote.data.ProductDataResponse
import com.sol.fakestoreapiapp.utils.doAfterImageLoaded
import java.util.*
import kotlin.collections.ArrayList


class ProductsListAdapter(private val listener:OnItemClick):RecyclerView.Adapter<ProductsListAdapter.ProductViewHolder>() {

    private var productsList = ArrayList<ProductDataResponse>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val bind = ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val item = productsList[position]
        holder.binItem(item)

    }

    override fun getItemCount() =  productsList.size

    fun updateData(data: List<ProductDataResponse>) {
        if(productsList.isNotEmpty()){
            return
        }

        productsList.addAll(data)
        notifyItemRangeInserted(0,data.size)

    }


    interface OnItemClick{

        fun onItemClick(item: ProductDataResponse)

    }

    inner class ProductViewHolder(private val binding: ProductItemLayoutBinding):RecyclerView.ViewHolder(binding.root){

        private val resources:Resources = binding.root.context.resources

        fun binItem(item:ProductDataResponse){

            binding.tvProductPrice.text = resources.getString(R.string.str_amount,String.format(Locale.ENGLISH,"%.2f",item.price))

            binding.tvProductTitle.text = item.title

            Glide.with(binding.root.context)
                .load(item.image)
                .doAfterImageLoaded {
                    binding.progressBar.visibility = ViewGroup.GONE
                }.into(binding.ivImage)

            binding.root.setOnClickListener{listener.onItemClick(item)}

        }
    }



}