package com.izouhair.test.bam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.izouhair.test.bam.adapters.ViewPagerAdapter
import com.izouhair.test.bam.frags.FavorisFragment
import com.izouhair.test.bam.frags.MainFragment
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    private var mViewPager: ViewPager? = null
    private var mToolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewPager = findViewById(R.id.viewpager)
        tabLayout = findViewById(R.id.tablayout)
        mToolbar = findViewById(R.id.toolbar)

        //realm init
        Realm.init(applicationContext)


        initToolbar()
        setupViewPager()
        setupTabLayoutListener()
    }

    private fun setupTabLayoutListener() {

        //tab with 2 columns
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Home"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Favorites"))
        mViewPager!!.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mViewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun initToolbar() {
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = "BAM"
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setDisplayUseLogoEnabled(true)
    }

    private fun setupViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.removeAll()
        viewPagerAdapter.addFragment(MainFragment.newInstance(), "") // index 0
        viewPagerAdapter.addFragment(FavorisFragment.newInstance(), "") // index 1
        mViewPager!!.adapter = viewPagerAdapter
        mViewPager!!.offscreenPageLimit = 2
    }
}