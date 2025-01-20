package com.arshia.lightdocumentreader.core.data.repository

interface PermissionChecker {

    fun checkPermission(permission: String): Boolean

}
