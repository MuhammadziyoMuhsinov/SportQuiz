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
import uz.muhammadziyo.sportquiz.databinding.FragmentFootballBinding
import uz.muhammadziyo.sportquiz.databinding.ItemChekDialogBinding
import uz.muhammadziyo.sportquiz.databinding.ItemDialogBinding
import uz.muhammadziyo.sportquiz.models.MyData
import uz.muhammadziyo.sportquiz.models.Question
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FragmentFootball : Fragment() {
    private lateinit var list: ArrayList<Question>
    private lateinit var binding: FragmentFootballBinding
    private var position = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFootballBinding.inflate(layoutInflater)
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
        val alertDialog = AlertDialog.Builder(binding.root.context,R.style.NewDialog).create()
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
        binding.level.text = "level: 0"
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

        list.add(Question(" When was the first Premier League played?",
            "1992/93",
            "1994/95",
            "1996/97",
            "1998/99",
            1
        ))

        list.add(Question("In which year Ronaldo retired before changing his decision?",
            "2014",
            "2016",
            "2018",
            "2020",
            2
        ))

        list.add(Question(
            "This team won the first Premier League",
            "Manchester United",
            "Manchester City",
            "Liverpool",
            "Chelsea",
            1
        ))

        list.add(Question("Which country won the first World Cup Championship?",
            "Portugal",
            "Brazil",
            "England",
            "Uruguay",
            4
        ))

        list.add(Question("How many clubs played in the first Premier League?",
            "21",
            "32",
            "22",
            "20",
            3
        ))

        list.add(Question("Manchester City was founded in which year?",
            "1880",
            "1788",
            "1790",
            "1799",
            1
        ))

        list.add(Question(" How many clubs have never been relegated from the Premier League?",
            "5",
            "6",
            "7",
            "8",
            2
        ))

        list.add(Question("This player is the top scorer of the Champions League.",
            "Cristiano Ronaldo",
            "Leonel Messi",
            "Muhammad Salah",
            "Karim Benzema",
            1
        ))

        list.add(Question("This club has won the most Champions League titles?",
            "Barcelona",
            "PSJ",
            "Real Madrid",
            "Liverpool",
            3
        ))

        list.add(Question("Liverpool was founded in which year?",
        "1890",
            "1898",
            "1895",
            "1892",
            4
            ))

    }


}