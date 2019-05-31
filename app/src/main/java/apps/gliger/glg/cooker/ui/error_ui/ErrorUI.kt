package apps.gliger.glg.cooker


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import apps.gliger.glg.cooker.databinding.FragmentErrorUiBinding
import apps.gliger.glg.cooker.repository.Repository

class ErrorUI : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val uiBinding =FragmentErrorUiBinding.inflate(inflater)
        val repository = Repository.getInstance(context!!)
        repository.networkStatus.observe(this, Observer {
            if(it) Navigation.findNavController(uiBinding.root).popBackStack()
        })

        return uiBinding.root
    }


}
