package com.wisnumkt.capstone1.core.model.remote

data class RekomResponse(
	val data: List<DataItem>,
	val message: String,
	val status: String
)

data class DataItem(
	val name: String,
	val tentang: String,
	val produk: String,
	val phone: String,
	val rating: String,
	val id: String
)

