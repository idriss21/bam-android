package com.izouhair.test.bam.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.izouhair.test.bam.R
import com.izouhair.test.bam.adapters.ProjetAdapter
import com.izouhair.test.bam.persistance.ProjetController
import java.util.*

class FavorisFragment : Fragment() {
    private var list: RecyclerView? = null
    private var adapter: ProjetAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favoris, container, false)
        list = root.findViewById(R.id.list)
        setupViewAdapter()

        // Inflate the layout for this fragment
        return root
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            adapter!!.clear()
            adapter!!.addAllItems(ProjetController.projetsList(true))
        }
    }

    private fun setupViewAdapter() {
        //init adapter
        adapter = ProjetAdapter(ArrayList(), requireContext())
        list!!.setHasFixedSize(true)
        list!!.layoutManager = LinearLayoutManager(context)
        list!!.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
    }

    companion object {
        fun newInstance(): FavorisFragment {
            val fragment = FavorisFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}