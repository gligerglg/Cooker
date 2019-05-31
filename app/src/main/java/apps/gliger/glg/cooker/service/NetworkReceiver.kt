package apps.gliger.glg.cooker.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class NetworkReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if(connectivityChangeListener!=null){
            connectivityChangeListener!!.onNetConnectionChanged(isConnectedOrConnecting(context!!))
        }
    }

    private fun isConnectedOrConnecting(context: Context): Boolean {
        var connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo
        return networkInfo!=null && networkInfo.isConnected
    }

    interface ConnectivityChangeListener{
        fun onNetConnectionChanged(isConnected:Boolean)
    }

    companion object{
        var connectivityChangeListener:ConnectivityChangeListener?=null
    }
}

