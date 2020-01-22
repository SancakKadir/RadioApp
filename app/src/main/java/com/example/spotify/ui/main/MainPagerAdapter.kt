package com.example.spotify.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.spotify.R
import com.example.spotify.ui.favorites.FavoriteFragment
import com.example.spotify.ui.radios.RadiosFragment
import java.lang.IllegalStateException

class MainPagerAdapter(context:Context,fragmentManager:FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val tabs=context.applicationContext.resources.getStringArray(R.array.tabs)

    override fun getItem(position: Int): Fragment {
        return when (position) {
            TAB_INDEX_RADİOS -> RadiosFragment.newInstance()
            TAB_INDEX_FAVORİTES -> FavoriteFragment.newInstance()
            else -> throw IllegalStateException("can not find fragment.position:$position")
        }
    }
    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].toUpperCase()
    }

    companion object{
        private const val TAB_INDEX_RADİOS=0
        private const val TAB_INDEX_FAVORİTES=1
    }

}