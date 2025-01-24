package com.arshia.lightdocumentreader.core.data.repository.imp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.arshia.lightdocumentreader.core.data.repository.PermissionManager
import com.arshia.lightdocumentreader.core.model.LDRPermission

class LDRPermissionManager(
    private val context: Context,
) : PermissionManager {

    override fun checkPermission(permission: LDRPermission): Boolean {
        // todo only works for storage permission
        return if (Build.VERSION.SDK_INT > 29) {
            Environment.isExternalStorageManager()
        } else {
            ContextCompat.checkSelfPermission(
                context,
                permission.value
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun requestPermission(permission: LDRPermission) {
        if (Build.VERSION.SDK_INT > 29) {
            val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } else {

        }
    }

}
