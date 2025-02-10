package com.example.taskapp.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.taskapp.R
import com.example.taskapp.dashboard.model.ItemDatasList


class DashBoardAdapter(private val ctx: Context, val itemsList: ArrayList<ItemDatasList>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        var onItemClickListener: OnItemClickListener? = null
    }

    open interface OnItemClickListener {
        fun onItemClick(adapterPosition: Int);
    }

    fun setOnItemClickListener(onItemClickListeners: OnItemClickListener) {
        onItemClickListener = onItemClickListeners;
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // For Loading the datas !!
        val v: View
        v = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_items_list, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            bindItemsData(
                holder as ItemViewHolder,
                itemsList.get(position),
                position
            )

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun bindItemsData(
        viewHolder: ItemViewHolder,
        itemList: ItemDatasList,
        position: Int
    ) {

        // Use replaceAll() to clean the string
        val cleanedData = itemList?.description!!.replace("(\r\n|\n\r|\r\r|\n\n)+".toRegex(), "\n").trim { it <= ' ' }
        viewHolder.txtDescription.setText(cleanedData)

        viewHolder.txtAuthor.setText(itemList.author)
        Glide.with(viewHolder.imgView.context)
            .load(itemList.urlToImage)
            .transform(CenterInside(), RoundedCorners(24))
            .placeholder(R.drawable.placeholder_icon)
            .error(R.drawable.error_img)
            .into(viewHolder.imgView)


        viewHolder.constraintParentContainer.setOnClickListener {
            if (onItemClickListener != null) {

               onItemClickListener!!.onItemClick(
                    viewHolder.adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val constraintParentContainer =
            itemView.findViewById(R.id.constraint_parent_container) as ConstraintLayout
        val txtDescription = itemView.findViewById(R.id.txt_description) as TextView
        val txtAuthor = itemView.findViewById(R.id.txt_author) as TextView
        val imgView = itemView.findViewById(R.id.img_view) as ImageView
    }

}