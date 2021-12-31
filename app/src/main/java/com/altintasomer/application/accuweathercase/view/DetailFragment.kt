package com.altintasomer.application.accuweathercase.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.altintasomer.application.accuweathercase.R
import com.altintasomer.application.accuweathercase.databinding.FragmentDetailBinding
import com.altintasomer.application.accuweathercase.viewmodel.GeneralViewModelModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val viewModel: GeneralViewModelModel by activityViewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view = view)
    }

    private fun init(view: View) {
        val binding = FragmentDetailBinding.bind(view)
        /**
         * Tıklanan konumun ismini frament ın argumanına tanımlayıp burada alıyoruz.
         * */
        val name = args.name


        /**
         * burada List fragment da tıkladığımız alanın hava durumunu viewModel de tutup layout daki ilgili view leri dolduruyoruz.
         *
         * */
        viewModel.weatherCondition.observe(viewLifecycleOwner,{
            it?.let {
                when (it.WeatherIcon) {

                    1 -> {
                        binding.ivDetail.setImageResource(R.drawable._1)
                    }
                    2 -> {
                        binding.ivDetail.setImageResource(R.drawable._2)
                    }
                    3 -> {
                        binding.ivDetail.setImageResource(R.drawable._3)
                    }
                    4 -> {
                        binding.ivDetail.setImageResource(R.drawable._4)
                    }
                    5 -> {
                        binding.ivDetail.setImageResource(R.drawable._5)
                    }
                    6 -> {
                        binding.ivDetail.setImageResource(R.drawable._6)
                    }
                    7 -> {
                        binding.ivDetail.setImageResource(R.drawable._7)
                    }
                    8 -> {
                        binding.ivDetail.setImageResource(R.drawable._8)
                    }
                    11 -> {
                        binding.ivDetail.setImageResource(R.drawable._11)
                    }
                    12 -> {
                        binding.ivDetail.setImageResource(R.drawable._12)
                    }
                    13 -> {
                        binding.ivDetail.setImageResource(R.drawable._13)
                    }
                    14 -> {
                        binding.ivDetail.setImageResource(R.drawable._14)
                    }
                    15 -> {
                        binding.ivDetail.setImageResource(R.drawable._15)
                    }
                    16 -> {
                        binding.ivDetail.setImageResource(R.drawable._16)
                    }
                    17 -> {
                        binding.ivDetail.setImageResource(R.drawable._17)
                    }
                    18 -> {
                        binding.ivDetail.setImageResource(R.drawable._18)
                    }
                    19 -> {
                        binding.ivDetail.setImageResource(R.drawable._19)
                    }
                    20 -> {
                        binding.ivDetail.setImageResource(R.drawable._20)
                    }
                    21 -> {
                        binding.ivDetail.setImageResource(R.drawable._21)
                    }
                    22 -> {
                        binding.ivDetail.setImageResource(R.drawable._22)
                    }
                    23 -> {
                        binding.ivDetail.setImageResource(R.drawable._23)
                    }
                    24 -> {
                        binding.ivDetail.setImageResource(R.drawable._24)
                    }
                    25 -> {
                        binding.ivDetail.setImageResource(R.drawable._25)
                    }
                    26 -> {
                        binding.ivDetail.setImageResource(R.drawable._26)
                    }
                    29 -> {
                        binding.ivDetail.setImageResource(R.drawable._29)
                    }
                    30 -> {
                        binding.ivDetail.setImageResource(R.drawable._30)
                    }
                    31 -> {
                        binding.ivDetail.setImageResource(R.drawable._31)
                    }
                    32 -> {
                        binding.ivDetail.setImageResource(R.drawable._32)
                    }
                    33 -> {
                        binding.ivDetail.setImageResource(R.drawable._33)
                    }
                    34 -> {
                        binding.ivDetail.setImageResource(R.drawable._34)
                    }
                    35 -> {
                        binding.ivDetail.setImageResource(R.drawable._35)
                    }
                    36 -> {
                        binding.ivDetail.setImageResource(R.drawable._36)
                    }

                    37 -> {
                        binding.ivDetail.setImageResource(R.drawable._37)
                    }
                    38 -> {
                        binding.ivDetail.setImageResource(R.drawable._38)
                    }
                    39 -> {
                        binding.ivDetail.setImageResource(R.drawable._39)
                    }
                    40 -> {
                        binding.ivDetail.setImageResource(R.drawable._40)
                    }
                    41 -> {
                        binding.ivDetail.setImageResource(R.drawable._41)
                    }
                    42 -> {
                        binding.ivDetail.setImageResource(R.drawable._42)
                    }
                    43 -> {
                        binding.ivDetail.setImageResource(R.drawable._43)
                    }
                    44 -> {
                        binding.ivDetail.setImageResource(R.drawable._44)
                    }
                }
            }

            binding.tvDegree.text = ""+it?.Temperature?.Metric?.Value+it?.Temperature?.Metric?.Unit
            binding.tvCondition.text =it?.WeatherText
            binding.tvName.text = name
        })
    }
}