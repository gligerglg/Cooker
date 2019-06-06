package apps.gliger.glg.cooker


import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import apps.gliger.glg.cooker.databinding.EventInputBinding
import apps.gliger.glg.cooker.databinding.FragmentMainMenuUiBinding
import apps.gliger.glg.cooker.model.Event
import apps.gliger.glg.cooker.ui.main_menu.EventItemListener
import apps.gliger.glg.cooker.ui.main_menu.EventListAdapter
import apps.gliger.glg.cooker.ui.main_menu.MainMenuViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MainMenuUI : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMainMenuUiBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModel : MainMenuViewModel by sharedViewModel()

        val adapter = EventListAdapter(EventItemListener {event:Event->
            performEventAdd(event, inflater, viewModel)
        })

        binding.mainMenuRecycler.layoutManager = GridLayoutManager(context, 2)
        binding.mainMenuRecycler.adapter = adapter
        binding.button.setOnClickListener { Navigation.findNavController(binding.root).navigate(MainMenuUIDirections.actionMainToPeople(true)) }

        viewModel.eventList.observe(this, Observer {
            val eventlist = arrayListOf<Event>()
            eventlist.addAll(it)
            eventlist.add(Event(-1,"NewItem","New","NULL"))
            adapter.submitList(eventlist)
        })


        return binding.root
    }

    private fun performEventAdd(
        it: Event,
        inflater: LayoutInflater,
        viewModel: MainMenuViewModel
    ) {
        val eventBinding = EventInputBinding.inflate(inflater)
        if (it.id == -1L) {

            val dialog = AlertDialog.Builder(context)
            dialog.setView(eventBinding.root)

            val alertDialog: AlertDialog = dialog.create()
            alertDialog.show()

            eventBinding.eventSubmit.setOnClickListener {
                if (eventBinding.eventStatus.text!!.isNotEmpty() && eventBinding.eventTitle.text!!.isNotEmpty()) {
                    viewModel.addNewEvent(
                        Event(
                            eventBinding.eventTitle.text.toString(),
                            eventBinding.eventStatus.text.toString()
                        )
                    )
                    alertDialog.dismiss()
                }
            }
        } else
            viewModel.removeEvent(it)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = ChangeBounds().apply {
            duration=1000
        }*/


    }

}
