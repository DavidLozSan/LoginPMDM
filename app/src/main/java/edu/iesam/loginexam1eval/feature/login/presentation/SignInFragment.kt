package edu.iesam.loginexam1eval.feature.login.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.R
import edu.iesam.loginexam1eval.databinding.FragmentSignInBinding
import edu.iesam.loginexam1eval.feature.login.domain.User

class SignInFragment : Fragment() {

    private lateinit var loginFactory: LoginFactory
    private lateinit var viewModel: SignInViewModel

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            action.setOnClickListener {
                val username = username.text.toString()
                val password = password.text.toString()
                viewModel.loadSignIn(User(username, password))

                findNavController().navigate(SignInFragmentDirections.actionSignInToWelcome())

            }
            backToMain.setOnClickListener {
                findNavController().navigate(SignInFragmentDirections.actionBackToMain())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginFactory = LoginFactory(requireContext())
        viewModel = loginFactory.buildSignInViewModel()
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
                        Log.d("@dev", "Bienvenido usuario")
                    } else {
                        Log.d("@dev", "El usuario o contraseña no es valido")
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