package com.example.sergio.challenge.presentation.list.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sergio.challenge.domain.list.entity.Producto
import com.example.sergio.challenge.domain.list.usecase.ListProductsUseCase
import com.example.sergio.challenge.presentation.list.adapter.AutocompleteData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListViewModel @Inject constructor(private val listProductsUseCase : ListProductsUseCase) : ViewModel() {

    private val _success_producto = MutableLiveData<Boolean>(false)
    val success_producto: LiveData<Boolean> get() = _success_producto

    private val _error_msn = MutableLiveData<String>("")
    val error_msn: LiveData<String> get() = _error_msn


    private val _list_productos = MutableLiveData<List<Producto>>(emptyList())
    val list_productos: LiveData<List<Producto>> get() = _list_productos

    fun consultarByCatalogo(codigo : String) {
        viewModelScope.launch {
            val responce = withContext(Dispatchers.IO) { listProductsUseCase.consultarByCatalogo(codigo) }
            if(!responce.isSuccess) {
                responce.error?.let {
                    setErrorMsn(it)
                }
            } else {
                responce.list?.let {
                    setlistProductos(it)
                }
            }
            setSuccessProducto(responce.isSuccess)
        }
    }

    fun setSuccessProducto(value : Boolean) {
        _success_producto.value = value
    }

    fun setlistProductos (list : List<Producto>) {
        _list_productos.value = list
    }

    fun setErrorMsn(value : String) {
        _error_msn.value = value
    }
}