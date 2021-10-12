package org.smartmobiletech.euriskoapp.ui.listview

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.smartmobiletech.euriskoapp.R
import org.smartmobiletech.euriskoapp.modules.UserData

class ListFragment : Fragment(), ListAdapter.ClickListener {

    private lateinit var viewModel: ListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListAdapter
    private lateinit var data: ArrayList<UserData>
    private lateinit var refresh: SwipeRefreshLayout
    private var root: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if(root == null) {

            root = inflater.inflate(R.layout.fragment_list, container, false)

            activity?.let {
                viewModel = ViewModelProvider(it).get(ListViewModel::class.java)
            }
            data = ArrayList()

            initSwipe()
            initRecycler()

            getData()
        }

        return root!!
    }

    private fun initSwipe() {
        refresh = root!!.findViewById(R.id.swipe_refresh)
        refresh.setOnRefreshListener {
            getData()
        }
    }

    private fun initRecycler() {
        recyclerView = root!!.findViewById(R.id.recycler_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ListAdapter(data, this)
        recyclerView.adapter = adapter
    }

    private fun getData() {
        viewModel.getData(root!!.context).observe(this.viewLifecycleOwner, {
            data.clear()
            data.addAll(it)
            adapter.notifyDataSetChanged()
            refresh.isRefreshing = false
        })
    }

    override fun clicked(row: UserData) {
            viewModel.itemClicked(row)
    }
}