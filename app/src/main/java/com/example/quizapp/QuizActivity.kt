package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityQuizBinding
import com.example.quizapp.model.Question
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuizActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }
    private var questionList= ArrayList<Question>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        questionList=ArrayList<Question>()
        var image = intent.getIntExtra("categoryimg",0)
        var catText=intent.getStringExtra("questionType")
        Firebase.firestore.collection("Questions")
            .document(catText.toString())
            .collection("question1").get().addOnSuccessListener {
                questionData->
                questionList.clear()
                for (data in questionData.documents){
                    var question: Question? =data.toObject(Question::class.java)
                    questionList.add(question!!)
                }
                if(questionList.size > 0) {
                    binding.Question.text = questionList.get(0).question
                    binding.Option1.text = questionList.get(0).option1
                    binding.Option2.text = questionList.get(0).option2
                    binding.Option3.text = questionList.get(0).option3
                    binding.Option4.text = questionList.get(0).option4
                }

            }
        binding.categoryimg.setImageResource(image )
        binding.imageView5.setOnClickListener{
            val bottomSheetDialog: BottomSheetDialogFragment = Withdrawal()
            bottomSheetDialog.show(this@QuizActivity.supportFragmentManager,"TEST")
            bottomSheetDialog.enterTransition
        }
        binding.textView6.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialogFragment = Withdrawal()
            bottomSheetDialog.show(this@QuizActivity.supportFragmentManager, "TEST")
            bottomSheetDialog.enterTransition
        }
    }
}