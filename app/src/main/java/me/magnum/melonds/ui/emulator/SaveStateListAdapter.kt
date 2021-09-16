package me.magnum.melonds.ui.emulator

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.core.view.isInvisible
import me.magnum.melonds.R
import me.magnum.melonds.databinding.ItemSaveStateSlotBinding
import me.magnum.melonds.domain.model.SaveStateSlot
import java.text.SimpleDateFormat

class SaveStateListAdapter(
    slots: List<SaveStateSlot>,
    private val dateFormat: SimpleDateFormat,
    private val onSlotSelected: (SaveStateSlot) -> Unit,
    private val onDeletedSlot: (SaveStateSlot) -> Unit,
) : ListAdapter {

    private val items = slots.toMutableList()
    private val observers = mutableListOf<DataSetObserver?>()

    fun updateSaveStateSlots(slots: List<SaveStateSlot>) {
        items.clear()
        items.addAll(slots)
        observers.forEach { it?.onChanged() }
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        observers.add(observer)
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        observers.remove(observer)
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return items[position].slot.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        if (parent == null) {
            return null
        }

        val view = if (convertView == null) {
            ItemSaveStateSlotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            ItemSaveStateSlotBinding.bind(convertView)
        }

        val item = items[position]
        view.textSlot.text = parent.context.getString(R.string.save_state_slot, item.slot)
        view.textDateTime.text = if (item.exists) {
            dateFormat.format(item.lastUsedDate!!)
        } else {
            parent.context.getString(R.string.empty_slot)
        }
        view.root.setOnClickListener {
            onSlotSelected(item)
        }
        view.buttonDelete.setOnClickListener {
            onDeletedSlot(item)
        }
        view.buttonDelete.isInvisible = !item.exists

        return view.root
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    override fun areAllItemsEnabled(): Boolean {
        return true
    }

    override fun isEnabled(position: Int): Boolean {
        return true
    }
}