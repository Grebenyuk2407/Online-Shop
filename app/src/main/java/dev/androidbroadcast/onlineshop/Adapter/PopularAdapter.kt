package dev.androidbroadcast.onlineshop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import dev.androidbroadcast.onlineshop.Models.ItemModel
import dev.androidbroadcast.onlineshop.databinding.ViewholderRecommendedBinding

class PopularAdapter(val items: MutableList<ItemModel>):RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    private var context:Context? = null

    class ViewHolder(val binding: ViewholderRecommendedBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       context = parent.context
        val binding = ViewholderRecommendedBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleTxt.text=items[position].title
        holder.binding.priceTxt.text="$"+items[position].price.toString()
        holder.binding.ratingTxt.text = items[position].rating.toString()

        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
                .apply(requestOptions)
            .into(holder.binding.pic)


       // holder.itemView.setOnClickListener {
        //    val intent = Intent
        //}

    }

    override fun getItemCount(): Int = items.size
}