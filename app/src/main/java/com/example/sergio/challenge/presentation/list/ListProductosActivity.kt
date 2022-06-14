package com.example.sergio.challenge.presentation.list

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.AdapterView.*
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sergio.challenge.R
import com.example.sergio.challenge.databinding.ActivityListProductosBinding
import com.example.sergio.challenge.presentation.common.extencion.checkPermissions
import com.example.sergio.challenge.presentation.common.extencion.observer
import com.example.sergio.challenge.presentation.common.utils.ViewUtils
import com.example.sergio.challenge.presentation.list.adapter.AutocompleteAdapter
import com.example.sergio.challenge.presentation.list.ui.detail.DetalleProductoFragmentDirections
import com.example.sergio.challenge.presentation.list.ui.main.ListProductosFragment
import com.example.sergio.challenge.presentation.list.ui.main.ListProductosFragmentDirections
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProductosActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback  {

    private lateinit var binding : ActivityListProductosBinding
    private val viewModel : ListProductosViewModel by viewModels()
    protected val CODE_PERMISSION = 122
    private var  mySnackbar : Snackbar? = null
    val permission = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
        )
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            // Handle Permission granted/rejected
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value
                if (isGranted) {
                    // Permission is granted by each
                } else {
                    Toast.makeText(
                        this@ListProductosActivity,
                        R.string.all_permission_are_required,
                        Toast.LENGTH_SHORT
                    ).show()
                    Snackbar.make(
                        binding.container, R.string.all_permission_are_required,
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("Ok") {
                        ActivityCompat.requestPermissions(
                            this@ListProductosActivity, permission,
                            CODE_PERMISSION
                        )
                    }.show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_list_productos)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        with(viewModel) {
            observer(error_msn){
                if(it.isNotEmpty()) { msnError(it) }
            }
            observer(success_catalogo){
                it.getContentIfNotHandled()?.let { success ->
                    if(success) {
                        viewModel.getListCatalogos()
                    }
                }
            }
            observer(list_autocomplete) {
                it?.let { list ->
                    if(list.isNotEmpty()) {
                        val adapter = AutocompleteAdapter(this@ListProductosActivity, list)
                        binding.autoCompleteTextView.setAdapter(adapter)
                        viewModel.consultarByCatalogo(list[0].codigo)
                        binding.autoCompleteTextView.setText(list[0].name)
                        hideProgressBar()
                    }
                }
            }
        }
        binding.autoCompleteTextView.threshold = 1
        binding.autoCompleteTextView?.let {
            it.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                showProgressBar()
                viewModel.consultarByCatalogo(viewModel.list_autocomplete.value!![position].codigo)
            }

        }
        if(!checkPermissions(permission)) {
            requestPermission()
        } else {
            viewModel.consumeWSSitesMCO()
            showProgressBar()
        }
    }

    open fun msnError(msn : String) {
        hideProgressBar()
        mySnackbar = Snackbar.make(
            binding.container,
            msn!!, Snackbar.LENGTH_INDEFINITE
        )
        mySnackbar?.let {
            it.setActionTextColor(resources.getColor(R.color.colorPrimary))
            it.setAction(
                resources.getString(R.string.name_button_Snackbar),
                View.OnClickListener { })
            ViewUtils.setMaterialSnackbar(mySnackbar)
            val snackbarView = it.view
            val snackTextView =
                snackbarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
            snackTextView.maxLines = 3
            it.show()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (this@ListProductosActivity.isDestroyed()) { // or call isFinishing() if min sdk version < 17
                    return
                }
            }
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permiso in permission) {
                requestPermissionLauncher.launch(permission)
            }
        } else {
            if (!checkPermissions(permission)!!) {
                ActivityCompat.requestPermissions(
                    this@ListProductosActivity, permission,
                    CODE_PERMISSION
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissions.size != 0) {
            if (permissions.size >= permission.size) {
                var allAcept = true
                for (valuePermissions in grantResults) {
                    if (valuePermissions != PackageManager.PERMISSION_GRANTED) {
                        allAcept = false
                        break
                    }
                }
                if (allAcept) {
                    viewModel.consumeWSSitesMCO()
                    showProgressBar()
                } else {
                    Toast.makeText(
                        this@ListProductosActivity,
                        R.string.all_permission_are_required,
                        Toast.LENGTH_SHORT
                    ).show()
                    Snackbar.make(
                        binding.container, R.string.all_permission_are_required,
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("Ok") {
                        ActivityCompat.requestPermissions(
                            this@ListProductosActivity, permission,
                            CODE_PERMISSION
                        )
                    }.show()
                }
            } else {
                Toast.makeText(
                    this@ListProductosActivity,
                    R.string.all_permission_are_required,
                    Toast.LENGTH_SHORT
                ).show()
                Snackbar.make(
                    binding.container, R.string.all_permission_are_required,
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Ok") {
                    ActivityCompat.requestPermissions(
                        this@ListProductosActivity, permission,
                        CODE_PERMISSION
                    )
                }.show()
            }
        }
    }

    open fun hideProgressBar() {
        binding.progressBar.visibility = GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    open fun showProgressBar() {
        binding.progressBar.visibility = VISIBLE
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}