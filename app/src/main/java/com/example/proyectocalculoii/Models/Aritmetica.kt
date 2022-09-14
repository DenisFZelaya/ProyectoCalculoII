package com.example.proyectocalculoii.Models

class Aritmetica {
    var numero1: Int = 0;
    var numero2: Int = 0;

    constructor() {
        //
    }

    constructor(numero1: Int, numero2: Int) {
        this.numero1 = numero1
        this.numero2 = numero2
    }


    fun suma(): Int {
        println("Suma()")
        return numero1 + numero2;
    }

    fun resta(): Int {
        println("Resta()")
        return numero1 - numero2;
    }

    fun dividir(): Int {
        println("Dividir()")
        return numero1 / numero2;
    }

    fun multiplicar(): Int {
        println("Multiplicar()")
        return numero1 * numero2;
    }

    fun Mod(): Int {
        println("Mod()")
        return numero1.mod(numero2);
    }
}