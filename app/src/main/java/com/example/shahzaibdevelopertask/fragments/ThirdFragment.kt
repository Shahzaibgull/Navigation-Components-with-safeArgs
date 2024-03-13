package com.example.shahzaibdevelopertask.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.shahzaibdevelopertask.databinding.FragmentThirdBinding
import com.example.shahzaibdevelopertask.retrofit.JsonApiResponse_DataClass
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import java.io.ByteArrayOutputStream

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private val args: ThirdFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentThirdBinding.inflate(inflater, container, false)

        val sharedPreferences1 = requireActivity().getSharedPreferences("check", Context.MODE_PRIVATE)
        val checkSignInStatus = sharedPreferences1.getInt("Key1", 0)

        if (checkSignInStatus ==1) {
            binding.selectedTitleTextView.isVisible=true
            binding.selectedImageView.isVisible=true
            binding.adView.isVisible=true
            binding.imageText.isVisible=true
            binding.btnShare.isVisible=true
            binding.sampleNote.isVisible=false

            val selectedItem: JsonApiResponse_DataClass = args.selectedItem
            Glide.with(requireContext()).load(selectedItem.thumbnailUrl).into(binding.selectedImageView)  // get data from item click
            binding.selectedTitleTextView.text = selectedItem.title

            MobileAds.initialize(requireContext()) {}   //banner ad
            val adRequest = AdRequest.Builder().build()
            binding.adView.loadAd(adRequest)

            binding.btnShare.setOnClickListener {
                shareImage(selectedItem.thumbnailUrl)
            }
        }
        val editor1 = sharedPreferences1.edit()
        editor1.putInt("Key1",55)
        editor1.apply()

        return binding.root
    }
    private fun shareImage(imageUrl: String) {  // share image to other apps
        val sendIntent=Intent().apply {
            action=Intent.ACTION_SEND
            type="text/plain"  //image/*
            putExtra(Intent.EXTRA_TEXT,imageUrl)
        }
        val shareIntent=Intent.createChooser(sendIntent,null)
        startActivity(shareIntent)
    }
}