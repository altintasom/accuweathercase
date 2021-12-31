package com.altintasomer.application.accuweathercase.view

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.altintasomer.application.accuweathercase.R
import com.altintasomer.application.accuweathercase.databinding.FragmentMainBinding
import com.altintasomer.application.accuweathercase.viewmodel.GeneralViewModelModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainFragment"
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: GeneralViewModelModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding
    private lateinit var locationManager : LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {
        /**
         * Burada fragmen ın view ını bind olduk
         * */
        binding = FragmentMainBinding.bind(view)
        /**
         * locationManager tanımlandı
         * */
        locationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
        registerLauncher()

        locationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
                Log.d(TAG, "onLocationChanged: "+p0.latitude+","+p0.longitude)
                val latLong =""+p0.latitude+","+p0.longitude
                viewModel.searchGeoPosition(latLong = latLong)
            }
        }
        checkAndRequestLocationPermission()

        viewModel.rKey.observe(viewLifecycleOwner ,{
            viewModel.getWeatherCondition(it?:"")
        })

        binding.fb.setOnClickListener {
           initAndRequestLastLocation()
        }


        viewModel.weatherCondition.observe(viewLifecycleOwner,{weatherCondition ->
            binding.tvCondition.text = weatherCondition.WeatherText
            binding.tvDegree.text = weatherCondition.Temperature?.Metric?.Value.toString() +""+weatherCondition.Temperature?.Metric?.Unit
            binding.tvName.text = viewModel.locationName

            weatherCondition.let {
                when (it?.WeatherIcon) {

                    1 -> {
                        binding.ivMain.setImageResource(R.drawable._1)
                    }
                    2 -> {
                        binding.ivMain.setImageResource(R.drawable._2)
                    }
                    3 -> {
                        binding.ivMain.setImageResource(R.drawable._3)
                    }
                    4 -> {
                        binding.ivMain.setImageResource(R.drawable._4)
                    }
                    5 -> {
                        binding.ivMain.setImageResource(R.drawable._5)
                    }
                    6 -> {
                        binding.ivMain.setImageResource(R.drawable._6)
                    }
                    7 -> {
                        binding.ivMain.setImageResource(R.drawable._7)
                    }
                    8 -> {
                        binding.ivMain.setImageResource(R.drawable._8)
                    }
                    11 -> {
                        binding.ivMain.setImageResource(R.drawable._11)
                    }
                    12 -> {
                        binding.ivMain.setImageResource(R.drawable._12)
                    }
                    13 -> {
                        binding.ivMain.setImageResource(R.drawable._13)
                    }
                    14 -> {
                        binding.ivMain.setImageResource(R.drawable._14)
                    }
                    15 -> {
                        binding.ivMain.setImageResource(R.drawable._15)
                    }
                    16 -> {
                        binding.ivMain.setImageResource(R.drawable._16)
                    }
                    17 -> {
                        binding.ivMain.setImageResource(R.drawable._17)
                    }
                    18 -> {
                        binding.ivMain.setImageResource(R.drawable._18)
                    }
                    19 -> {
                        binding.ivMain.setImageResource(R.drawable._19)
                    }
                    20 -> {
                        binding.ivMain.setImageResource(R.drawable._20)
                    }
                    21 -> {
                        binding.ivMain.setImageResource(R.drawable._21)
                    }
                    22 -> {
                        binding.ivMain.setImageResource(R.drawable._22)
                    }
                    23 -> {
                        binding.ivMain.setImageResource(R.drawable._23)
                    }
                    24 -> {
                        binding.ivMain.setImageResource(R.drawable._24)
                    }
                    25 -> {
                        binding.ivMain.setImageResource(R.drawable._25)
                    }
                    26 -> {
                        binding.ivMain.setImageResource(R.drawable._26)
                    }
                    29 -> {
                        binding.ivMain.setImageResource(R.drawable._29)
                    }
                    30 -> {
                        binding.ivMain.setImageResource(R.drawable._30)
                    }
                    31 -> {
                        binding.ivMain.setImageResource(R.drawable._31)
                    }
                    32 -> {
                        binding.ivMain.setImageResource(R.drawable._32)
                    }
                    33 -> {
                        binding.ivMain.setImageResource(R.drawable._33)
                    }
                    34 -> {
                        binding.ivMain.setImageResource(R.drawable._34)
                    }
                    35 -> {
                        binding.ivMain.setImageResource(R.drawable._35)
                    }
                    36 -> {
                        binding.ivMain.setImageResource(R.drawable._36)
                    }

                    37 -> {
                        binding.ivMain.setImageResource(R.drawable._37)
                    }
                    38 -> {
                        binding.ivMain.setImageResource(R.drawable._38)
                    }
                    39 -> {
                        binding.ivMain.setImageResource(R.drawable._39)
                    }
                    40 -> {
                        binding.ivMain.setImageResource(R.drawable._40)
                    }
                    41 -> {
                        binding.ivMain.setImageResource(R.drawable._41)
                    }
                    42 -> {
                        binding.ivMain.setImageResource(R.drawable._42)
                    }
                    43 -> {
                        binding.ivMain.setImageResource(R.drawable._43)
                    }
                    44 -> {
                        binding.ivMain.setImageResource(R.drawable._44)
                    }


                }
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            if (it){
                binding.progressLayout.visibility = View.VISIBLE
            }else
                binding.progressLayout.visibility = View.GONE
        })

        viewModel.error.observe(viewLifecycleOwner,{
            if (it)
                Toast.makeText(requireContext(),"Error!",Toast.LENGTH_LONG).show()
        })

    }

    /**
     * Konum isinlerinin kontrolü yapılıyor ve izinler istendi
     * */
    private fun checkAndRequestLocationPermission() {
        if (checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Snackbar.make(
                    binding.root,
                    "Permission needed for location",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Give Permission") {
                    permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                }.show()
            } else {
                permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }

        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1f,locationListener)
        }
    }

    private fun registerLauncher(){
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if (it){
                if (checkSelfPermission(requireContext(),Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0f,locationListener)
            }else{
                Toast.makeText(requireContext(),"Permission denied!",Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Burada izinler kontrol ediliyor ve daha önce alınmış bir konum var ise o alınıyor
     *
     * */
    private fun initAndRequestLastLocation(){
        var lastLocation : Location? = null
        if (checkSelfPermission(requireContext(),Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)
             lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        else
            registerLauncher()
        viewModel.searchGeoPosition(lastLocation?.latitude.toString()+","+lastLocation?.longitude)
    }

}