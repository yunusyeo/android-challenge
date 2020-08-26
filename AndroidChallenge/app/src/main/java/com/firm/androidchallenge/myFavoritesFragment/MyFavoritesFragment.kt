package com.firm.androidchallenge.myFavoritesFragment


import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.firm.androidchallenge.R
import com.firm.androidchallenge.databinding.BindingMyFavoritesFragment
import com.firm.androidchallenge.install.Application
import com.firm.androidchallenge.mainActivity.MainActivity
import com.firm.androidchallenge.model.Sound
import com.firm.androidchallenge.myFavoritesFragment.adapter.MyFavoritesListAdapter
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MyFavoritesFragment : Fragment() {
    lateinit var mBind: BindingMyFavoritesFragment
    val mViewModel: MyFavoritesFragmentViewModel by viewModel()
    var mAdapter: MyFavoritesListAdapter? = null
    var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBind = BindingMyFavoritesFragment.inflate(inflater, container, false)
        mBind.lifecycleOwner = this
        mBind.viewModel = mViewModel
        mBind.isLoad = false
        mBind.toolbar.toolbarName.setText(resources.getText(R.string.menu_favorites))
        return mBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaPlayer = MediaPlayer()
        mAdapter = MyFavoritesListAdapter(object : DeleteFavorite {
            override fun onItemClick(sound: Sound) {
                mViewModel.deleteFavoriteData(sound)

            }
        })
        mBind.isLoad = true
        mViewModel.data.observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter?.submitList(it)
                setRecylerView()
                Application.favoriteList = it
            }
        })
    }

    fun setRecylerView() {
        mBind.myFavoritesRecyclerView.apply {
            adapter = mAdapter
        }
        mBind.isLoad = false
    }
}
