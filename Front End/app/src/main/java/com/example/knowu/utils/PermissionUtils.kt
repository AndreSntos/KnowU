package com.example.knowu.utils

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

import androidx.appcompat.app.AppCompatActivity








/**
 * Utility class for access to runtime permissions.
 */
object PermissionUtils {

    fun isPermissionGranted(
        grantPermissions: Array<String>, grantResults: IntArray,
        permission: String
    ): Boolean {
        for (i in grantPermissions.indices) {
            if (permission == grantPermissions[i]) {
                return grantResults[i] == PackageManager.PERMISSION_GRANTED
            }
        }
        return false
    }

    fun requestPermission(
        activity: AppCompatActivity, requestId: Int,
        permission: String, finishActivity: Boolean
    ) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            // Display a dialog with rationale.
        } else {
            // Location permission has not been granted yet, request it.
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestId)
        }
    }

}