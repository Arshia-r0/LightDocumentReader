package com.roseoj.roseapp.core.common.util

inline fun guard(closure: () -> Boolean) {
    if (closure()) return
}
