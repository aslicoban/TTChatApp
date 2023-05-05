package com.example.ttchat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ttchat.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth




class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding !!
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupButton.setOnClickListener {
            //sign up button a tıklandığında yapılacaklar. kullanıcı kayıt işlemi yapılacak
            auth.createUserWithEmailAndPassword(binding.emailText3.text.toString(),binding.passwordText.text.toString()).addOnSuccessListener {
                //kullanıcı oluşturuldu
                val action = LoginFragmentDirections.actionLoginFragmentToChatFragment()
                findNavController().navigate(action)
            }.addOnFailureListener { exception ->
                //hata aldık ve bu bana exception olarak verildi
                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            }

        }

        binding.loginButton.setOnClickListener {
            //login button a tıklandığında yapılacaklar. kullanıcı giriş işlemi yapılacak

            auth.signInWithEmailAndPassword(binding.emailText3.text.toString(),binding.passwordText.text.toString()).addOnSuccessListener {
                val action = LoginFragmentDirections.actionLoginFragmentToChatFragment()
                findNavController().navigate(action)

            }.addOnFailureListener {
                Toast.makeText(requireContext(),it.localizedMessage,Toast.LENGTH_LONG).show()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


