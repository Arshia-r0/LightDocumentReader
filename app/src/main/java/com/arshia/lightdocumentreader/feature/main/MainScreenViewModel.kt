package com.arshia.lightdocumentreader.feature.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshia.lightdocumentreader.core.data.repository.DocumentRepository
import com.arshia.lightdocumentreader.core.data.repository.PermissionChecker
import com.arshia.lightdocumentreader.core.model.LDRPermissions
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val documentRepository: DocumentRepository,
    private val permissionChecker: PermissionChecker,
) : ViewModel() {

    val readExternalStoragePermission = LDRPermissions.ReadExternalStorage
    val requestPermission = mutableStateOf(false)

    val documents = documentRepository.documents
        .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5000),
        )

    init {
        refresh()
    }
   
    private fun checkPermission(): Boolean =
        permissionChecker.checkPermission(readExternalStoragePermission.value)

    fun refresh() {
        if (!checkPermission()) {
            requestPermission.value = true
        }
        viewModelScope.launch {
            documentRepository.refresh()
        }
    }

}
