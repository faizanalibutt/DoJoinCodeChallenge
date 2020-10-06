package com.test.dojoincodetest.model

import com.google.gson.annotations.SerializedName


data class Category(

	@SerializedName("id") val id: String,
	@SerializedName("title") val title: String,
	@SerializedName("subCategories") val subCategories: List<SubCategories>,
	var expand: Boolean
)