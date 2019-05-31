package apps.gliger.glg.cooker.ui.main_menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import apps.gliger.glg.cooker.databinding.EventItemBinding
import apps.gliger.glg.cooker.model.Event


class EventListAdapter(private val clickListener: EventItemListener) :
    ListAdapter<Event, EventListAdapter.ViewHolder>(EventDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EventItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(event: Event, clickListener: EventItemListener) {
            binding.event = event
            binding.click = clickListener
            binding.executePendingBindings()
        }
    }

}


class EventDiffCallback : DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}


class EventItemListener(val clickListener: (eventId: Event) -> Unit) {
    fun onClick(event: Event) = clickListener(event)
}


