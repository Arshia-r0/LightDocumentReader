package com.arshia.lightdocumentreader.core.model

import android.Manifest
import android.os.Build

enum class LDRPermissions(val value: String) {
    ReadExternalStorage(
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q)
            Manifest.permission.MANAGE_EXTERNAL_STORAGE
        else
            Manifest.permission.READ_EXTERNAL_STORAGE
    );
}
