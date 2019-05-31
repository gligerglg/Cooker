package apps.gliger.glg.cooker.ui


import android.graphics.Path
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator

import apps.gliger.glg.cooker.R
import kotlinx.android.synthetic.main.fragment_splash_ui.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SplashUI : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_ui, container, false)
        val timer = object : CountDownTimer(2000,1000) {
            override fun onFinish() {
                /*val extras = FragmentNavigator.Extras.Builder().also {
                    it.addSharedElement(imageView,"logo_animation")
                    it.addSharedElement(txt_view,"txt_animation")
                }.build()*/
//                Navigation.findNavController(view).navigate(SplashUIDirections.splashToMainMenu(),extras)
                Navigation.findNavController(view).navigate(SplashUIDirections.splashToMainMenu())
            }

            override fun onTick(millisUntilFinished: Long) {

            }

        }
        timer.start()
        return view
    }

}
