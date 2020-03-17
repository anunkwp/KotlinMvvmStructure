package com.nankung.kotlinmvvmstructure.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nankung.common.module.base.URLService
import com.nankung.common.module.base.interfaces.BaseRecyclerViewItemTouchListener
import com.nankung.common.module.base.mvvm.fragment.AppMvvmFragment
import com.nankung.common.module.dialog.hideLoading
import com.nankung.common.module.dialog.showGradientLoading
import com.nankung.kotlinmvvmstructure.R
import com.nankung.kotlinmvvmstructure.ui.dashboard.adapter.RecyclerViewPeopleAdapter
import com.nankung.kotlinmvvmstructure.util.obtainPeopleViewModel
import com.nankung.network.model.response.result.PeopleResult
import com.nankung.network.remote.Status
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : AppMvvmFragment() {

    private lateinit var viewModel: DashboardViewModel
    private var peopleAdapter: RecyclerViewPeopleAdapter? = null
    companion object {
        fun newInstance(): DashboardFragment {
            val fragment = DashboardFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = obtainViewModel()
        viewModel.initKeys(URLService.tmdbApiKey)
        initialObServe()
    }

    private fun obtainViewModel(): DashboardViewModel = obtainPeopleViewModel(DashboardViewModel::class.java)

    private fun initialObServe() {
        viewModel.requestPeopleResource.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    response.data.let {
                        initRecyclerView(it)
                        hideLoading()
                        //Log.d("RoomNow", "$it")
                    }
                }
                Status.LOADING -> {
                    showGradientLoading()
                }
                Status.ERROR -> {
                    response.message.let { error ->
                        Toast.makeText(activity,"$error",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun callRequestCombined(){
        viewModel.requestCombined.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    response.data.let {
                        hideLoading()
                        //Log.d("RoomNow", "$it")
                    }
                }
                Status.LOADING -> {
                    showGradientLoading()
                }
                Status.ERROR -> {
                    response.message.let { error ->
                        Toast.makeText(activity,"$error",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun initRecyclerView(data: List<PeopleResult>?) {
        recyclerviewPeople.apply {
            peopleAdapter = RecyclerViewPeopleAdapter(context, data!!)
            layoutManager = LinearLayoutManager(context)
            adapter = SlideInRightAnimationAdapter(peopleAdapter).apply {
                setDuration(500)
                setInterpolator(OvershootInterpolator())
                setFirstOnly(false)
            }
            peopleAdapter!!.notifyDataSetChanged()
            addOnItemTouchListener(
                BaseRecyclerViewItemTouchListener(this,
                    object : BaseRecyclerViewItemTouchListener.ClickListener {
                        override fun onClick(view: View, position: Int) {
                            viewModel.initIdCombined(URLService.tmdbApiKey,data[position].id.toString())


                            callRequestCombined()
                            Toast.makeText(
                                context!!,
                                "${data[position].id}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            )
            scheduleLayoutAnimation()
            //Log.d("RoomNow", " $data")
        }
    }

}
