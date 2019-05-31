package apps.gliger.glg.cooker

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import apps.gliger.glg.cooker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var mainBinding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}
