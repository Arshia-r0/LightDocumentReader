package com.arshia.lightdocumentreader.core.data.repository

import com.arshia.lightdocumentreader.core.model.LDRPermission

interface PermissionManager {

    fun checkPermission(permission: LDRPermission): Boolean

    fun requestPermission(permission: LDRPermission)

}
