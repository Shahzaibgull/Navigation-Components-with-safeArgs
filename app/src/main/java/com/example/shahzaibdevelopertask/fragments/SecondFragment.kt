package com.example.shahzaibdevelopertask.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.shahzaibdevelopertask.R
import com.example.shahzaibdevelopertask.databinding.FragmentFirstBinding
import com.example.shahzaibdevelopertask.databinding.FragmentSecondBinding
import com.example.shahzaibdevelopertask.retrofit.JsonApiResponse_DataClass
import com.example.shahzaibdevelopertask.retrofit.JsonApi_Interface
import com.example.shahzaibdevelopertask.retrofit.RecyclerView_Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SecondFragment : Fragment(), RecyclerView_Adapter.OnItemClickListener {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val data = MutableLiveData<List<JsonApiResponse_DataClass>>()
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSecondBinding.inflate(inflater, container, false)

        val jsonApi = retrofit.create(JsonApi_Interface::class.java)
        //val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        getData(jsonApi)

        data.observe(viewLifecycleOwner) {
            val adapter = RecyclerView_Adapter(it,requireContext(), this)
            binding.recycler.adapter = adapter
        }

        return binding.root
    }

    fun getData(jsonApi: JsonApi_Interface) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = jsonApi.getPhotos()
            if (response.isSuccessful) {

                data.postValue(response.body())
            }
        }
    }

    override fun onItemClick(jsonapiresponseDataclass: JsonApiResponse_DataClass) {  // click on recycler view items
        val sharedPreferences1 = requireActivity().getSharedPreferences("check", Context.MODE_PRIVATE)
        val editor = sharedPreferences1.edit()
        editor.putInt("Key1", 1)
        editor.apply()

        val action = SecondFragmentDirections.actionSecondFragmentToThirdFragment(jsonapiresponseDataclass)
        findNavController().navigate(action)
    }
}