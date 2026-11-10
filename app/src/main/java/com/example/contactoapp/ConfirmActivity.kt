package com.example.contactoapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        findViewById<TextView>(R.id.tvNombre).text = intent.getStringExtra("nombre")
        findViewById<TextView>(R.id.tvFecha).text = "Fecha Nacimiento: ${intent.getStringExtra("fecha")}"
        findViewById<TextView>(R.id.tvTelefono).text = "Tel: ${intent.getStringExtra("telefono")}"
        findViewById<TextView>(R.id.tvEmail).text = "Email: ${intent.getStringExtra("email")}"
        findViewById<TextView>(R.id.tvDescripcion).text = "Descripción: ${intent.getStringExtra("descripcion")}"

        // Al volver, MainActivity sigue en memoria con los campos ya llenos (datos precargados)
        findViewById<Button>(R.id.btnEditar).setOnClickListener { finish() }
    }
}