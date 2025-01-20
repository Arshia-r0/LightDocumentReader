package com.arshia.lightdocumentreader.core.data.repository.imp

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.arshia.lightdocumentreader.core.data.repository.PermissionChecker

class LDRPermissionChecker(
    private val context: Context,
) : PermissionChecker {

    override fun checkPermission(permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

}
