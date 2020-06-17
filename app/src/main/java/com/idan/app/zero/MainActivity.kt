package com.idan.app.zero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.idan.app.module.books.BooksFragment
import com.idan.app.module.movie.MovieFragment
import com.idan.app.module.music.MusicFragment
import com.idan.app.module.sports.SportFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * createUser : Administrator
 * createDate : 2020/6/17
 * remark     :
 */
class MainActivity : AppCompatActivity() {

    var tFragments: ArrayList<Fragment> = ArrayList()
    var tabPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        tFragments.add(MovieFragment())
        tFragments.add(SportFragment())
        tFragments.add(MusicFragment())
        tFragments.add(BooksFragment())
        vp.adapter = MainAdapter(tFragments, this)
        vp.isUserInputEnabled = false
        nv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_movie -> checkTab(0)
                R.id.nav_sports -> checkTab(1)
                R.id.nav_music -> checkTab(2)
                R.id.nav_books -> checkTab(3)
            }
            true
        }
    }

    fun checkTab(position: Int) {
        vp.setCurrentItem(position, Math.abs(position - tabPosition) <= 1)
        tabPosition = position
    }
}


class MainAdapter(var tFragment: ArrayList<Fragment>, activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = tFragment.size

    override fun createFragment(position: Int): Fragment = tFragment[position]
}