package com.arshia.lightdocumentreader.core.model

import android.Manifest
import android.os.Build

// todo custom methods for each permission

enum class LDRPermission(val value: String, val type: PermissionType) {
    ReadExternalStorage(
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q)
            Manifest.permission.MANAGE_EXTERNAL_STORAGE
        else
            Manifest.permission.READ_EXTERNAL_STORAGE,
        PermissionType.Special
    );
}

enum class PermissionType {
    Runtime, Special
}
