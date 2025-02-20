package com.kmirror.mylibrary

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object LibUtils {
    fun libTestMethod() {
        GlobalScope.launch(Dispatchers.IO) {
            var miao = 0
            while (true) {
                delay(1000L)
                miao++
            }
        }
    }
}