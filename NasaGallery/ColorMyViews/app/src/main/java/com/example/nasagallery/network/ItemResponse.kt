package com.example.nasagallery.network

import com.google.gson.annotations.SerializedName


data class CollectionNasa(
    @SerializedName("collection") val collection: CollectionData = CollectionData()
)
