package org.smartmobiletech.euriskoapp.ui.listview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.smartmobiletech.euriskoapp.R
import org.smartmobiletech.euriskoapp.modules.UserData

class ListAdapter(private val userData: List<UserData>, private val onClick: ClickListener): RecyclerView.Adapter<ListAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = userData[position].title
        if(!userData[position].completed)
            holder.card.setCardBackgroundColor(Color.parseColor("#CF0A0A"))
        else
            holder.card.setCardBackgroundColor(Color.parseColor("#FFFFFF"))

        holder.itemView.setOnClickListener {
            onClick.clicked(userData[position])
        }
    }

    override fun getItemCount(): Int {
        return userData.size
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.txt_title)
        val card: CardView = view.findViewById(R.id.card)
    }

    interface ClickListener {
        fun clicked(row: UserData)
    }
}