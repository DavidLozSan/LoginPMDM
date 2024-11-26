package edu.iesam.loginexam1eval.feature.login.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.R
import edu.iesam.loginexam1eval.databinding.FragmentSignUpBinding
import edu.iesam.loginexam1eval.feature.login.domain.User

class SignUpFragment : Fragment() {

    private lateinit var loginFactory: LoginFactory
    private lateinit var viewModel: SignUpViewModel

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            action.setOnClickListener {
                val username = username.text.toString()
                val password = password.text.toString()
                viewModel.loadSignUp(User(username, password))
            }
            backToMain.setOnClickListener {
                findNavController().navigate(R.id.fragment_main)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginFactory = LoginFactory(requireContext())
        viewModel = loginFactory.buildSignUpViewModel()
        setupObserver()
    }


    private fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            if (uiState.isLoading) {
                Log.d("@dev", "Loading...")
            } else {
                Log.d("@dev", "Loaded")

                uiState.registered?.let { isRegistered ->
                    if (isRegistered) {
                        Log.d("@dev", "Usuario dado de alta con exito")
                        findNavController().navigate(SignUpFragmentDirections.actionSignUpToWelcome())
                    } else {
                        Log.d("@dev", "Este usuario ya existe")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}