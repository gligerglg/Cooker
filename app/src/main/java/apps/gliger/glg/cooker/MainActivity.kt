package apps.gliger.glg.cooker

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import apps.gliger.glg.cooker.databinding.ActivityMainBinding
import apps.gliger.glg.cooker.repository.RepositoryImpl
import apps.gliger.glg.cooker.service.NetworkReceiver

class MainActivity : AppCompatActivity(),NetworkReceiver.ConnectivityChangeListener {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onNetConnectionChanged(isConnected: Boolean) {
        val repository = RepositoryImpl(applicationContext)
        repository.setNetworkStatus(isConnected)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        registerReceiver(NetworkReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onResume() {
        super.onResume()
        NetworkReceiver.connectivityChangeListener =  this
    }

}
