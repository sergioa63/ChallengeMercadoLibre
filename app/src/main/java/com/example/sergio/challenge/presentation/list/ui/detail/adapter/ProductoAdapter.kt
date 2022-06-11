package com.example.sergio.challenge.presentation.list.ui.detail.adapter

import android.app.Application
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.sergio.challenge.R
import com.example.sergio.challenge.databinding.ItemProductoBinding
import com.example.sergio.challenge.domain.list.entity.Producto
import com.example.sergio.challenge.presentation.common.extencion.inflate
import com.example.sergio.challenge.presentation.common.extencion.loadUrl
import com.example.sergio.challenge.presentation.common.utils.DownloadImageTask
import com.example.sergio.challenge.presentation.list.ChallengeApplication
import java.net.URL
import java.util.*
import kotlin.properties.Delegates


class ProductoAdapter (items: List<Producto> = emptyList(), val listenerOnClick : (Producto) -> Unit) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder> (), Filterable {

    lateinit var view : View
    lateinit var item : Producto

    var items : List<Producto> by Delegates.observable(items) { property, oldValue, newValue ->
        itemsFiltered = newValue
        notifyDataSetChanged()
    }

    var itemsFiltered: List<Producto> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = parent.inflate(R.layout.item_producto)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        item = itemsFiltered[position]
        holder.itemView.setOnClickListener{
            listenerOnClick(itemsFiltered[position])
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemsFiltered.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        lateinit var item : Producto
        val binding = ItemProductoBinding.bind(view)

        fun bind (producto: Producto) {
            item = producto
            with(binding) {
                binding.title.text = item.name
                //binding.price.text = item.
                item.urlImage?.let {
                    DownloadImageTask(binding.image)
                        .execute(it);
                }
                item.price?.let {
                    binding.price.text = String.format(ChallengeApplication.localeCOL,
                        ChallengeApplication.context.getString(R.string.precio),it)
                }
            }
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString()!!.toLowerCase() ?: ""
                if (charString.isEmpty()) {
                    itemsFiltered = items
                } else {
                    val filteredList = ArrayList<Producto>()
                    items
                        .filter {
                            (it.name?.let { nombre ->
                                nombre.toLowerCase().contains(constraint!!)
                            } ?: kotlin.run {
                                false
                            })
                        }
                        .forEach { filteredList.add(it) }
                    itemsFiltered = filteredList

                }
                return FilterResults().apply {
                    values = itemsFiltered
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemsFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as List<Producto>
                notifyDataSetChanged()
            }
        }
    }
}