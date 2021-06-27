package com.izouhair.test.bam.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

class ViewPagerAdapter(manager: FragmentManager?) : FragmentStatePagerAdapter(
    manager!!
) {
    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private val mFragmentTitleList: MutableList<String> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    fun removeAll() {
        mFragmentList.clear()
    }

    fun getTitle(position: Int): String {
        return mFragmentTitleList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }
}