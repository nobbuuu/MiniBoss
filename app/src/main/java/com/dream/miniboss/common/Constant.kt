package com.dream.miniboss.common


/**
 * @author : tiaozi
 * time : 2020/11/10 18:47
 */
object Constant {
    const val URL_KEY = "nretail-tsale-api-name"
    var LOGIN_SHOW_DIALOG = false
    var LOGIN_SHOW_MSG = ""
    const val PERMISSION_STORAGE_REQUEST_CODE = 0x00000012

    var isAccountLogin = false //密码登录
    var isNewest = false //是否最新版本号

    const val BACK_CLICK = "clickBack"

}

object ShoppingAddressConstant {
    const val KEY_SHOPPINGADDRESS_CHECK_UUID = "shippingAddress_uuid"
    const val KEY_INVOICEADDRESS_CHECK_UUID = "invoiceAddress_uuid"
    const val KEY_SHOPPINGADDRESS_FROM = "shippingAddress_from"
    const val ADDRESS_SETTING = "address_setting"
    const val ADDRESS_INVOICE = "address_invoice"
    const val ORDER_SELECT_ADDRESS = "order_select_address"
    const val ORDER_SELECT_ADDRESS_INVOICE = "order_select_address_invoice"
    const val ORDER_SELECT_ADDRESS_RECEIVEGOODS = "order_select_address_receivegoods"
    const val EDIT_ADDTRESS = "edit_addtress"
}

object BankConstant {
    const val KEY_BANK_DETAILS = "key_bank_details"
}

object CategoryDetailConstant {
    const val CATEGORY_TAB_KEY_WORD = "tab_key_word"
    const val CATEGORY_TAB_IS_SEARCH = "tab_is_search"
    const val GOODS_GROUP_ID = "goods_group_id"
    const val GOODS_GROUP_TITLE = "goods_group_title"
}

/**
 * 》》》WARNING《《《
 *
 * mmkv  key不能改动
 *
 * */
object MmkvConstant {
    const val PROTOCOL = "protocol"//协议内容
    const val KEY_USER_INFO = "key_user_info"


    const val KEY_DEBUG_CURRENT_TYPE = "current_type"
    const val KEY_ACCESS_TOKEN = "accessToken"
    const val KEY_REFRESH_TOKEN = "refreshToken"
    const val KEY_VISIBLE_INVITE = "visible_invite"
    const val KEY_VISIBLE_MYMONEY = "visible_mymoney"
    const val KEY_VISIBLE_PARTNER = "visible_partner"
    const val KEY_VISIBLE_WALLET = "visible_wallet"
    const val KEY_VISIBLE_PROFIT = "visible_profit"
    const val KEY_ACCOUNTID = "accountId"

    const val KEY_ADDRESS = "address"

}

@Deprecated("待废除，禁用SP，相关尽快迁移mmkv")
object SPConstant {
}

object IntentExtraKey {
    const val KEY_EXTRA_TAG = "extra_tag"
    const val KEY_EXTRA_WHERE = "extra_where"
    const val KEY_EXTRA_ORDERID = "extra_orderId"
    const val KEY_EXTRA_DELIVERYUUID = "extra_deliveryUuid"
    const val KEY_EXTRA_IMAGEURL = "extra_imageUrl"
    const val KEY_EXTRA_TITLEBODY = "extra_titleBody"
    const val KEY_EXTRA_BUYNUM = "extra_buyNum"
    const val KEY_EXTRA_PAYPRICE = "extra_payPrice"
    const val KEY_EXTRA_APPLYUUID = "extra_applyUuid"
    const val KEY_EXTRA_AFTER_SERVICE_NO = "extra_after_service_no"
    const val KEY_EXTRA_AFTER_SERVICE_UUID = "extra_after_service_uuid"
    const val KEY_EXTRA_LOGIN_PHONE = "extra_login_phone"
    const val KEY_EXTRA_LOGIN_CODE = "extra_login_code"
    const val KEY_EXTRA_GOODS_SIZE = "extra_goods_size"

    const val KEY_EXTRA_AUTHORIZATION_URL = "KEY_EXTRA_AUTHORIZATION_URL"
}

object EventConstant {
    const val KEY_GO_ORDERDETAIL = "key_go_orderdetail"
}

object AdapterItemType {
    const val PRODUCT_TYPE_LIST_SHORT = 0
    const val PRODUCT_TYPE_LIST_LONG = 1
    const val PRODUCT_TYPE_GIRD_SHORT = 2
    const val PRODUCT_TYPE_GIRD_LONG = 3
}