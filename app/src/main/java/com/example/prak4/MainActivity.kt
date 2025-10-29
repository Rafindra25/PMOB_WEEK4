package com.example.prak4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.prak4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val daftarWarga = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set default text saat aplikasi baru dibuka
        binding.tvOutput.text = "Belum ada data warga yang tersimpan."

        // Spinner sudah kamu buat sebelumnya, tidak berubah
        val options = arrayOf("Belum Menikah", "Menikah", "Cerai")
        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spStatus.adapter = adapter

        // Tombol Simpan
        binding.btnSimpan.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val nik = binding.etNIK.text.toString()
            val kab = binding.etKabupaten.text.toString()
            val kec = binding.etKecamatan.text.toString()
            val desa = binding.etDesa.text.toString()
            val rt = binding.etRT.text.toString()
            val rw = binding.etRW.text.toString()
            val gender = if (binding.rbLaki.isChecked) "Laki-Laki" else "Perempuan"
            val status = binding.spStatus.selectedItem.toString()

            if (nama.isEmpty() || nik.isEmpty() || kab.isEmpty() || kec.isEmpty() || desa.isEmpty() || rt.isEmpty() || rw.isEmpty()) {
                Toast.makeText(this, "Harap isi semua data terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = "${daftarWarga.size + 1}. $nama ($gender) - $status\n" +
                    "NIK: $nik\n" +
                    "Alamat: RT $rt/RW $rw, $desa, $kec, $kab\n"

            daftarWarga.add(data)

            // Update tampilan daftar
            binding.tvOutput.text = daftarWarga.joinToString("\n\n")
            Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show()
        }

        // Tombol Reset
        binding.btnReset.setOnClickListener {
            daftarWarga.clear()
            binding.tvOutput.text = "Belum ada data warga yang tersimpan."
            Toast.makeText(this, "Data berhasil direset", Toast.LENGTH_SHORT).show()
        }
    }
}
