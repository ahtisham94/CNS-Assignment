package com.jobseekers.cns_assignment.coreBase

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * This baseview model can handle common functionality for viewmodels
 */
open class BaseViewmodel : ViewModel() {

    /**
     *  apiChannel send API request/response State
     *  apiFlow collect states for show hide dialog and also Error Handling
     */
    var apiChannel = Channel<APIState<Any>>()

    var apiFlow = apiChannel.receiveAsFlow()


}
