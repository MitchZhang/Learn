package com.mitch.learnandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.mitch.learnandroid.R
import com.mitch.learnandroid.databinding.FragmentBannerDialogBinding

/**
 * @Class: BannerDialogFragment
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/4/21
 */
class BannerDialogFragment : DialogFragment() {

    private lateinit var mBinding: FragmentBannerDialogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate<FragmentBannerDialogBinding>(
            inflater,
            R.layout.fragment_banner_dialog,
            container,
            false
        )
        return mBinding.root
    }
}