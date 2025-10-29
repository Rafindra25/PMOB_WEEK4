package com.example.prak4

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // List untuk menyimpan data warga
    private val daftarWarga = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNama = findViewById<EditText>(R.id.etNama)
        val etNik = findViewById<EditText>(R.id.etNIK)
        val etKabupaten = findViewById<EditText>(R.id.etKabupaten)
        val etKecamatan = findViewById<EditText>(R.id.etKecamatan)
        val etDesa = findViewById<EditText>(R.id.etDesa)
        val etRt = findViewById<EditText>(R.id.etRT)
        val etRw = findViewById<EditText>(R.id.etRT)
        val tvDaftar = findViewById<TextView>(R.id.tvDaftar)
        val tvOutput = findViewById<TextView>(R.id.tvOutput)
        tvOutput.text = "Belum ada data warga yang tersimpan."

        val rbLaki = findViewById<RadioButton>(R.id.rbLaki)
        val rbPerempuan = findViewById<RadioButton>(R.id.rbPerempuan)

        val spinner = findViewById<Spinner>(R.id.spStatus)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnReset = findViewById<Button>(R.id.btnReset)


        // Spinner Adapter
        val options = arrayOf("Belum Menikah", "Menikah", "Cerai")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Tombol Simpan
        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString()
            val nik = etNik.text.toString()
            val kab = etKabupaten.text.toString()
            val kec = etKecamatan.text.toString()
            val desa = etDesa.text.toString()
            val rt = etRt.text.toString()
            val rw = etRw.text.toString()

            val gender = if (rbLaki.isChecked) "Laki-Laki" else "Perempuan"
            val status = spinner.selectedItem.toString()

            //  Validasi input wajib diisi
            if (nama.isEmpty() || nik.isEmpty() || kab.isEmpty() || kec.isEmpty() || desa.isEmpty() || rt.isEmpty() || rw.isEmpty()) {
                Toast.makeText(this, "Harap isi semua data terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val data = "${daftarWarga.size + 1}. $nama ($gender) - $status\n" +
                    "NIK: $nik\n" +
                    "Alamat: RT $rt/RW $rw, $desa, $kec, $kab\n"

            daftarWarga.add(data)

            // Tampilkan daftar
            if (daftarWarga.isEmpty()) {
                tvOutput.text = "Belum ada data warga yang tersimpan."
            } else {
                tvOutput.text = daftarWarga.joinToString("\n\n")
            }
            Toast.makeText(this, "Data tersimpan ", Toast.LENGTH_SHORT).show()
        }

        // Tombol Reset
        btnReset.setOnClickListener {
            etNama.text.clear()
            etNik.text.clear()
            etKabupaten.text.clear()
            etKecamatan.text.clear()
            etDesa.text.clear()
            etRt.text.clear()
            etRw.text.clear()
            rbLaki.isChecked = false
            rbPerempuan.isChecked = false
            spinner.setSelection(0)

            // Kosongkan daftar data yang sudah disimpan
            daftarWarga.clear()

            // Kembalikan tampilan daftar ke default
            tvOutput.text = "Belum ada data warga yang tersimpan."

            Toast.makeText(this, "Form telah direset ", Toast.LENGTH_SHORT).show()
        }
    }
}
