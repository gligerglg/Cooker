package apps.gliger.glg.cooker


import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import apps.gliger.glg.cooker.databinding.FragmentPeopleUiBinding
import apps.gliger.glg.cooker.ui.people_ui.PeopleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel

class PeopleUI : Fragment() {

    var isFromMainUI:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val uiBinding = FragmentPeopleUiBinding.inflate(inflater)
        val viewModel :PeopleViewModel by sharedViewModel()
        isFromMainUI = arguments?.let { PeopleUIArgs.fromBundle(it).isFromMainUI }!!

        uiBinding.locationButton.setOnClickListener { isFromMainUI=false
            Navigation.findNavController(uiBinding.root).navigate(PeopleUIDirections.actionPeople2Locatioin(0)) }
        uiBinding.loginButton.setOnClickListener { isFromMainUI=false
            Navigation.findNavController(uiBinding.root).navigate(PeopleUIDirections.actionPeople2Locatioin(1)) }
        uiBinding.btnRefresh.setOnClickListener {
            uiBinding.shimmer.startShimmer()
            uiBinding.shimmer.visibility = View.VISIBLE
            uiBinding.profileActiveView.visibility = View.GONE
            uiBinding.btnRefresh.visibility=View.GONE

            CoroutineScope(Dispatchers.Main).launch {

                val response = viewModel.getPeopleList()
                if(response!=null)
                    viewModel.savePeopleData(response.results[0])
                else Navigation.findNavController(uiBinding.root).navigate(PeopleUIDirections.actionPeople2Error())
            }
        }

        viewModel.getPerson().observe(this, Observer {
            it?.let {
                uiBinding.person = it
                uiBinding.shimmer.stopShimmer()
                uiBinding.shimmer.visibility = View.GONE
                uiBinding.profileActiveView.visibility = View.VISIBLE
                uiBinding.btnRefresh.visibility=View.VISIBLE
            }
        })

        return uiBinding.root
    }
}
