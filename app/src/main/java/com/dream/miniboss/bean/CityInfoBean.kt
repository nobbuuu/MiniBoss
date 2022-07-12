package com.dream.miniboss.bean

data class CityInfoBean(
    val city: List<City>,
    val name: String
)

data class City(
    val area: List<String>,
    val name: String
)