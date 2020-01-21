package com.michalsvec.singlerowcalendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * @author Michal Švec
 * @since v1.0.0
 */

class SingleRowCalendarAdapter(private val dateList: List<Date>, private var calendarViewManager: CalendarViewManager) : RecyclerView.Adapter<SingleRowCalendarAdapter.CalendarViewHolder>() {

    companion object {
        lateinit var selectionTracker: SelectionTracker<Long>
    }


    override fun getItemViewType(position: Int): Int {
        return if (selectionTracker.isSelected(position.toLong())){
            // when item is selected,position will have negative value + 1
            val selectedPosition = position + 1
            return -selectedPosition
        } else
            position
    }

    init {
        setHasStableIds(true)
    }

    inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long? = itemId
            }
    }

    /**
     * This function is responsible for inflating right itemView layouts
     */
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CalendarViewHolder {

        val viewId =  if(position < 0)
            // when position is negative, item is selected and then we have to take position back to original state
            calendarViewManager.setCalendarViewResourceId(
                (position * -1) -1,
                true
            )
        else
            calendarViewManager.setCalendarViewResourceId(
                position,
                false
            )


        val itemView = LayoutInflater.from(parent.context).inflate(viewId, parent, false)

        return CalendarViewHolder(itemView)
    }


    /**
     * This function is responsible for binding data to itemView
     */
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) =
        calendarViewManager.bindDataToCalendarView(holder, dateList[position], position, selectionTracker.isSelected(position.toLong()))


    /**
     *
     */
    override fun getItemCount() = dateList.size


    override fun getItemId(position: Int): Long = position.toLong()
}