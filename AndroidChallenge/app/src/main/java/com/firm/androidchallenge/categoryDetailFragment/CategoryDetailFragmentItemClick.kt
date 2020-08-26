package com.firm.androidchallenge.categoryDetailFragment

import com.firm.androidchallenge.model.Sound

interface CategoryDetailFragmentItemClick {
    fun onItemClick(addListCheck:Boolean,favorite: Sound)
}