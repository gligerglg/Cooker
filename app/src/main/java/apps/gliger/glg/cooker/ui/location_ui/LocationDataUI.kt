package apps.gliger.glg.cooker.ui.location_ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListPopupWindow
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import apps.gliger.glg.cooker.databinding.FragmentLocationDataUiBinding
import apps.gliger.glg.cooker.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LocationDataUI : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val uiBinding = FragmentLocationDataUiBinding.inflate(inflater)
        val direction = arguments?.let { LocationDataUIArgs.fromBundle(it).datasetType }
        val viewModel = ViewModelProviders.of(this).get(LocationUIViewModel::class.java)

        viewModel.repository.getPerson().observe(this, Observer {
            uiBinding.person = it
            uiBinding.isLocation = direction==0
        })

        return uiBinding.root
    }



}
