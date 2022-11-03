package com.hope.bitex.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hope.bitex.R
import com.hope.bitex.data.model.FakePriceModel
import kotlinx.android.synthetic.main.adapter_price.view.*

class PriceAdapter(
    private val context: Context,
    private val models: List<FakePriceModel>,
    private val maxWidth:Int,
    private val textColor:Int,
    private val progressColor:Int,
) : RecyclerView.Adapter<PriceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.adapter_price, parent, false)
    )


    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        models[position].apply {
            holder.textPrice.text = price.toString()
            holder.textAmount.text = amount.toString()
            holder.viewProgress.layoutParams.apply {
                width=(progress*maxWidth)/100
            }
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val viewProgress = view.viewProgress
        val textPrice = view.textPrice
        val textAmount = view.textAmount

        init {
            textPrice.setTextColor(ContextCompat.getColor(context,textColor))
            viewProgress.backgroundTintList= ContextCompat.getColorStateList(context, progressColor)
        }

    }
}
