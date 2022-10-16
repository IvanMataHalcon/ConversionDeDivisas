package mx.edu.utez.divisas

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import mx.edu.utez.divisas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sp1 = binding.spinner
        val sp2 = binding.spinner2
        val lista = resources.getStringArray(R.array.divisas)
        var positioSP1 = ""
        var positioSP2 = ""
        val cantidad = binding.editTextNumber
        val resultado = binding.textResult
        var total = 0.0

        ArrayAdapter.createFromResource(
            this,
            R.array.divisas,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            sp1.adapter = adapter
            sp2.adapter = adapter
            sp1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    positioSP1 = lista[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(this@MainActivity," ERRO AL TRAER LAS DIVISAS",Toast.LENGTH_LONG)
                        .show()
                }

            }
            sp2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    positioSP2 = lista[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(this@MainActivity," ERRO AL TRAER LAS DIVISAS",Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        binding.btnEnviar.setOnClickListener {
            if (cantidad.text.isNotEmpty()){
                when(positioSP1){
                    "Dolar" -> {
                        when(positioSP2){
                            "Dolar" -> {
                                resultado.text = cantidad.text
                            }
                            "Euro" -> {
                                total = cantidad.text.toString().toFloat() * 0.98
                                resultado.text = total.toString()
                            }
                            "Peso" -> {
                                total = cantidad.text.toString().toFloat() * 0.050
                                resultado.text = total.toString()
                            }
                        }
                    }
                    "Euro" -> {
                        when(positioSP2){
                            "Dolar" -> {
                                total = cantidad.text.toString().toFloat() * 0.98
                                resultado.text = total.toString()
                            }
                            "Euro" -> {
                                resultado.text = cantidad.text
                            }
                            "Peso" -> {
                                total = cantidad.text.toString().toFloat() * 0.051
                                resultado.text = total.toString()
                            }
                        }
                    }
                    "Peso" -> {
                        when(positioSP2){
                            "Dolar" -> {
                                total = cantidad.text.toString().toFloat() * 0.050
                                resultado.text = String.format("%.2f", total)
                            }
                            "Euro" -> {
                                total = cantidad.text.toString().toFloat() * 0.051
                                resultado.text = String.format("%.2f", total)
                            }
                            "Peso" -> {
                                resultado.text = cantidad.text
                            }
                        }

                    }
                }
            }else{
                Toast.makeText(this@MainActivity,"INGRESE UNA CANTIDAD",Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


}
