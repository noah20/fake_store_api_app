package com.sol.fakestoreapiapp.utils

import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.sol.fakestoreapiapp.R
import com.sol.fakestoreapiapp.databinding.EmptyStateLayoutBinding


fun RequestBuilder<Drawable>.doAfterImageLoaded(handleResponse:Boolean = false , afterImageLoaded:(isSuccess:Boolean)->Unit): RequestBuilder<Drawable> {


    return this.error(R.drawable.ic_broken_image)
        .addListener(object : RequestListener<Drawable?> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable?>?,
            isFirstResource: Boolean
        ): Boolean {
            afterImageLoaded.invoke(true)
            return handleResponse

        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable?>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            afterImageLoaded.invoke(false)
            return handleResponse
        }
    })


}



fun EmptyStateLayoutBinding.showLoading(isFail:Boolean){

    if(isFail){
        this.ivReload.visibility = View.VISIBLE
        this.progressBar.visibility = View.GONE
        this.tvMessage.visibility = View.GONE
    }else{
        this.ivReload.visibility = View.GONE
        this.progressBar.visibility = View.VISIBLE
        this.tvMessage.visibility = View.GONE

    }

}
