package com.example.shoestore.ui.newshoelist

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestore.R
import com.example.shoestore.model.Shoe


class ShoeListAdapter(var ShoesList: MutableList<Shoe>?):RecyclerView.Adapter<ShoeListAdapter.Viewholder>()
{





    class Viewholder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var Image :ImageView?=null
        var Name :TextView?=null
        var Size:TextView?=null
        var Company:TextView?=null
        var Description:TextView?=null

        init {
            Image = itemView.findViewById(R.id.image)
            Name =itemView.findViewById(R.id.name)
            Size =itemView.findViewById(R.id.size)
            Company =itemView.findViewById(R.id.company)
           Description =itemView.findViewById(R.id.description)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)

        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val uri = ShoesList?.get(position)?.images?.get(0)
        val imageResource: Int =
            holder.itemView.resources.getIdentifier(uri, null, holder.itemView.context.packageName)
        val logoDrawable: Drawable = holder.itemView.resources.getDrawable(imageResource)


        holder.Image?.setImageDrawable(logoDrawable)

    }

    override fun getItemCount(): Int {
        return ShoesList?.size ?: 0
    }
}