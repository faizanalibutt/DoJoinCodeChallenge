package com.test.dojoincodetest.model
import com.google.gson.annotations.SerializedName


data class CategoryBase (

	@SerializedName("result") val category : List<Category>,
	@SerializedName("errorMessage") val errorMessage : String,
	@SerializedName("paging") val paging : String,
	@SerializedName("serverTime") val serverTime : String
)