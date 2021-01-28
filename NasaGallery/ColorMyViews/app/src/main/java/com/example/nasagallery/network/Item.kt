package com.example.nasagallery.network

import com.google.gson.annotations.SerializedName

data class DataNasa(
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String = ""
)

data class LinksNasa(
    @SerializedName("href") val href: String = ""
)

data class Item(
    @SerializedName("data") val data: List<DataNasa> = listOf<DataNasa>(),
    @SerializedName("links") val links: List<LinksNasa> = listOf<LinksNasa>()
)

data class CollectionData(
    @SerializedName("href") val href: String = "",
    @SerializedName("items") val items: List<Item> = listOf<Item>()
)





