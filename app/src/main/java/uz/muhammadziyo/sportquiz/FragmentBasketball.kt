package uz.muhammadziyo.sportquiz

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import uz.muhammadziyo.sportquiz.databinding.FragmentBasketballBinding
import uz.muhammadziyo.sportquiz.databinding.ItemChekDialogBinding
import uz.muhammadziyo.sportquiz.databinding.ItemDialogBinding
import uz.muhammadziyo.sportquiz.models.MyData
import uz.muhammadziyo.sportquiz.models.Question
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FragmentBasketball : Fragment() {

    private lateinit var binding: FragmentBasketballBinding
    private lateinit var list: ArrayList<Question>
    private var position = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBasketballBinding.inflate(layoutInflater)
        MyData.correct = 0
        MyData.incorrect = 0

        MyData.ball = 0

        loadList()
        loadQuestion()



        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.answerA.setOnClickListener {
            chekQuestion(1)
        }
        binding.answerB.setOnClickListener {
            chekQuestion(2)
        }
        binding.answerC.setOnClickListener {
            chekQuestion(3)
        }
        binding.answerD.setOnClickListener {
            chekQuestion(4)
        }

        return binding.root
    }


    private fun chekQuestion(answer: Int) {
        if (answer == list[position].correctAnswer) {
            if (list.size == position + 1) {
                MyData.correct += 1
                MyData.ball += 50

                alertDialog()
            } else {
                position += 1
                MyData.correct += 1
                MyData.ball += 50
                binding.txtBall.text = "ball: ${MyData.ball}"
                chekDialog(1)
                loadQuestion()
            }

        } else {
            if (list.size == position + 1) {
                MyData.incorrect += 1
                alertDialog()
            } else {
                MyData.incorrect += 1
                chekDialog(2)
                position += 1
                loadQuestion()

            }

        }

    }

    private fun alertDialog() {
        val alertDialog = AlertDialog.Builder(binding.root.context, R.style.NewDialog).create()
        val itemDialogBinding = ItemDialogBinding.inflate(LayoutInflater.from(binding.root.context))
        alertDialog.setView(itemDialogBinding.root)
        binding.xira.visibility = View.VISIBLE
        itemDialogBinding.ball.text = "total ball:  ${MyData.ball}"
        itemDialogBinding.correct.text = "correct answer: ${MyData.correct}"
        itemDialogBinding.wrong.text = "wrong answer: ${MyData.incorrect}"
        position = 0
        MyData.correct = 0
        MyData.incorrect = 0
        MyData.ball = 0
        binding.txtBall.text = "ball: 0"
        binding.level.text = "level: 1"
        loadQuestion()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val myDateObj = LocalDateTime.now()
                val myFormatObj: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")
                val formattedDate = myDateObj.format(myFormatObj)

                itemDialogBinding.date.text = formattedDate


                alertDialog.show()
            }
        }
        alertDialog.setOnDismissListener {
            binding.xira.visibility = View.INVISIBLE
        }


    }


    private fun loadQuestion() {
        binding.level.text = "level: ${position+1}"

        binding.txtQuestion.text = list[position].question
        binding.txtAnswerA.text = list[position].answer1
        binding.txtAnswerB.text = list[position].answer2
        binding.txtAnswerC.text = list[position].answer3
        binding.txtAnswerD.text = list[position].answer4


    }

    private fun chekDialog(answer: Int) {
        val dialog = BottomSheetDialog(binding.root.context, R.style.NewDialog)
        val itemDialog = ItemChekDialogBinding.inflate(layoutInflater)
        if (answer == 1) {
            itemDialog.chek.text = "Correct!"
            itemDialog.chek.setTextColor(Color.GREEN)
            dialog.setContentView(itemDialog.root)
            dialog.show()
            Handler().postDelayed(object : Runnable {
                override fun run() {
                    dialog.dismiss()
                }
            }, 1000)
        } else {
            itemDialog.chek.text = "Wrong!"
            itemDialog.chek.setTextColor(Color.RED)
            dialog.setContentView(itemDialog.root)
            dialog.show()
            Handler().postDelayed(object : Runnable {
                override fun run() {
                    dialog.dismiss()
                }
            }, 1000)
        }

    }

    private fun loadList() {
        list = ArrayList()
        list.add(Question(
            "In which country is basketball a major sport?",
            "Canada",
            "United States of America",
            "United Kingdom",
            "Jamaica",
            2
        ))

        list.add(Question("What is the name of the governing body in the world's top basketball league?",
            "MLB",
            "NHL",
            "NBA",
            "WNBA",
            3
        ))

        list.add(Question("Is there any contact allowed between players in this sport?",
            "Yes",
            "No",
            "Maybe...",
            "I don't know",
            2
        ))

        list.add(Question("What is it called when a player scores a point?",
            "Goal",
            "Touchdown",
            "Home-run",
            "A Basket",
            4
        ))

        list.add(Question("How many points is a shot worth when inside the oppositions semi-circle?",
            "1",
            "2",
            "3",
            "4",
            2
        ))

        list.add(Question("How many points are awarded for a score from outside the oppositions semi-circle?",
            "1",
            "2",
            "3",
            "4",
            3
        ))

        list.add(Question("When in possession of the ball, what must a player do?",
            "Run with the ball",
            "Stand still",
            "Bounce the ball while running",
            "Balance the ball on your head",
            3
        ))

        list.add(Question("What happens if a player fails to dribble while in moving with the ball?",
        "Possession is given to the opposing team",
            "Nothing",
            "The player is sent back to his own end",
            "The game ends",
            1
            ))

        list.add(Question("How many parts to a basketball game are there?",
        "2",
            "3",
            "4",
            "5",
            3
            ))

        list.add(Question("How long is each part?",
        "10",
            "15",
            "20",
            "30",
            1
            ))
    }


}