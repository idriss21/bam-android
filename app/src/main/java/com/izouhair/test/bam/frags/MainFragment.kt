package com.izouhair.test.bam.frags

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.izouhair.test.bam.R
import com.izouhair.test.bam.adapters.ProjetAdapter
import com.izouhair.test.bam.adapters.ProjetAdapter.ClickListener
import com.izouhair.test.bam.network.SimpleRequest
import com.izouhair.test.bam.network.VolleySingleton
import com.izouhair.test.bam.other.Constances
import com.izouhair.test.bam.parser.ProjetParser
import com.izouhair.test.bam.persistance.ProjetController
import com.wuadam.awesomewebview.AwesomeWebView
import org.json.JSONArray
import org.json.JSONException
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), OnRefreshListener, ClickListener {
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var list: RecyclerView? = null
    private var adapter: ProjetAdapter? = null
    private fun setupViewAdapter() {
        //init adapter
        adapter = ProjetAdapter(ArrayList(), requireContext())
        //add a listener
        adapter!!.setClickListener(this)
        list!!.setHasFixedSize(true)
        list!!.layoutManager = LinearLayoutManager(context)
        list!!.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        list = root.findViewById(R.id.list)
        swipeRefreshLayout = root.findViewById(R.id.refresh)
        swipeRefreshLayout.setOnRefreshListener(this)

        //setup view adapter
        setupViewAdapter()

        // Inflate the layout for this fragment
        return root
    }

    override fun onStart() {
        super.onStart()
        //retrieve data from API
        loadDataFromAPI(false, false)
    }

    override fun onRefresh() {
        //retrieve data from API
        loadDataFromAPI(false, true)
    }

    @SuppressLint("ResourceAsColor")
    override fun itemClicked(view: View?, position: Int) {
        AwesomeWebView.Builder(requireActivity())
            .webViewCookieEnabled(true)
            .showMenuOpenWith(false)
            .fileChooserEnabled(true)
            .iconDefaultColorRes(R.color.white)
            .iconDefaultColor(R.color.white)
            .titleColor(
                ResourcesCompat.getColor(resources, R.color.teal_200, null)
            ).urlColor(
                ResourcesCompat.getColor(resources, R.color.teal_700, null)
            ).show(adapter!!.getItem(position)!!.html_url.toString())
    }

    override fun favoriteProj(view: View?, position: Int) {
        ProjetController.favoriteProjets(
            adapter!!.getItem(position), !adapter!!.getItem(position)!!.favorite
        )
    }

    private fun loadDataFromAPI(storeIntoDatabase: Boolean, refresh: Boolean) {
        swipeRefreshLayout.isRefreshing = true
        val queue: RequestQueue = VolleySingleton.Companion.getInstance(context)!!.requestQueue
        val request = SimpleRequest(
            Request.Method.GET,
            Constances.API_LINK + Constances.PAGINATION + "1", { response: String? ->
                try {
                    //display project data
                    Log.d("ProjectList", response!!)
                    val jsonArray = JSONArray(response)
                    val list = ProjetParser.getProjets(jsonArray)

                    //swipe to refresh
                    if (refresh) adapter!!.clear()
                    if (list!!.size > 0) adapter!!.addAllItems(list)

                    //update database
                    if (storeIntoDatabase) ProjetController.saveProjets(list)
                    swipeRefreshLayout!!.isRefreshing = false
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Log.e("ERROR", e.toString())
                }
            }) { error -> Log.e("ERROR", error.toString()) }
        request.retryPolicy = DefaultRetryPolicy(
            SimpleRequest.Companion.TIME_OUT,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        queue.add(request)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}