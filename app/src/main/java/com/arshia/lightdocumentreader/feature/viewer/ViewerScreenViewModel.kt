package com.arshia.lightdocumentreader.feature.viewer

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshia.lightdocumentreader.core.document.PDFBitmapConverter
import kotlinx.coroutines.launch

class ViewerScreenViewModel(
    private val uri: Uri,
    private val pdfBitmapConverter: PDFBitmapConverter,
) : ViewModel() {

    val uiState = mutableStateOf<ViewerScreenUiState>(ViewerScreenUiState.Loading)
    val renderedPages = mutableStateOf<List<Bitmap>>(emptyList())

    init {
        renderPdf()
    }

    fun renderPdf() {
        viewModelScope.launch {
            renderedPages.value = pdfBitmapConverter.pdfToBitmaps(uri)
            uiState.value = ViewerScreenUiState.Success
        }
    }

}

sealed class ViewerScreenUiState {
    data object Loading : ViewerScreenUiState()
    data object Success : ViewerScreenUiState()
}
