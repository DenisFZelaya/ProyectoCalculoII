package com.example.proyectocalculoii

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.example.proyectocalculoii.Models.Aritmetica
import com.example.proyectocalculoii.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var aritmetica = Aritmetica();

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        var simbolo = ""

        // Variables de entrada
        var n1 = findViewById<TextView>(R.id.itn1)
        var n2 = findViewById<TextView>(R.id.itn2)

        // Castear botones
        val btnSuma = findViewById<Button>(R.id.btnSuma)
        val btnResta = findViewById<Button>(R.id.btnResta)
        val btnDivision = findViewById<Button>(R.id.btnDivision)
        val btnMulti = findViewById<Button>(R.id.btnMulti)
        val btnOC = findViewById<Button>(R.id.btnOC)

        // Eventos al cambiar valores de entrante
        btnOC.setOnClickListener(){
            var operacion = findViewById<TextView>(R.id.itOP)
            println("btn oc presionado" + operacion.text)
            OperacionCompleja(operacion.text.toString())
        }

        n1.setOnClickListener(){
            aritmetica.numero1 = Integer.parseInt(n1.text.toString())
            aritmetica.numero2 = Integer.parseInt(n2.text.toString())
            Calcular(simbolo)
        }

        n2.setOnClickListener(){
            aritmetica.numero1 = Integer.parseInt(n1.text.toString())
            aritmetica.numero2 = Integer.parseInt(n2.text.toString())
            Calcular(simbolo)
        }

        n2.setOnClickListener(){
            aritmetica.numero1 = Integer.parseInt(n1.text.toString())
            aritmetica.numero2 = Integer.parseInt(n2.text.toString())
            Calcular(simbolo)
        }

        // Eventos al presionar los botones
        btnSuma.setOnClickListener(){
            simbolo = "+"
            Calcular(simbolo)
        }

        btnResta.setOnClickListener(){
            simbolo = "-"
            Calcular(simbolo)
        }

        btnDivision.setOnClickListener(){
            simbolo = "/"
            Calcular(simbolo)
        }

        btnMulti.setOnClickListener(){
            simbolo = "*"
            Calcular(simbolo)
        }
    }

    fun Calcular(simbolo: String) {

        // Variables mostrar informacion
        var tvResultado = findViewById<TextView>(R.id.tvResultado)
        var tvEcuacion = findViewById<TextView>(R.id.tvEcuacion)

        tvEcuacion.text = "${aritmetica.numero1} ${simbolo} ${aritmetica.numero2}"
        var resultado: String = ""

        if(simbolo.equals("+")){
            resultado = "${aritmetica.suma()}"
        }

        if(simbolo.equals("-")){
            resultado = "${aritmetica.resta()}"
        }

        if(simbolo.equals("/")){
            if(aritmetica.numero2 == 0) {
                resultado = "No se puede dividir un numero entre 0"
            } else {
                resultado = "${aritmetica.dividir()}"
            }

        }

        if(simbolo.equals("*")){
            resultado = "${aritmetica.multiplicar()}"
        }

        tvResultado.text = resultado
    }

    fun OperacionCompleja(operacion: String)
    {
        var resutadoOC = findViewById<TextView>(R.id.tvResultadoEcuacionCompleja)

        var numbers = Regex("[0-9]+").findAll(operacion)
            .map(MatchResult::value)
            .toList()

        var simbols = Regex("[^a-zA-Z0-9]+").findAll(operacion)
            .map(MatchResult::value)
            .toList()

        var result: Int = 0;

        if(simbols.size < 1){
            resutadoOC.text = "No se encontraron signos de operaciones"
        }else {
            for (i in simbols.indices){

                println("resut inicial: en iteracion " + i + " : " + result)

                if(simbols[i].toString() == "+"){
                    if(i > 0){
                        result = result  + Integer.parseInt(numbers[i + 1])
                    }else {
                        result = Integer.parseInt(numbers[i]) + Integer.parseInt(numbers[i + 1])
                    }
                }

                if(simbols[i].toString() == "-"){
                    if(i > 0){
                        result = result - Integer.parseInt(numbers[i + 1])
                    }else {
                        result = Integer.parseInt(numbers[i]) - Integer.parseInt(numbers[i + 1])
                    }
                }

                if(simbols[i].toString() == "*"){
                    if(i > 0){
                        result = result  * Integer.parseInt(numbers[i + 1])
                    }else {
                        result = Integer.parseInt(numbers[i]) * Integer.parseInt(numbers[i + 1])
                    }
                }

                if(simbols[i].toString() == "/"){
                    if(i > 0){
                        result = result / Integer.parseInt(numbers[i + 1])
                        println("numbers: " + numbers[i + 1])
                    }else {
                        result = Integer.parseInt(numbers[i]) / Integer.parseInt(numbers[i + 1])
                    }
                }
                println("resut: en iteracion " + i + " : " + result)
            }

            resutadoOC.text = result.toString();
            println("El resultado es: " + result)
            println(numbers)
            println(simbols)
        }


    }
    fun isNumeric(s: String): Boolean {
        return try {
            s.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}