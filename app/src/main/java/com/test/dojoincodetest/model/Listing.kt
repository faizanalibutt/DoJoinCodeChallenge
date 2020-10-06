package com.test.dojoincodetest.model

import androidx.lifecycle.LiveData
import com.test.dojoincodetest.util.NetworkState

/**
 * Data class that is necessary for a UI to show a listing and interact w/ the rest of the system
 */
data class Listing<T>(
        // the LiveData of paged lists for the UI to observe
    val categoryList: LiveData<List<Category>>,
        // represents the network request status to show to the user
    val networkState: LiveData<NetworkState>,
        // retries any failed requests.
    val retry: () -> Unit)