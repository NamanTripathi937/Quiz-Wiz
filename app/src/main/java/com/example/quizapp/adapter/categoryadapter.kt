package com.example.quizapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.QuizActivity
import com.example.quizapp.databinding.CategoryitemBinding
import com.example.quizapp.model.categorymodelclass

class categoryadapter(
    var categoryList: ArrayList<categorymodelclass>,
    var requireActivity: FragmentActivity
): RecyclerView.Adapter<categoryadapter.MycategoryViewHolder>() {
    class MycategoryViewHolder(var binding: CategoryitemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MycategoryViewHolder {
        return MycategoryViewHolder(CategoryitemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()= categoryList.size

    override fun onBindViewHolder(holder: MycategoryViewHolder, position: Int) {
        var datalist = categoryList[position]
        holder.binding.imageView.setImageResource(datalist.catImage)
        holder.binding.category.text=datalist.catText
        holder.binding.categorybutton.setOnClickListener {
            var intent = Intent (requireActivity, QuizActivity::class.java)
            intent.putExtra("categoryimg" , datalist.catImage)
            intent.putExtra("questionType" , datalist.catText)
            requireActivity.startActivity(intent)
        }
    }
}