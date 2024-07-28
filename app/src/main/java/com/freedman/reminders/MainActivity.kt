package com.freedman.reminders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.freedman.reminders.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

private const val NUM_PAGES = 2


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = ScreenSlidePagerAdapter(this)


        TabLayoutMediator(binding.tabLayoutTop,binding.pager) {tab, position ->
            when (position){
                0 -> {
                    tab.text = "Passwords"
                    tab.icon = AppCompatResources.getDrawable(this, R.drawable.icon_lock)
                }

                1 ->{
                    tab.text = "General"
                    tab.icon = AppCompatResources.getDrawable(this, R.drawable.icon_general)
                }
            }
        }.attach()

    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                1 -> General()
                else -> Passwords()
            }
        }

    }
}


/*private lateinit var viewPager: ViewPager2
private lateinit var pagerAdapter : ScreenSlidePagerAdapter
private lateinit var tabLayout: TabLayout
private lateinit var binding: ActivityMainBinding


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)


    //binding.pager.adapter = ScreenSlidePagerAdapter(this)
    setVariables()
    //tabListener()
}

*//*private fun tabListener() {
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener { //listens for tab select and changes page
            override fun onTabSelected(tab: TabLayout.Tab) { viewPager.setCurrentItem(tab.position) }
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabReselected(p0: TabLayout.Tab?) {}
        })
        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback(){ //listens for page select and changes tab
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }
        })
    }*//*

    private fun setVariables() {


        viewPager = findViewById(R.id.pager)
        pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
        tabLayout = findViewById(R.id.tab_layout_top)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    */
/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 *//*
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                1 -> General2()
                else -> Passwords2()
            }
        }
    }
}

*/

