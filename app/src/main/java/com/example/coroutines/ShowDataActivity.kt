package com.example.coroutines

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_show_data.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ShowDataActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapter
    private lateinit var progressDialog: ProgressDialog

    var dataList = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        createProgressDialog()

        //recycler View
        val linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL , false)
        recyclerView.layoutManager = linearLayoutManager
        adapter = CustomAdapter(this,dataList)
        recyclerView.adapter = adapter

        showData()
    }


    /**
     * Getting data from API using coroutine.
     */
    private fun showData() {
        lifecycleScope.launch {
           // progressDialog.show()

            val call = ApiClient.getClient.getData()
            call.enqueue(object : retrofit2.Callback<List<DataModel>>{
                override fun onResponse(
                    call: Call<List<DataModel>>,
                    response: Response<List<DataModel>>
                ) {
                    progressDialog.dismiss()
                    Log.i("ShowData","Data ${response.body()}")
                    dataList.addAll(response.body()?:ArrayList())
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                    progressDialog.dismiss()
                    Log.e("ShowData","Error is ${t.localizedMessage}")
                    showToast("Some Error Occurred while fetching data")
                }
            })
        }
    }

    private fun createProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading..")
        progressDialog.setMessage("Please wait while we fetch data..")
        progressDialog.setCancelable(false)
    }
}