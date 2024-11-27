package edu.iesam.loginexam1eval.feature.login.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.databinding.FragmentDeleteAccountBinding
import edu.iesam.loginexam1eval.feature.login.domain.User

class DeleteAccountFragment : Fragment() {

    private lateinit var loginFactory: LoginFactory
    private lateinit var viewModel: DeleteAccountViewModel

    private var _binding: FragmentDeleteAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteAccountBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            action.setOnClickListener {
                val username = username.text.toString()
                val password = password.text.toString()
                viewModel.loadDeleteAccount(User(username, password))
            }
            backToMain.setOnClickListener {
                findNavController().navigate(
                    DeleteAccountFragmentDirections.actionDeleteAccountToMain()
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginFactory = LoginFactory(requireContext())
        viewModel = loginFactory.buildDeleteAccountViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            if (uiState.isLoading) {
                Log.d("@dev", "Loading...")
            } else {
                Log.d("@dev", "Loaded")

                uiState.deleted?.let { isDeleted ->
                    if (isDeleted) {
                        Log.d("@dev", "Usuario dado de baja con exito")
                    } else {
                        Log.d("@dev", "Indique las credenciales correctas")
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