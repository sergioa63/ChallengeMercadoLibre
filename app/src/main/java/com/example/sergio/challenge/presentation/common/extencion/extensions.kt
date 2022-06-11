package com.example.sergio.challenge.presentation.common.extencion

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.sergio.challenge.R
import java.io.File

val READ_REQUEST_CODE = 222
val appPermissions = arrayOf(
    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) Manifest.permission.ACCESS_BACKGROUND_LOCATION
    else Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
)

fun Context.checkPermissions(permission : Array<String>) : Boolean{
    var isAllCheckP = true
    for (x in permission.indices) {
        if (ContextCompat.checkSelfPermission(
                this, permission[x]
            ) ==
            PackageManager.PERMISSION_GRANTED
        ) {
        } else {
            isAllCheckP = false
            break
        }
    }
    return isAllCheckP
}

fun Context.toast(msn: String, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, msn, length).show()

fun RecyclerView.ViewHolder.toast(msn: String) = itemView.context.toast(msn)

fun ViewGroup.inflate(@LayoutRes resource: Int): View =
    LayoutInflater.from(this.context).inflate(resource, this, false)


fun checkLocation(context: Context): Boolean {
    var isAllCheckP = true
    for (x in appPermissions.indices) {
        if (ContextCompat.checkSelfPermission(context, appPermissions[x]) != 0) {
            isAllCheckP = false
            break
        }
    }
    return isAllCheckP
}

inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(body: T.() -> Unit): T {
    return ViewModelProvider(this).get<T>().apply(body)
}

fun <T> LifecycleOwner.observer(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, androidx.lifecycle.Observer(observer))
}

fun ImageView.loadUrl(url: String) {
    val photoUri: Uri = Uri.fromFile(File(url))
    Glide.with(this).load(photoUri)
        .transforms( CenterCrop(),  RoundedCorners(20)). dontAnimate().centerCrop()
        .into(this)
}




