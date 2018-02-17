package com.joselbe.domain.model

//interfaces segregados (principio de SOLID)
interface ReadAgregate<T>{
    fun count(): Int
    fun all() : List<T>
    fun get(pos : Int) : T
}
interface WriteAgregate<T>{
    fun add(element : T)
    fun delete(pos : Int)
    fun delete(element : T)
}

interface Agregate<T>: ReadAgregate<T>, WriteAgregate<T>

interface Marker