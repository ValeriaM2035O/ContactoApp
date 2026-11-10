package com.example.contactoapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MainActivity : AppCompatActivity() {

    private lateinit var tilNombre: TextInputLayout
    private lateinit var tilFecha: TextInputLayout
    private lateinit var tilTelefono: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilDescripcion: TextInputLayout

    private lateinit var etNombre: TextInputEditText
    private lateinit var etFecha: TextInputEditText
    private lateinit var etTelefono: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etDescripcion: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tilNombre = findViewById(R.id.tilNombre)
        tilFecha = findViewById(R.id.tilFecha)
        tilTelefono = findViewById(R.id.tilTelefono)
        tilEmail = findViewById(R.id.tilEmail)
        tilDescripcion = findViewById(R.id.tilDescripcion)

        etNombre = findViewById(R.id.etNombre)
        etFecha = findViewById(R.id.etFecha)
        etTelefono = findViewById(R.id.etTelefono)
        etEmail = findViewById(R.id.etEmail)
        etDescripcion = findViewById(R.id.etDescripcion)

        etFecha.setOnClickListener { mostrarDatePicker() }

        findViewById<Button>(R.id.btnSiguiente).setOnClickListener {
            if (validar()) {
                val intent = Intent(this, ConfirmActivity::class.java).apply {
                    putExtra("nombre", etNombre.text.toString().trim())
                    putExtra("fecha", etFecha.text.toString())
                    putExtra("telefono", etTelefono.text.toString().trim())
                    putExtra("email", etEmail.text.toString().trim())
                    putExtra("descripcion", etDescripcion.text.toString().trim())
                }
                startActivity(intent)
            }
        }
    }

    private fun mostrarDatePicker() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Fecha de nacimiento")
            .build()

        picker.addOnPositiveButtonClickListener { millis ->
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            formato.timeZone = TimeZone.getTimeZone("UTC")
            etFecha.setText(formato.format(Date(millis)))
            tilFecha.error = null
        }
        picker.show(supportFragmentManager, "DATE_PICKER")
    }

    private fun validar(): Boolean {
        var ok = true
        val obligatorio = "Campo obligatorio"

        tilNombre.error = if (etNombre.text.isNullOrBlank()) obligatorio.also { ok = false } else null
        tilFecha.error = if (etFecha.text.isNullOrBlank()) obligatorio.also { ok = false } else null
        tilTelefono.error = if (etTelefono.text.isNullOrBlank()) obligatorio.also { ok = false } else null
        tilDescripcion.error = if (etDescripcion.text.isNullOrBlank()) obligatorio.also { ok = false } else null

        val email = etEmail.text.toString().trim()
        tilEmail.error = when {
            email.isEmpty() -> obligatorio.also { ok = false }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Email no válido".also { ok = false }
            else -> null
        }
        return ok
    }
}