package com.example.shoestore.ui.newshoelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestore.R
import com.example.shoestore.model.Shoe


class ShoeListAdapter(var ShoesList: MutableList<Shoe?>?):RecyclerView.Adapter<ShoeListAdapter.Viewholder>()
{





    class Viewholder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var Image :ImageView? = itemView.findViewById(R.id.image)
        var Name :TextView? = itemView.findViewById(R.id.name)
        var Size:TextView? = itemView.findViewById(R.id.size)
        var Company:TextView? = itemView.findViewById(R.id.company)
        var Description:TextView? = itemView.findViewById(R.id.description)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)

        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        val context = holder.Image?.context
        val image__name = ShoesList?.get(position)?.images?.first()
        val id = context!!.resources.getIdentifier(image__name, "drawable", context.packageName)
        holder.Image?.setImageResource(id)
// using string formatting
        holder.Name?.text = context.resources.getString(R.string.shoesname,ShoesList?.get(position)?.name)
        holder.Company?.text   = context.resources.getString(R.string.shoescompany,ShoesList?.get(position)?.company)
        holder.Size?.text = context.resources.getString(R.string.shoessize,ShoesList?.get(position)?.size.toString())
        holder.Description?.text = context.resources.getString(R.string.description,ShoesList?.get(position)?.description)





    }

    override fun getItemCount(): Int {
        return ShoesList?.size ?: 0
    }
//function to update the adapter list
    fun getShoeList(ShoesList: MutableList<Shoe?>?)
    {
        this.ShoesList = ShoesList
        notifyDataSetChanged()
    }
}