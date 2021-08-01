package com.example.kotlinexample.Network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

class InternetReceiver(var connection: getConnection?) : BroadcastReceiver() {

    constructor():this(
        null
    )


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {


        var status= context?.let { NetworkUtil().getConnectivityString(it) };
        if(status.equals("0")){
            status="No internet connection"
            connection?.getNoConnection(status)
        }
        else{
            connection?.getYesConnection("Online",status);
        }
    }
    interface getConnection{
        fun getNoConnection(text:String)
        fun getYesConnection(online:String,text:String?)
    }
}