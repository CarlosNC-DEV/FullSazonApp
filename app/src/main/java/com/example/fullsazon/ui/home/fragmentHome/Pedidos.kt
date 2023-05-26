package com.example.fullsazon.ui.home.fragmentHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fullsazon.R
import com.example.fullsazon.databinding.FragmentPedidosBinding
import com.example.fullsazon.model.local.HistorialDb
import com.example.fullsazon.model.local.db.ManagerhelperDb
import com.example.fullsazon.ui.dapter.pedidos.PedidosAdapter
import com.example.fullsazon.ui.dapter.pedidos.PedidosHolder.Companion.deleteVal
import com.example.fullsazon.ui.dapter.pedidos.PedidosHolder.Companion.totalPagoFin
import com.example.fullsazon.ui.prefrencias.FullSazon.Companion.preferencias
import java.util.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Pedidos.newInstance] factory method to
 * create an instance of this fragment.
 */
class Pedidos : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentPedidosBinding
    private lateinit var managerhelperDb: ManagerhelperDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ovPedidos = inflater.inflate(R.layout.fragment_pedidos, container, false)
        binding = FragmentPedidosBinding.bind(ovPedidos)
        managerhelperDb = ManagerhelperDb(requireContext())

        totalPagoFin.observe(viewLifecycleOwner, Observer { total->
            binding.precioFinal.text = total.toString()
        })

        deleteVal.observe(viewLifecycleOwner, Observer { delete->
            when(delete){
                true-> cargarData()
                else->{
                }
            }
            true
        })

        binding.finalizar.setOnClickListener {
            val resul = managerhelperDb.deleteDataPedido()
            if(resul == true){
                val historialVal = HistorialDb(Date().toString(), preferencias.getUsuario().toString(), binding.precioFinal.text.toString().toInt() )
                val resul = managerhelperDb.registrarHistorial(historialVal)
                if(resul>0){
                    Toast.makeText(requireContext(), "Pedido Finalizado correctamente", Toast.LENGTH_SHORT).show()
                    cargarData()
                }
            }
        }

        cargarData()





        return ovPedidos
    }

    private fun cargarData() {
        totalPagoFin.value = 0
        val resul = managerhelperDb.pedidosFund()
        val rcvPedidos = binding.rcvPedidos
        rcvPedidos.layoutManager = LinearLayoutManager(requireContext())
        rcvPedidos.adapter = PedidosAdapter(resul)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Pedidos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Pedidos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}