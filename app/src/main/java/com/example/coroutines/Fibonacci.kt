package com.example.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun printFibonacciSeries(lastTerm: Int){
    var first = 0
    var second = 1
    var sum = 0

    for(i in 1..lastTerm){
        print("$first")
        sum = first + second
        first = second
        second = sum
        delay(300)
    }
}


suspend fun main(){
    //To run coroutine in background on main thread
    GlobalScope.launch {
        printFibonacciSeries(7)
    }
    println("Fibonacci Series:")
    //to keep JVM alive
    delay(2000)
}