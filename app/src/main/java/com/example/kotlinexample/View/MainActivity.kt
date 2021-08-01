package com.example.kotlinexample.View

import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.transition.AutoTransition
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexample.Adapter.Adapter
import com.example.kotlinexample.Controller.MainActivityController
import com.example.kotlinexample.Model.Country
import com.example.kotlinexample.Network.InternetReceiver
import com.example.kotlinexample.R
import com.example.kotlinexample.Room.AppDatabase
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.gson.Gson

class MainActivity : AppCompatActivity() , MainActivityView ,Adapter.ItemClickListener,InternetReceiver.getConnection{


    var progressIndicator:LinearProgressIndicator?=null
    var internet_layout:LinearLayout?=null
    var internet_check:TextView?=null;

    var controller:MainActivityController?=null

    var internet_connection:Boolean?=null
    var isDataRetrieved:Boolean?=false

    var delete_room_data: Button?=null
    var empty_message:TextView?=null


    var appDatabase:AppDatabase?=null;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDatabase=AppDatabase.getRoomInstance(this)

        val intentFilter=IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

        registerReceiver(InternetReceiver(this),intentFilter);
        controller=MainActivityController(this)


        init()


        delete_room_data?.setOnClickListener(View.OnClickListener {
            appDatabase?.databaseDao()?.deleteData();
            controller?.getCountriesFromRoom(this)
        })
    }
    fun init(){
        progressIndicator=findViewById(R.id.progress_horizontal)
        internet_layout=findViewById(R.id.internet_layout)
        internet_check=findViewById(R.id.internet_check)
        delete_room_data=findViewById(R.id.delete_room)
        empty_message=findViewById(R.id.empty_message)

    }



    override fun onGettingCountries(countries: List<Country>?) {
        setRecyclerView(countries);


        if (countries != null) {
            for( i in countries.indices) {
                appDatabase?.databaseDao()?.InsertCountry(countries[i]);
            }

        }
    }

    override fun onGettingListFromRoom(countries: List<Country>?) {
        setRecyclerView(countries)
    }

    fun setRecyclerView(countries: List<Country>?){


        progressIndicator?.visibility=View.GONE


        if(countries?.isEmpty() == true){
            empty_message?.visibility=View.VISIBLE
        }
        else{
            empty_message?.visibility=View.GONE
        }

        val recyclerView=findViewById<RecyclerView>(R.id.recycler_view);
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter= Adapter(this,countries,this)



    }

    override fun onCountryClicked(country: Country?) {


        val intent = Intent(this,CountryDetails::class.java)
        intent.putExtra("country",Gson().toJson(country))
        startActivity(intent)

    }

    override fun getNoConnection(text: String) {

        progressIndicator?.visibility=View.GONE

        delete_room_data?.visibility=View.VISIBLE

        TransitionManager.beginDelayedTransition(findViewById(R.id.main_layout),AutoTransition())
        TransitionManager.beginDelayedTransition(internet_layout,AutoTransition())

        internet_layout?.visibility=View.VISIBLE
        internet_layout?.setBackgroundColor(Color.RED)
        internet_check?.setTextColor(Color.WHITE)
        internet_check?.setText(text)

        internet_connection=false

        if(isDataRetrieved==false){
            controller?.getCountriesFromRoom(this);
        }

    }

    override fun getYesConnection(online: String, text: String?) {

        progressIndicator?.visibility= View.VISIBLE
        delete_room_data?.visibility=View.GONE

        internet_layout?.setBackgroundColor(Color.parseColor("#4b8b3b"))
        internet_check?.setTextColor(Color.WHITE)
        internet_check?.setText(online)

        val handler=Handler()

        Thread(Runnable {
            try {
                Thread.sleep(1500)
            }
            catch (e:InterruptedException){
                e.printStackTrace()
            }
            handler.post(Runnable {
                TransitionManager.beginDelayedTransition(internet_layout,Slide(Gravity.BOTTOM))
                internet_layout?.visibility=View.GONE
            })
        }).start()

        controller?.getCountries();
        isDataRetrieved=true
        internet_connection=true

    }
}