package com.test.dojoincodetest.model
import com.google.gson.annotations.SerializedName


data class SubCategories (

	@SerializedName("id") val id : String,
	@SerializedName("title") val title : String
)