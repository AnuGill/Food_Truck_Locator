package com.android.example.foodtrucklocator


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.android.example.foodtrucklocator.databinding.AccountBinding

/**
 * A simple [Fragment] subclass.
 */
class Account : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<AccountBinding>(inflater,
            R.layout.account,container,false)
        return binding.root
        }
    }



