package com.arshia.lightdocumentreader.feature.main

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshia.lightdocumentreader.core.document.PDFBitmapConverter
import kotlinx.coroutines.launch

class MainScreenViewModel(
//    private val documentRepository: DocumentRepository,
//    private val permissionManager: PermissionManager,
    private val pdfBitmapConverter: PDFBitmapConverter,
) : ViewModel() {

    val pdfUri = mutableStateOf<Uri?>(null)
    val renderedPages = mutableStateOf<List<Bitmap>>(emptyList())

    fun renderPdf() {
        viewModelScope.launch {
            pdfUri.value?.let { uri ->
                renderedPages.value = pdfBitmapConverter.pdfToBitmaps(uri)
            }
        }
    }

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
