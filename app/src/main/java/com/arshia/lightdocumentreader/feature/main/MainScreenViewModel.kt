package com.arshia.lightdocumentreader.feature.main

import androidx.lifecycle.ViewModel

class MainScreenViewModel(
//    private val documentRepository: DocumentRepository,
//    private val permissionManager: PermissionManager,
) : ViewModel() {

//    val documents = documentRepository.documents
//        .stateIn(
//            scope = viewModelScope,
//            initialValue = emptyList(),
//            started = SharingStarted.WhileSubscribed(5000),
//        )
//
//    init {
//        refresh()
//    }
//   
//    private fun checkPermission(): Boolean =
//        permissionManager.checkPermission(readExternalStoragePermission)
//
//    fun refresh() {
//        if (!checkPermission()) {
//            showPermissionDialog.value = true
//        } else {
//            viewModelScope.launch {
//                documentRepository.refresh().collect {}
//            }
//        }
//    }
//
//    fun requestPermission() {
//        permissionManager.requestPermission(readExternalStoragePermission)
//    }
}
