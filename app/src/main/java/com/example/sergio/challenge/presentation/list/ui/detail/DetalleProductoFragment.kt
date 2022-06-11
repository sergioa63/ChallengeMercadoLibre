package com.example.sergio.challenge.presentation.list.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.sergio.challenge.R
import com.example.sergio.challenge.databinding.FragmentDetalleProductoBinding
import com.example.sergio.challenge.databinding.FragmentMainBinding
import com.example.sergio.challenge.presentation.common.extencion.observer
import com.example.sergio.challenge.presentation.common.utils.DownloadImageTask
import com.example.sergio.challenge.presentation.list.ChallengeApplication
import com.example.sergio.challenge.presentation.list.ListProductosActivity
import com.example.sergio.challenge.presentation.list.ListProductosViewModel
import com.example.sergio.challenge.presentation.list.ui.main.ListProductosFragmentDirections

class DetalleProductoFragment : Fragment() {

    companion object {
        fun newInstance() = DetalleProductoFragment()
    }

    private val viewModel: ListProductosViewModel by activityViewModels()
    private lateinit var binding: FragmentDetalleProductoBinding
    val args: DetalleProductoFragmentArgs by navArgs()
    private lateinit var listProductosActivity : ListProductosActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalleProductoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
            observer(product_select) {
                it?.let {
                    args.urlImage?.let {
                        DownloadImageTask(binding.ImageDetalle)
                            .execute(it);
                    }
                    binding.textView4.text = String.format(ChallengeApplication.localeCOL,
                        ChallengeApplication.context.getString(R.string.precio),it.price)
                    listProductosActivity.hideProgressBar()
                }
            }

            observer(success_producto){
                if(it) {
                    NavHostFragment.findNavController(this@DetalleProductoFragment).navigate(
                        DetalleProductoFragmentDirections.actionDetalleProductoFragmentToListProductosFragment()
                    )
                }
            }
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        args.urlImage?.let {
            DownloadImageTask(binding.ImageDetalle)
                .execute(it);
        }
        args.codeProduct?.let {
            viewModel.consultarDetalle(it)
            listProductosActivity.showProgressBar()
        }
    }
    override fun onAttach(cListProductosFragmentontext : Context) {
        super.onAttach(cListProductosFragmentontext)
        listProductosActivity = (activity as ListProductosActivity)
    }
}