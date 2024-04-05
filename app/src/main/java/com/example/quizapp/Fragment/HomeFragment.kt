package com.example.quizapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizapp.R
import com.example.quizapp.Withdrawal
import com.example.quizapp.adapter.categoryadapter
import com.example.quizapp.databinding.FragmentHomeBinding
import com.example.quizapp.model.User
import com.example.quizapp.model.categorymodelclass
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private var categorylist = ArrayList<categorymodelclass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categorylist.add(categorymodelclass(R.drawable.scince1,"Science"))
        categorylist.add(categorymodelclass(R.drawable.english1,"English"))
        categorylist.add(categorymodelclass(R.drawable.geography,"Geography"))
        categorylist.add(categorymodelclass(R.drawable.math,"Maths"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.imageView5.setOnClickListener{
            val bottomSheetDialog:BottomSheetDialogFragment = Withdrawal()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"TEST")
            bottomSheetDialog.enterTransition
        }
        binding.textView6.setOnClickListener{
            val bottomSheetDialog:BottomSheetDialogFragment = Withdrawal()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"TEST")
            bottomSheetDialog.enterTransition
        }
        // Inflate the layout for this fragment
        Firebase.database.reference.child("Users")
            .child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var user=snapshot.getValue<User>()
                        binding.NameHome.text=user?.name
                    }


                    override fun onCancelled(error: DatabaseError) {
                        //TODO("Not yet implemented")
                    }

                }
            )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryrv.layoutManager=GridLayoutManager(requireContext(),2)
        var adapter = categoryadapter(categorylist,requireActivity())
        binding.categoryrv.adapter = adapter
        binding.categoryrv.setHasFixedSize(true)
    }
    companion object {
    }
}