package com.example.taskapp.dashboard.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemDatas {

    @SerializedName("status")
    @Expose
    var status : String? = ""


    @SerializedName("totalResults")
    @Expose
    var totalResults: Int = 0


    @SerializedName("articles")
    @Expose
    public var articles : List<ItemDatasList>? = ArrayList<ItemDatasList>();


}