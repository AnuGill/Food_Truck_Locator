package com.android.example.foodtrucklocator


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.example.foodtrucklocator.databinding.LauncherBinding

/**
 * A simple [Fragment] subclass.
 */
class Launcher : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<LauncherBinding>(
            inflater,
            R.layout.launcher, container, false
        )

        binding.registerButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(LauncherDirections.actionTitleFragmentToRegisterFragment())
        }
        binding.loginButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(LauncherDirections.actionTitleFragmentToLoginFragment())
        }
        binding.guestButton.setOnClickListener { view: View ->
            view.findNavController().navigate(LauncherDirections.actionLauncherToMapsActivity())
        }

        binding.listButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(LauncherDirections.actionLauncherToListMap3())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

}



