package com.firm.androidchallenge.mainActivity

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.firm.androidchallenge.myFavoritesFragment.MyFavoritesFragment
import com.firm.androidchallenge.R
import com.firm.androidchallenge.bookSelfFragment.BookselfFragment
import com.firm.androidchallenge.categoryDetailFragment.CategoryDetailFragment
import com.firm.androidchallenge.databinding.BindingMainActivity
import com.firm.androidchallenge.model.Category
import com.firm.androidchallenge.model.Sound

class MainActivity : AppCompatActivity() {

    lateinit var mBind: BindingMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBind = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        showFragment(MyFavoritesFragment())
        mBind.myFavoritesBtn.setOnClickListener { showFragment(MyFavoritesFragment()) }
        mBind.myBookselfBtn.setOnClickListener { showFragment(BookselfFragment()) }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(mBind.container.id, fragment).commit()
    }

    fun showCategoryDetailFragment(category: Category) {
        var args = Bundle()
        var fragment = CategoryDetailFragment()
        args.putString("CategoryId", category.categoryId)
        fragment.arguments = args
        showFragment(fragment)
    }



    fun voice(url: String?) {
        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
            }
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }
    }
}
