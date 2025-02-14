package com.arshia.lightdocumentreader.core.common.util

import androidx.compose.ui.Modifier

inline fun <reified T : Enum<T>> T.next(): T {
    val values = enumValues<T>()
    val nextOrdinal = (ordinal + 1) % values.size
    return values[nextOrdinal]
}

fun Modifier.condition(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: (Modifier.() -> Modifier) = { this },
): Modifier = then(if (condition) ifTrue() else ifFalse())
