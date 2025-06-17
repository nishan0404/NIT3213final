package com.example.nit3213final

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DashboardAdapter(
    private val items: List<DashboardItem>
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    inner class ViewHolder(val container: LinearLayout) :
        RecyclerView.ViewHolder(container)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val container = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dashboard, parent, false) as LinearLayout
        return ViewHolder(container)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.container.removeAllViews()

        item.fields
            .filterKeys { it != "description" }
            .forEach { (key, value) ->
                TextView(holder.container.context).apply {
                    text = "${key.replaceFirstChar { it.uppercase() }}: $value"
                    textSize = 16f
                    setPadding(0, 8, 0, 8)
                }.also { holder.container.addView(it) }
            }

        holder.container.setOnClickListener {
            val ctx = holder.container.context
            val intent = Intent(ctx, DetailActivity::class.java).apply {
                putExtra("fields", HashMap(item.fields))  // Passing data to detail screen
            }
            ctx.startActivity(intent) // Navigate to detail activity
        }
    }

    override fun getItemCount(): Int = items.size
}
