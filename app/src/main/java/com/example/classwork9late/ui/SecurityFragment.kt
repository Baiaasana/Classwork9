package com.example.classwork9late.ui

import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.classwork9late.adapter.PasswordAdapter
import com.example.classwork9late.common.BaseFragment
import com.example.classwork9late.common.list
import com.example.classwork9late.databinding.FragmentSecurityBinding
import com.example.classwork9late.utility.Constants

class SecurityFragment : BaseFragment<FragmentSecurityBinding>(FragmentSecurityBinding::inflate) {

    private val passwordAdapter: PasswordAdapter = PasswordAdapter()

    override fun listeners() {

        var clickNumber = -1
        var enterPassword = ""
        val list2 = mutableListOf<View>(binding.pass1, binding.pass2, binding.pass3, binding.pass4)

        passwordAdapter.numberClick = {
            clickNumber += 1
            list2[clickNumber].setBackgroundColor(Color.GREEN)
            enterPassword += it.number.toString()
            if (enterPassword.length == 4) {
                if (Constants.PASSWORD.toString() == enterPassword) {
                    Toast.makeText(context, "success $enterPassword", Toast.LENGTH_SHORT).show()
                    enterPassword = ""
                    clickNumber = -1
                    reset(list2)
                } else {
                    enterPassword = ""
                    clickNumber = -1
                    list2.forEach {
                        it.setBackgroundColor(Color.RED)
                    }
                }
            }
        }
        passwordAdapter.touchCLick = {
            Toast.makeText(context, "touch", Toast.LENGTH_SHORT).show()
        }
        passwordAdapter.removeClick = {
            enterPassword = ""
            clickNumber = -1
            reset(list2)
        }
    }

    override fun init() {
        initRecycler()
        passwordAdapter.submitList(list)
    }

    private fun reset(list: List<View>) {
        list.forEach {
            it.setBackgroundColor(Color.WHITE)
        }
    }

    private fun initRecycler() {

        binding.rvNumbers.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = passwordAdapter
        }
    }

    override fun observers() {
    }
}
