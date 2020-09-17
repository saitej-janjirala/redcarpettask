package com.example.redcarpettask.fragments.main

import Articles
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.redcarpettask.R
import com.example.redcarpettask.adapters.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment(), MainAdapter.onMainItemClickListener {

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var articleslist:ArrayList<Articles>
    private lateinit var adapter: MainAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        articleslist= ArrayList()
        adapter=MainAdapter(this.requireContext(),articleslist,this)
        view.findViewById<RecyclerView>(R.id.mainrecyclerview).adapter=adapter
        retry()
        viewModel.responsestatus.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                when(it){
                    "success"->{
                    }
                    "error"->{
                        showdialog("Unexpected error occured")
                    }
                    "failed"->{
                        showdialog("Failed Unexpectedly")
                    }
                }
            }
        })
        viewModel.nointernet.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if(it){
                    showdialog("No internet connection")
                }
            }
        })
        viewModel.apiCallInprogress.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (it) {
                    mainprogressbar.visibility = View.VISIBLE
                } else {
                    mainprogressbar.visibility = View.GONE
                }
            }
        })
        viewModel.data.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                articleslist.clear()
                it.articles?.let { it1 -> articleslist.addAll(it1) }
               adapter= MainAdapter(this.requireContext(),articleslist,this)
                mainrecyclerview.adapter=adapter
            }
        })
        return view
    }

    override fun onclick(articles: Articles) {
        Log.i("clicked", "$articles")
        val action= articles.urlToImage?.let {
            articles.content?.let { it1 ->
                articles.description?.let { it2 ->
                    articles.title?.let{it3->
                        MainFragmentDirections.actionMainFragmentToDetailsFragment(
                            it, it2, it1,it3
                        )

                    }
                }
            }
        }
        if (action != null) {
            NavHostFragment.findNavController(myNavHostFragment).navigate(action)
        }
    }
    private fun retry(){
        viewModel.getdata()
    }
    private fun showdialog(msg:String){
        val dialog=AlertDialog.Builder(this.context)
            .setTitle("Error")
            .setMessage(msg)
            .setPositiveButton("Retry", null)
            .setCancelable(false)
            .show()
        val positiveButton =
            dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            dialog.dismiss()
            retry()
        }
    }
}