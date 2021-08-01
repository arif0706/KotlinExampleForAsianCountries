package com.example.kotlinexample.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinexample.Controller.CountryDetailsController
import com.example.kotlinexample.Model.Country
import com.example.kotlinexample.R
import com.github.chrisbanes.photoview.PhotoView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson

class CountryDetails : AppCompatActivity(),CountryDetailsView {

    var country:Country?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)

        val intent:Intent=intent


        country= Gson().fromJson(intent.getStringExtra("country"),Country::class.java)

        setValues();

    }
    fun setValues(){

        val flag: PhotoView =findViewById(R.id.photo_view)
        val country_name:TextView=findViewById(R.id.country_name)
        val capital_name = findViewById<TextView>(R.id.capital_name)
        val region_name = findViewById<TextView>(R.id.region_name)
        val sub_region_name = findViewById<TextView>(R.id.sub_region_name)
        val population_number = findViewById<TextView>(R.id.population_number)
        val borders_name = findViewById<TextView>(R.id.borders_name)
        val languages_name = findViewById<TextView>(R.id.languages_name)


        val chipGroup =findViewById<ChipGroup>(R.id.borders_chip_group)

        if(country?.borders?.size!! >0){
            borders_name.visibility= View.GONE
            for (i in 0 until country!!.borders?.size!!) run {
                val chip =Chip(this);
                val layoutParams=ChipGroup.LayoutParams(
                    ChipGroup.LayoutParams.WRAP_CONTENT,ChipGroup.LayoutParams.WRAP_CONTENT)

                chip.layoutParams=layoutParams;
                chip.text=country?.borders?.get(i)
                chipGroup.addView(chip)


                chip.setOnClickListener(View.OnClickListener {
                    CountryDetailsController(this).getCountryWithCode(chip.text.toString())
                })
            }
        }
        else{
            borders_name.append("No borders");
        }

        for(i in 0 until country!!.languages?.size!!)run {
            languages_name.append("${i+1}. ${country?.languages?.get(i)?.name} \n")
        }
        country_name.text= country!!.name
        capital_name.text= country!!.capital
        region_name.text= country!!.region
        sub_region_name.text= country!!.subregion
        population_number.text= country!!.population

        GlideToVectorYou.init()
            .with(this)
            .load(Uri.parse(country!!.flag),flag)


    }

    override fun onGettingCountry(country: Country?) {
        val intent=Intent(this,CountryDetails::class.java)
        intent.putExtra("country",Gson().toJson(country))
        startActivity(intent)
    }
}