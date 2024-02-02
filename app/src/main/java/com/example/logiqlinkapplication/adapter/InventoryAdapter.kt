package com.example.logiqlinkapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.logiqlinkapplication.R
import com.example.logiqlinkapplication.room.InventoryDataEntity

/**
 * Created by Ritik on 2/1/2024.
 */
class InventoryAdapter(
    private val list: List<InventoryDataEntity>,
    private val onClick: ClickListener
) :
    RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.inventory_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.productName.text = "${item.title} (${item.type})"
        holder.productDes.text = item.description
        holder.productColor.text = "Color : ${item.color}"
        holder.productQty.text = "Available : ${item.available}"
        holder.productPrice.text = item.price

        holder.report.setOnClickListener {
            onClick.onClickedToReport()
        }

        holder.add.setOnClickListener {
            onClick.onClickToAdd(item.available ?: 0, holder.temCount)
        }
        holder.minus.setOnClickListener {
            onClick.onClickToSub(item.available ?: 0, holder.temCount)

        }

    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val productName: TextView = item.findViewById(R.id.product_name)
        val productDes: TextView = item.findViewById(R.id.product_des)
        val productColor: TextView = item.findViewById(R.id.product_color)
        val productQty: TextView = item.findViewById(R.id.product_qty)
        val productPrice: TextView = item.findViewById(R.id.price)
        val report: Button = item.findViewById(R.id.report_btn)
        val add: ImageButton = item.findViewById(R.id.add)
        val minus: ImageButton = item.findViewById(R.id.minus)
        val temCount: TextView = item.findViewById(R.id.count)

    }
}

interface ClickListener {
    fun onClickedToReport()
    fun onClickToAdd(count: Int, temCount: TextView)
    fun onClickToSub(count: Int, temCount: TextView)
}