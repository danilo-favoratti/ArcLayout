package com.favoratti.arclayout.util

class CircularList<T>(private val items: List<T>) : Iterable<T> {
    private var currentIndex = 0

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            private var iterations = 0

            override fun hasNext(): Boolean {
                return iterations < items.size
            }

            override fun next(): T {
                if (items.isEmpty()) throw NoSuchElementException()
                val currentItem = items[currentIndex]
                this@CircularList.next()
                iterations++
                return currentItem
            }
        }
    }

    fun next(): T {
        currentIndex = (currentIndex + 1) % items.size
        return items[currentIndex]
    }
}