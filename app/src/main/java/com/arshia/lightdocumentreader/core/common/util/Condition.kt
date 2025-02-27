package com.arshia.lightdocumentreader.core.common.util

import androidx.compose.ui.Modifier

fun Modifier.condition(
    condition: Boolean,
    ifFalse: (Modifier.() -> Modifier) = { this },
    ifTrue: Modifier.() -> Modifier,
): Modifier = then(if (condition) ifTrue() else ifFalse())
