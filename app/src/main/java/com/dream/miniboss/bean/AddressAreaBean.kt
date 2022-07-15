package com.dream.miniboss.bean

data class AddressAreaBean(
    val areaBaseList: List<AddressAreaBean>,
    val createTime: String,
    val grade: String,
    val id: String,
    val isAll: String,
    val name: String,
    val parentId: String,
    val shortName: String,
    val updateTime: String,
    var isSelect: Boolean
)
