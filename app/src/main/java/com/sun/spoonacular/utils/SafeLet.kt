package com.sun.spoonacular.utils

inline fun <T, E, R> safeLet(a: T?, b: E?, block: (T, E) -> R) {
    if (a != null && b != null) block.invoke(a, b)
}
