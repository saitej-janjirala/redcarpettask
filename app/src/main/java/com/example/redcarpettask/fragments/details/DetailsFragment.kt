package com.example.redcarpettask.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.redcarpettask.R


class DetailsFragment : Fragment() {
    private val detailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        DetailsFragmentArgs.fromBundle(requireArguments()).let {
            Glide.with(this.requireContext()).load(it.imageurl)
                .error(R.drawable.ic_launcher_background)
                .into(view.findViewById<ImageView>(R.id.detailsimage))
            view.findViewById<TextView>(R.id.detailsheadline).text=it.headline
            view.findViewById<TextView>(R.id.description).text=it.description
            view.findViewById<TextView>(R.id.contents).text=it.content

        }
        return view
    }

}