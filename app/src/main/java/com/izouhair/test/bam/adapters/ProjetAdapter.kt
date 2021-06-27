package com.izouhair.test.bam.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.izouhair.test.bam.R
import com.izouhair.test.bam.adapters.ProjetAdapter.mViewHolder
import com.izouhair.test.bam.modals.Projet
import io.realm.RealmList
import java.util.*

class ProjetAdapter(data: MutableList<Projet>, context: Context) :
    RecyclerView.Adapter<mViewHolder>() {
    private val infalter: LayoutInflater
    private var data: MutableList<Projet>
    private val context: Context
    private var clickListener: ClickListener? = null
    fun setClickListener(clicklistener: ClickListener?) {
        clickListener = clicklistener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        var rootView: View? = null
        rootView = infalter.inflate(R.layout.item_projet_open_source, parent, false)
        return mViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val projet = data[position]
        holder.name.text = projet.name
        holder.description.text = projet.description
        holder.favorite.setImageDrawable(
            if (projet.favorite) context.resources.getDrawable(R.drawable.ic_baseline_favorite_24) else context.resources.getDrawable(
                R.drawable.ic_baseline_favorite_border
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(position: Int): Projet? {
        return try {
            data[position]
        } catch (e: Exception) {
            null
        }
    }

    fun addAllItems(list: RealmList<Projet>?) {
        data.addAll(list!!)
        notifyDataSetChanged()
    }

    fun addItem(item: Projet) {
        data.add(item)
        notifyDataSetChanged()
    }

    fun clear() {
        data = ArrayList()
        notifyDataSetChanged()
    }

    /*public void checkFavProjects(Projet item) {

        List<Projet> favProjects = ProjetController.projetsList(true);
        if (favProjects != null && favProjects.size() > 0) {
            for (Projet prj : favProjects) {

            }
        }

    }*/
    //view custom click listener
    interface ClickListener {
        fun itemClicked(view: View?, position: Int)
        fun favoriteProj(view: View?, position: Int)
    }

    inner class mViewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var image: ImageView
        var name: TextView
        var description: TextView
        var favorite: ImageView

        init {
            image = itemView!!.findViewById(R.id.image)
            name = itemView.findViewById(R.id.name)
            description = itemView.findViewById(R.id.description)
            favorite = itemView.findViewById(R.id.favorite)

            //add a listener to the button
            favorite.setOnClickListener { view: View? ->
                clickListener!!.favoriteProj(view, adapterPosition)
                notifyItemChanged(adapterPosition)
            }
            itemView.setOnClickListener { view: View? ->
                clickListener!!.itemClicked(
                    view,
                    adapterPosition
                )
            }
        }
    }

    init {
        infalter = LayoutInflater.from(context)
        this.data = data
        this.context = context
    }
}