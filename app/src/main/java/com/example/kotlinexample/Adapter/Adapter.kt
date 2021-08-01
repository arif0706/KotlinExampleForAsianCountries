package com.example.kotlinexample.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexample.Model.Country
import com.example.kotlinexample.R
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class Adapter(private val context:Context,
              private val countriesList: List<Country>?,
              private val itemClickListener: ItemClickListener): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.list_item_country_card,parent,false)
        return ViewHolder(view,itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val country=countriesList?.get(position);
        holder.countryName.text=country?.name;
        GlideToVectorYou.init().
                with(context)
            .load(Uri.parse(country?.flag),holder.imageView)

    }

    override fun getItemCount(): Int {

        return countriesList?.size!!
    }
    inner class ViewHolder(itemView: View,itemClickListener: ItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView =itemView.findViewById(R.id.flag)
        val countryName: TextView=itemView.findViewById(R.id.country_name)

        init {
            itemView.setOnClickListener(View.OnClickListener {
                itemClickListener.onCountryClicked(countriesList?.get(adapterPosition))
            })
        }

    }
    interface ItemClickListener{
        fun onCountryClicked(country: Country?)
    }

}



