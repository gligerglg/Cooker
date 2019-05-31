package apps.gliger.glg.cooker.ui.people_ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import apps.gliger.glg.cooker.R
import com.squareup.picasso.Picasso

class ProfileImageAdapter {
    fun getImageUrl():String{
        return ""
    }

    companion object{
        @BindingAdapter("bind:imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, url:String?)
        {Picasso.get().load(url).placeholder(R.drawable.ic_add).into(view) }
    }
}