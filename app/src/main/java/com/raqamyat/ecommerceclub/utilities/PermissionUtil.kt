package com.raqamyat.ecommerceclub.utilities

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtil {
    fun isPermissionGranted(activity: Activity): Boolean {
        val read_permission =
            ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
        val write_permission =
            ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //        final int camera_permission = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        return read_permission == PackageManager.PERMISSION_GRANTED &&
                write_permission == PackageManager.PERMISSION_GRANTED
    }

    fun askForFileAndCameraPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity, arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ),
            2
        )
    }
}