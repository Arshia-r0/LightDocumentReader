package com.arshia.lightdocumentreader.ui.viewer

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshia.lightdocumentreader.core.document.PDFBitmapConverter
import kotlinx.coroutines.launch

class ViewerScreenViewModel(
    private val uri: Uri,
    private val pdfBitmapConverter: PDFBitmapConverter,
) : ViewModel() {

    val loading = mutableStateOf(true)
    val renderedPages = mutableStateOf<List<Bitmap>>(emptyList())

    val scale = mutableFloatStateOf(1f)
    val offset = mutableStateOf(Offset.Zero)

    init {
        renderPdf()
    }

    private fun renderPdf() {
        viewModelScope.launch {
            renderedPages.value = pdfBitmapConverter.pdfToBitmaps(uri)
            loading.value = false
        }
    }

}
