package com.example.shahzaibdevelopertask.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.shahzaibdevelopertask.R
import com.example.shahzaibdevelopertask.databinding.FragmentStartBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentStartBinding.inflate(inflater, container, false)

        loadInterstitialAd() // load interstitial ad

        binding.firstFragmentTextView.setOnClickListener {
            showInterstitialAd()
            it.findNavController().navigate(R.id.action_startFragment_to_firstFragment)
        }
        binding.secondFragmentTextView.setOnClickListener {
            showInterstitialAd()
            it.findNavController().navigate(R.id.action_startFragment_to_secondFragment)
        }
        binding.thirdFragmentTextView.setOnClickListener {
            showInterstitialAd()
            it.findNavController().navigate(R.id.action_startFragment_to_thirdFragment)
        }
        binding.fourthFragmentTextView.setOnClickListener {
            showInterstitialAd()
            it.findNavController().navigate(R.id.action_startFragment_to_fourthFragment)
        }
        return binding.root
    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(requireContext(), "ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
                Log.d("TAG", "Interstitial ad failed to load: $adError")
            }
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                Log.d("TAG", "Interstitial ad loaded successfully.")
            }
        })
    }

    private fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(requireActivity())
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet. Loading ad...")
            loadInterstitialAd()
        }
    }
}
