package com.arshia.lightdocumentreader.feature.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshia.lightdocumentreader.core.data.repository.DocumentRepository
import com.arshia.lightdocumentreader.core.model.LDRPermission
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val documentRepository: DocumentRepository,
    private val permissionManager: com.arshia.lightdocumentreader.core.data.repository.PermissionManager
) : ViewModel() {

    val readExternalStoragePermission = LDRPermission.ReadExternalStorage
    val showPermissionDialog = mutableStateOf(false)

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
        permissionManager.checkPermission(readExternalStoragePermission)

    fun refresh() {
        if (!checkPermission()) {
            showPermissionDialog.value = true
        } else {
            viewModelScope.launch {
                documentRepository.refresh().collect {}
            }
        }
    }

    fun requestPermission() {
        permissionManager.requestPermission(readExternalStoragePermission)
    }

}
