package uz.muhammadziyo.sportquiz

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.muhammadziyo.sportquiz.databinding.ActivityMainBinding
import uz.muhammadziyo.sportquiz.databinding.FragmentMainBinding
import uz.muhammadziyo.sportquiz.models.MyData

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}