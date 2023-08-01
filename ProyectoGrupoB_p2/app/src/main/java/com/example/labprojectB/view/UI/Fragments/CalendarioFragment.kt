package com.example.labprojectB.view.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labprojectB.Model.Conferencia
import com.example.labprojectB.R
import com.example.labprojectB.view.adapter.ConferenciaAdapter
import com.example.labprojectB.view.adapter.ConferenciaListener
import com.example.labprojectB.viewmodel.ConferenciasViewModel
import kotlinx.android.synthetic.main.fragment_calendario.*

class CalendarioFragment : Fragment(), ConferenciaListener {
    private lateinit var conferenciaAdapter: ConferenciaAdapter
    private lateinit var viewModel: ConferenciasViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calendario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConferenciasViewModel::class.java)
        viewModel.refresh()

        conferenciaAdapter = ConferenciaAdapter(this)

        RVCalendario.apply{
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = conferenciaAdapter
        }
        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.listadoconferencias.observe(viewLifecycleOwner, Observer<List<Conferencia>> { conferencia ->
            conferenciaAdapter.updatedat(conferencia)
        })
        viewModel.isloading.observe(viewLifecycleOwner, Observer<Boolean>{
            if(it != null)
                RLBaseSchedule.visibility = View.INVISIBLE
        })
    }

    override fun onConferenciaClicked(conferencia: Conferencia, position: Int) {

        val bundle= bundleOf("conferencia" to conferencia)
        findNavController().navigate(R.id.NAVProgramaFragment,bundle)
    }

}