package com.example.sergio.challenge.presentation.list.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sergio.challenge.databinding.FragmentMainBinding
import com.example.sergio.challenge.presentation.common.extencion.observer
import com.example.sergio.challenge.presentation.list.ListProductosActivity
import com.example.sergio.challenge.presentation.list.ListProductosViewModel
import com.example.sergio.challenge.presentation.list.ui.detail.adapter.ProductoAdapter

class ListProductosFragment : Fragment() {

    companion object {
        fun newInstance() = ListProductosFragment()
    }

    private lateinit var adapter : ProductoAdapter

    private lateinit var listProductosActivity : ListProductosActivity
    private val viewModel: ListProductosViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.searchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                adapter?.let {adapter.filter.filter(newText) }
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })
        with(viewModel) {
            observer(error_msn){
                if(it.isNotEmpty()) { listProductosActivity.msnError(it) }
            }
            observer(list_productos) { list->
                if(list.isEmpty()) {
                    binding.searchProduct.visibility = View.GONE
                } else {
                    binding.searchProduct.visibility = View.VISIBLE
                }
                adapter.items = list
                binding.rvItemsProduct.adapter = adapter
                listProductosActivity.hideProgressBar()
            }
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.rvItemsProduct.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onAttach(cListProductosFragmentontext : Context) {
        super.onAttach(cListProductosFragmentontext)
        listProductosActivity = (activity as ListProductosActivity)
        adapter = ProductoAdapter (
            listenerOnClick = {
                listProductosActivity.showProgressBar()
                viewModel.setProductSelect(it)
                NavHostFragment.findNavController(this@ListProductosFragment).navigate (
                    ListProductosFragmentDirections.actionListProductosFragmentToDetalleProductoFragment(
                        it.code, it.urlImage.toString()
                    )
                )
            })
    }
}