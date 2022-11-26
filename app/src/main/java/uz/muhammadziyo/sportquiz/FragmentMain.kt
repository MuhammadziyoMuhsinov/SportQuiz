package uz.muhammadziyo.sportquiz

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.navigation.fragment.findNavController
import uz.muhammadziyo.sportquiz.databinding.FragmentMainBinding
import uz.muhammadziyo.sportquiz.models.MyData


class FragmentMain : Fragment() {

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        Handler().postDelayed(object : Runnable{
            override fun run() {
                binding.splash.visibility = View.INVISIBLE
                binding.main.visibility = View.VISIBLE
            }

        },2000)





        binding.cardFootball.setOnClickListener {
            findNavController().navigate(R.id.fragmentFootball)
        }
        binding.cardBasketball.setOnClickListener {
            findNavController().navigate(R.id.fragmentBasketball)
        }


        return binding.root
    }


}