package com.example.demoandroid.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoandroid.R
import com.example.demoandroid.common.AppUtils
import com.example.demoandroid.data.model.RelatedTopic
import com.example.demoandroid.databinding.FragmentListBinding
import com.example.demoandroid.viewmodel.CharViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharListFragment : Fragment() {

    private val userViewModel by activityViewModels<CharViewModel>()
    private lateinit var userAdapter: CharDataAdapter
    private lateinit var binding: FragmentListBinding
    private lateinit var listener: ItemListListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        userAdapter = CharDataAdapter { topic -> adapterOnClick(topic) }
        binding.list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = userAdapter
            setHasFixedSize(true)
        }
        fetchData()
        return binding.root
    }

    /**
     * fetch data from network
     */
    private fun fetchData() {
        if (AppUtils.isNetworkConnected(requireContext())) {
            binding.errorMessage.visibility = View.VISIBLE
            binding.errorMessage.text =
                requireActivity().resources.getString(R.string.network_error)
            return
        }
        userViewModel.charList.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visibility = View.GONE
            userAdapter.submitList(it.RelatedTopics)
        })
    }

    private fun adapterOnClick(topic: RelatedTopic) {
        userViewModel.select(topic)
        listener.itemClicked(topic)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.listener = activity as ItemListListener
    }

    internal interface ItemListListener {
        fun itemClicked(topic: RelatedTopic)
    }
}