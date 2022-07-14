package com.dream.miniboss.job.vm

import com.dream.miniboss.bean.AddressAreaBean
import com.dream.miniboss.net.Api
import com.tcl.base.common.BaseViewModel
import com.tcl.base.event.SingleLiveEvent

/**
 *@author tiaozi
 *@date   2022/1/26
 *description
 */
class SelectViewModel : BaseViewModel() {
    val addressData = SingleLiveEvent<List<AddressAreaBean>>()
    fun getAddress() {
        rxLaunchUI({
            val result = Api.getAdsPictures()
            addressData.postValue(result)
        })
    }
}