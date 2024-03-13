package com.example.shahzaibdevelopertask.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shahzaibdevelopertask.R
import com.example.shahzaibdevelopertask.databinding.FragmentFirstBinding
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MobileAds.initialize(requireContext())// initialize native ads
        val adLoader = AdLoader.Builder(requireContext(), "ca-app-pub-3940256099942544/2247696110")
            .forNativeAd { nativeAd ->
                val styles = NativeTemplateStyle.Builder().build()
                val template = view.findViewById<TemplateView>(R.id.my_template)
                template.setStyles(styles)
                template.setNativeAd(nativeAd)
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder().build()
            ).build()
        adLoader.loadAd(AdRequest.Builder().build())


        binding.splashImage.alpha = 0f
        binding.splashImage.animate().setDuration(6000).alpha(1f)
        startCountdownTimer()
    }

    private fun startCountdownTimer() {
        val countdownTimer = object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000 // update the countdown text
                binding.countdownTextView.text = secondsLeft.toString()
            }
            override fun onFinish() {
                /*startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()*/
            }
        }
        countdownTimer.start()// start the countdown timer
    }
}