package com.example.taskapp.dashboard.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.AppConstant
import com.example.taskapp.R
import com.example.taskapp.dashboard.model.ItemDatasList
import com.example.taskapp.dashboard.view.adapter.DashBoardAdapter
import com.example.taskapp.dashboard.viewmodel.ViewModel
import com.example.taskapp.dashboard.viewmodel.ViewModelFactory
import com.example.taskapp.utils.ApiResult
import com.example.taskapp.viewdashboard.view.activity.ViewDashBoardActivity

class DashBoardActivity :  ComponentActivity(), View.OnClickListener,
DashBoardAdapter.OnItemClickListener {

    lateinit var progressBar: ProgressBar;

    lateinit var recyclerView: RecyclerView;

    lateinit var nestedScrollView: NestedScrollView;

    lateinit var adapter: DashBoardAdapter;

    private var viewModel: ViewModel? = null

    private var itemDataList: List<ItemDatasList>? = ArrayList<ItemDatasList>();

    lateinit var layoutManager: LinearLayoutManager

    lateinit var txtNoData: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        recyclerView = findViewById(R.id.rv_item) as RecyclerView

        nestedScrollView = findViewById(R.id.nested_scroll_view) as NestedScrollView

        progressBar = findViewById(R.id.progress_bar) as ProgressBar

        txtNoData = findViewById(R.id.txt_no_data) as TextView

        initViewModel();

        setRecyclerLayoutManager();

        makeAPIForItemData();


    }

    private fun makeAPIForItemData() {
        viewModel?.apiResponse?.observe(this, Observer { result ->
            when (result) {
                is ApiResult.Loading -> {
                    // Show loading indicator
                    showProgressDialog()
                }

                is ApiResult.Success -> {
                    // Handle successful response
                    val data = result.data
                    data.articles?.let { setRecyclerListItems(it) }
                    hideProgressDialog()
                }

                is ApiResult.Error -> {
                    // Handle error
                    val error = result.exception
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show()
                    hideProgressDialog()
                }
            }
        })

        // Trigger API call
        viewModel!!.fetchData(AppConstant.Q, AppConstant.FROM,AppConstant.SORT_BY,AppConstant.API_KEY);
    }

    private fun setRecyclerListItems(it: List<ItemDatasList>) {
        itemDataList = it
        adapter = DashBoardAdapter(
            applicationContext,
            it as ArrayList<ItemDatasList>
        )
        adapter.setOnItemClickListener(this);
        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter
    }

    private fun setRecyclerLayoutManager() {
        layoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layoutManager)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(this))
            .get(ViewModel::class.java)
        viewModel!!.init()
    }

    private fun hideProgressDialog() {
        progressBar.visibility = View.GONE
    }

    private fun showProgressDialog() {
        progressBar.visibility = View.VISIBLE
    }
    override fun onClick(view: View?) {

    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, ViewDashBoardActivity::class.java)
        intent.putExtra("DESCRIPTION", itemDataList?.get(position)?.description);
        intent.putExtra("CONTENT", itemDataList?.get(position)?.content);
        intent.putExtra("AUTHOR", itemDataList?.get(position)?.author);
        startActivity(intent)
    }
}