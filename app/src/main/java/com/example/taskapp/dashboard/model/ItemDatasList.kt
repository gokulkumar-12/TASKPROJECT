package com.example.taskapp.dashboard.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ItemDatasList : Serializable{


    @SerializedName("source")
    @Expose
    var source : Source? = null

    @SerializedName("author")
    @Expose
    var author : String? = ""

    @SerializedName("title")
    @Expose
    var title : String? = ""

    @SerializedName("description")
    @Expose
    var description : String? = ""

    @SerializedName("url")
    @Expose
    var url : String? = ""

    @SerializedName("urlToImage")
    @Expose
     var urlToImage : String? = ""

    @SerializedName("publishedAt")
    @Expose
    var publishedAt : String? = ""

    @SerializedName("content")
    @Expose
     var content : String? = ""

}