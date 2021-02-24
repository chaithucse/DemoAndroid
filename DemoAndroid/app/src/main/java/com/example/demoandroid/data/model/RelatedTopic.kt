package com.example.demoandroid.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RelatedTopic(
    val FirstURL: String,
    val Icon: Icon,
    val Result: String,
    val Text: String
) : Serializable