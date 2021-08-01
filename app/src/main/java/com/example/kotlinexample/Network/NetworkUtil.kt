package com.example.kotlinexample.Network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi

class NetworkUtil {

    @RequiresApi(Build.VERSION_CODES.M)
    fun getConnectivityString(context: Context): String? {
        var status:String?=null

        val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork= connectivityManager.activeNetworkInfo;
        if(activeNetwork!=null){
            if(activeNetwork.type==ConnectivityManager.TYPE_WIFI){
                    return "fast"
            }
            else if(activeNetwork.type==ConnectivityManager.TYPE_MOBILE){
                if(isConnectedFast(context))
                    status="fast"
                else
                    status="slow"
            }
        }
        else{
            status="0"
            return status;
        }



        return  status;
    }

    private fun isConnectedFast(context: Context): Boolean {
        val networkInfo=NetworkUtil().getNetworkInfo(context)
        return true && networkInfo?.isConnected == true && networkInfo?.type==ConnectivityManager.TYPE_MOBILE;
    }
     fun getNetworkInfo(context: Context):NetworkInfo?{
         val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
         return connectivityManager.activeNetworkInfo;

    }

}