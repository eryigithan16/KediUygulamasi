package com.example.catapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapp.R
import com.example.catapp.data.model.Cat
import com.example.catapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by viewModels()
    private lateinit var dataBinding : FragmentHomeBinding
    lateinit var catsAdapter: HomeCatsAdapter
    lateinit var catList : List<Cat>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return dataBinding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        catsAdapter = HomeCatsAdapter(viewModel.cats.value.onFavouriteChanged, CatClickListenerImpl(view))
        dataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = catsAdapter
        }

        viewModel.getListFromRemoteRepo()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.cats.collect {
                    catList = it.catsItems
                    catsAdapter.submitList(catList)
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)

    }
}