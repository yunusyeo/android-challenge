package com.firm.androidchallenge.categoryDetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.firm.androidchallenge.R
import com.firm.androidchallenge.categoryDetailFragment.adapter.CategoryDetailListAdapter
import com.firm.androidchallenge.databinding.BindingCategoryDetailFragment
import com.firm.androidchallenge.install.Application
import com.firm.androidchallenge.model.Category
import com.firm.androidchallenge.model.Sound
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.android.viewmodel.ext.android.viewModel


class CategoryDetailFragment : Fragment() {
    lateinit var mBind: BindingCategoryDetailFragment
    lateinit var categoryId: String
    val mViewModel: CategoryDetailFragmentViewModel by viewModel()
    var mAdapter: CategoryDetailListAdapter? = null
    var categoryDetailList: MutableList<Category>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBind = BindingCategoryDetailFragment.inflate(inflater, container, false)
        mBind.lifecycleOwner = this
        mBind.viewModel = mViewModel
        mBind.isLoad = false
        mBind.toolbar.toolbarName.setText(resources.getText(R.string.category_detail))
        return mBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryId = arguments?.getString("CategoryId").toString()
        categoryDetailList = ArrayList()
        mViewModel.fetchData(categoryId)

        mAdapter = CategoryDetailListAdapter(object : CategoryDetailFragmentItemClick {
            override fun onItemClick(addListCheck: Boolean, sound: Sound) {
                if (addListCheck) {
                    mViewModel.addFavoriteData(sound)
                } else {
                    mViewModel.deleteFavoriteData(sound)
                }
            }
        })

        mBind.isLoad = true
        mViewModel.data.observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter?.submitList(it)
                setRecylerView()
            }
        })
    }

    fun setRecylerView() {
        mBind.categoryDetailRecyclerView.apply {
            adapter = mAdapter
        }
        mBind.isLoad = false
    }

}
