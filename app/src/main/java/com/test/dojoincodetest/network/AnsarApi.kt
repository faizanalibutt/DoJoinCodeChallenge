package com.test.dojoincodetest.network

import com.test.dojoincodetest.model.CategoryBase
import com.test.shaadoow.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface AnsarApi {
    /**
     * Get the list of Categories from API
     */
    @GET(Constants.CATEGORY_END_POINT)
    fun getCategories() : Observable<CategoryBase>
}