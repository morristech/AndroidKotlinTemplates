

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.Collections
import java.util.Comparator

abstract class BaseRecyclerAdapter<T>(var items:MutableList<T>) : RecyclerView.Adapter<BaseRecyclerAdapter.Companion.ViewHolder>() {

    protected abstract val layout: Int

    companion object {
        class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(layout, parent, false))
    }

    fun addAll(items: MutableList<T>){
        items.addAll(items)
        notifyDataSetChanged()
    }

    fun add(`object`: T) {
        items.add(`object`)
        notifyItemInserted(itemCount - 1)
    }

    fun clear() {
        val size = itemCount
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItem(position: Int): T {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getPosition(item: T): Int {
        return items.indexOf(item)
    }

    fun insert(`object`: T, index: Int) {
        items.add(index, `object`)
        notifyItemInserted(index)

    }

    fun remove(`object`: T) {
        val position = getPosition(`object`)
        items.remove(`object`)
        notifyItemRemoved(position)
    }

    fun sort(comparator: Comparator<in T>) {
        Collections.sort(items, comparator)
        notifyItemRangeChanged(0, itemCount)
    }
}