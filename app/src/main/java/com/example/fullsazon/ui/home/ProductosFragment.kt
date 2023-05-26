package com.example.fullsazon.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fullsazon.R
import com.example.fullsazon.databinding.FragmentProductosBinding
import com.example.fullsazon.model.local.db.ManagerhelperDb
import com.example.fullsazon.ui.dapter.categorias.CategoriasHolder.Companion.remplaceProductos
import com.example.fullsazon.ui.dapter.productos.ProdcutosAdapter
import com.example.fullsazon.ui.home.fragmentHome.Categorias

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentProductosBinding
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
        val ovProdcutos = inflater.inflate(R.layout.fragment_productos, container, false)
        binding = FragmentProductosBinding.bind(ovProdcutos)
        managerhelperDb = ManagerhelperDb(requireContext())

        binding.atras.setOnClickListener{
            remplaceFrgament(Categorias())
        }

        remplaceProductos.observe(viewLifecycleOwner, Observer { data->
            cargarProdcutos(data)
        })

        return ovProdcutos
    }

    private fun cargarProdcutos(data:String) {
        val resul = managerhelperDb.productosFund(data)
        val rcvProductos = binding.rcvProductos
        rcvProductos.layoutManager = LinearLayoutManager(requireContext())
        rcvProductos.adapter = ProdcutosAdapter(resul)

    }

    private fun remplaceFrgament(fragment: Fragment) {
        val fgmManager = requireActivity().supportFragmentManager
        val fragmentTransition = fgmManager.beginTransaction()
        fragmentTransition.replace(R.id.frameLayout, fragment)
        fragmentTransition.commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}