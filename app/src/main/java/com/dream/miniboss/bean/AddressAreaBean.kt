package com.dream.miniboss.bean

data class AddressAreaBean(
    val areaBaseList: List<AreaBase>,
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

data class AreaBase(
    val areaBaseList: List<AreaBaseX>,
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

data class AreaBaseX(
    val areaBaseList: Any,
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