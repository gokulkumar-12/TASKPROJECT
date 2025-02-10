package com.example.taskapp.viewdashboard.view.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.taskapp.AppConstant
import com.example.taskapp.R
import com.example.taskapp.dashboard.model.ItemDatasList
import com.example.taskapp.utils.AndroidUtils

import kotlin.math.abs


class ViewDashBoardActivity : ComponentActivity(), View.OnClickListener {

    lateinit var txtDescription: TextView;

    lateinit var txtAuthor: TextView;

    lateinit var txtContent: TextView;

    lateinit var txtNoOfComment: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_dashboard);

        txtDescription = findViewById(R.id.txt_description) as TextView;
        txtAuthor = findViewById(R.id.txt_author) as TextView;
        txtContent = findViewById(R.id.txt_content) as TextView;

        txtDescription.setText("Description : "+intent.getStringExtra("DESCRIPTION"));
        txtAuthor.setText("Author : "+intent.getStringExtra("AUTHOR"));
        txtContent.setText("Content : "+intent.getStringExtra("CONTENT"));

        // val cleanedData = intent.getStringExtra("DESCRIPTION")?.replace("(\r\n|\n\r|\r\r|\n\n)+".toRegex(), "\n").trim { it <= ' ' }
        // txtDescription.setText(cleanedData)

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }


}