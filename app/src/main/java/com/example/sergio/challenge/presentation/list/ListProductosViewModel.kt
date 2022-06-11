package com.example.sergio.challenge.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sergio.challenge.domain.list.entity.Producto
import com.example.sergio.challenge.domain.list.usecase.ListProductsUseCase
import com.example.sergio.challenge.presentation.common.utils.Event
import com.example.sergio.challenge.presentation.list.adapter.AutocompleteData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListProductosViewModel @Inject constructor(private val listProductsUseCase : ListProductsUseCase): ViewModel(){

    private val _error_msn = MutableLiveData<String>("")
    val error_msn: LiveData<String> get() = _error_msn

    private val _success_select_product = MutableLiveData<Boolean>(false)
    val success_select_product: LiveData<Boolean> get() = _success_select_product

    private val _success_producto = MutableLiveData<Boolean>(false)
    val success_producto: LiveData<Boolean> get() = _success_producto

    private val _success_catalogo = MutableLiveData<Event<Boolean>>(Event(false))
    val success_catalogo: LiveData<Event<Boolean>> get() = _success_catalogo

    private val _list_autocomplete = MutableLiveData<List<AutocompleteData>>(emptyList<AutocompleteData>() )
    val list_autocomplete: LiveData<List<AutocompleteData>> get() = _list_autocomplete

    private val _list_productos = MutableLiveData<List<Producto>>(emptyList())
    val list_productos: LiveData<List<Producto>> get() = _list_productos

    private val _product_select = MutableLiveData<Producto>(null)
    val product_select: LiveData<Producto> get() = _product_select

    fun consultarByCatalogo(codigo : String) {
        viewModelScope.launch {
            val responce = withContext(Dispatchers.IO) { listProductsUseCase.consultarByCatalogo(codigo) }
            setSuccessProducto(responce.isSuccess)
            if(!responce.isSuccess) {
                responce.error?.let {
                    setErrorMsn(it)
                }
            } else {
                responce.list?.let {
                    setlistProductos(it)
                }
            }
            setSuccessProducto(false)
        }
    }
    fun consumeWSSitesMCO() {
        viewModelScope.launch {
            val responce = withContext(Dispatchers.IO) {listProductsUseCase.getDataWS()}
            if(!responce.isSuccess) {
                responce.error?.let {
                    setErrorMsn(it)
                }
            }
            setSuccessCatalogo(responce.isSuccess)
        }
    }

    fun getListCatalogos() {
        viewModelScope.launch {
            val responce = withContext(Dispatchers.IO) {listProductsUseCase.getListCatalogo()}
            setListAutocomplete(responce)
        }
    }

    fun consultarDetalle (codigo: String) {
        viewModelScope.launch {
            val responce = withContext(Dispatchers.IO) { listProductsUseCase.consultarDetalle(codigo) }
            if(!responce.isSuccess) {
                responce.error?.let {
                    setErrorMsn(it)
                }
            }
            responce.descripcionc?.let {  descrip ->
                _product_select.value?.let {
                    it.descrip = descrip
                    _product_select.value = _product_select.value
                }
            }
        }
    }

    fun setSuccessCatalogo(value : Boolean) {
        _success_catalogo.value = Event(value)
    }

    fun setErrorMsn(value : String) {
        _error_msn.value = value
    }

    fun setListAutocomplete(value : List<AutocompleteData>) {
        _list_autocomplete.value = value
    }

    fun setlistProductos (list : List<Producto>) {
        _list_productos.value = list
    }

    fun setSuccessProducto(value : Boolean) {
        _success_producto.value = value
    }

    fun setProductSelect(producto: Producto) {
        _product_select.value =producto
    }

}