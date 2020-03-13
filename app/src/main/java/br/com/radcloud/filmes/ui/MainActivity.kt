package br.com.radcloud.filmes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.radcloud.filmes.R
import br.com.radcloud.filmes.ui.adapter.MoviePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager.adapter = MoviePagerAdapter(this)
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.setText(
                if (position == 0)
                    R.string.tab_movies
                else
                    R.string.tab_favorites
            )
        }.attach()
    }
}
