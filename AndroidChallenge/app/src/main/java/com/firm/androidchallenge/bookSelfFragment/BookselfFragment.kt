package com.firm.androidchallenge.bookSelfFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.firm.androidchallenge.R
import com.firm.androidchallenge.bookSelfFragment.adapter.BookselfListAdapter
import com.firm.androidchallenge.databinding.BindingBookselfFragment
import com.firm.androidchallenge.mainActivity.MainActivity
import com.firm.androidchallenge.model.Category
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class BookselfFragment : Fragment() {
    lateinit var mBind: BindingBookselfFragment
    val mViewModel: BookselfFragmentViewModel by viewModel()
    var mAdapter: BookselfListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBind = BindingBookselfFragment.inflate(inflater, container, false)
        mBind.lifecycleOwner = this
        mBind.viewModel = mViewModel
        mBind.isLoad = false
        mBind.toolbar.toolbarName.setText(resources.getText(R.string.menu_bookself))
        return mBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = BookselfListAdapter(object : BookselFragmentItemClick {
            override fun onItemClick(category: Category) {
                val mainActivity = activity as? MainActivity
                mainActivity?.showCategoryDetailFragment(category)
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
        mBind.bookselfRecyclerView.apply {
            adapter = mAdapter
        }
        mBind.isLoad = false
    }

}
