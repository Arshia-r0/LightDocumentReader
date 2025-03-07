package com.arshia.lightdocumentreader.core.document

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PDFBitmapConverter(
    private val context: Context,
) {

    var renderer: PdfRenderer? = null

    suspend fun pdfToBitmaps(contentUri: Uri): List<Bitmap> =
        withContext(Dispatchers.IO) {
            renderer?.close()

            context.contentResolver
                .openFileDescriptor(contentUri, "r")
                ?.use { descriptor ->
                    with(PdfRenderer(descriptor)) {
                        renderer = this
                        return@withContext buildList {
                            (0 until pageCount).map { index ->
                                openPage(index).use { page ->

                                    val bitmap = Bitmap.createBitmap(
                                        page.width,
                                        page.height,
                                        Bitmap.Config.ARGB_8888
                                    )

                                    val canvas = Canvas(bitmap).apply {
                                        drawColor(Color.WHITE)
                                        drawBitmap(bitmap, 0f, 0f, null)
                                    }

                                    page.render(
                                        bitmap,
                                        null,
                                        null,
                                        PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY,
                                    )

                                    add(bitmap)
                                }
                            }
                        }
                    }
                }

            return@withContext emptyList()
        }

}
