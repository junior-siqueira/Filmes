package br.com.radcloud.filmes.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.radcloud.filmes.ui.fragments.MovieFavoritesFragment
import br.com.radcloud.filmes.ui.fragments.MovieListFragment

class MoviePagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0 )
            MovieListFragment()
        else
            MovieFavoritesFragment()
    }

}







