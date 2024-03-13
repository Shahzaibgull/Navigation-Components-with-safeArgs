package com.example.shahzaibdevelopertask.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.navigation.findNavController
import com.example.shahzaibdevelopertask.R
import com.example.shahzaibdevelopertask.databinding.FragmentFourthBinding
import com.example.shahzaibdevelopertask.databinding.FragmentStartBinding

class FourthFragment : Fragment() {

    private lateinit var binding: FragmentFourthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentFourthBinding.inflate(inflater, container, false)

        binding.exitButton.setOnClickListener {
            showExitCustomDialogBox()
        }

        binding.backButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_fourthFragment_to_secondFragment)
        }
        return binding.root
    }

    private fun showExitCustomDialogBox(){      // custom dialog box
        val dialog= Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dailog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnYes : Button = dialog.findViewById(R.id.buttonYes)
        val btnNo : Button = dialog.findViewById(R.id.buttonNo)

        btnYes.setOnClickListener {
            requireActivity().finish() // exit the app
        }
        btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}