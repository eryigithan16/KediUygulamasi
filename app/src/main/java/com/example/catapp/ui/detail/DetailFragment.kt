package com.example.catapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.catapp.R
import com.example.catapp.data.model.Cat
import com.example.catapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    lateinit var detailCat : Cat
    private lateinit var dataBinding : FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { // bu satır; eğer arguments'in içi doluysa demek oluyor.
            detailCat = DetailFragmentArgs.fromBundle(it).selectedCat!!
        }

        dataBinding.selectedCat = detailCat
    }

}