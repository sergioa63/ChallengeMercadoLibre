package com.example.sergio.challenge.presentation.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.sergio.challenge.R
import java.util.*
import java.util.Locale.filter


class AutocompleteAdapter(context: Context, objects: List<AutocompleteData>) :
    ArrayAdapter<AutocompleteData?>(context, android.R.layout.simple_list_item_1,
        objects as List<AutocompleteData?>
    ) {
    var customers: List<AutocompleteData> = objects
    var tempCustomer: ArrayList<AutocompleteData> = ArrayList<AutocompleteData>(objects)
    var suggestions: ArrayList<AutocompleteData> = ArrayList<AutocompleteData>(objects)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val autocomplete: AutocompleteData? = getItem(position)
        if (convertView == null) {
            convertView =
                LayoutInflater.from(context).inflate(R.layout.item_autocomplete, parent, false)
        }
        convertView?.let {  view ->
            val txtProducto = view.findViewById<TextView>(R.id.txtVAutoCategoria)
            val txtCodigo = view.findViewById<TextView>(R.id.txtVAutoCodeCat)
            autocomplete?.let { aut ->
                aut.name?.let {
                    txtProducto.text = it
                }
                aut.codigo?.let {
                    txtCodigo.text = it
                }
            }
        }
        return convertView!!
    }

    override fun getFilter(): Filter {
        return myFilter
    }

    var myFilter: Filter = object : Filter() {
        override fun convertResultToString(resultValue: Any): CharSequence {
            val autocomplete: AutocompleteData = resultValue as AutocompleteData
            return autocomplete.name
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return if (constraint != null) {
                suggestions.clear()
                for (autocomplete in tempCustomer) {
                    if (autocomplete.name.toLowerCase().startsWith(
                            constraint.toString().lowercase(
                                Locale.getDefault()
                            )
                        )
                    ) {
                        suggestions.add(autocomplete)
                    } else if (autocomplete.codigo.toLowerCase().startsWith(
                            constraint.toString().lowercase(
                                Locale.getDefault()
                            )
                        )
                    ) {
                        suggestions.add(autocomplete)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = suggestions
                filterResults.count = suggestions.size
                filterResults
            } else {
                FilterResults()
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.let {  resul ->
                resul.values?.let {
                    val c: ArrayList<AutocompleteData> = it as ArrayList<AutocompleteData>
                    if ( resul.count > 0) {
                        clear()
                        for (auto in c) {
                            add(auto)
                            notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }


}