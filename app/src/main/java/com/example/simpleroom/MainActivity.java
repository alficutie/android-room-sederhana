package com.example.simpleroom;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppViewModel viewModel;
    RecyclerView rvUtama;
    MahasiswaAdapter adapter;
    ImageView btnTambah;
    TextView tvEmpty;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(AppViewModel.class);
        rvUtama = findViewById(R.id.rv_utama);
        adapter = new MahasiswaAdapter(viewModel);
        btnTambah = findViewById(R.id.btn_tambah);
        tvEmpty = findViewById(R.id.tv_empty);

        rvUtama.setLayoutManager(new LinearLayoutManager(this));
        rvUtama.setHasFixedSize(true);

        viewModel.getListMahasiswa().observe(this, new Observer<List<Mahasiswa>>() {
            @Override
            public void onChanged(List<Mahasiswa> listMahasiswa) {
                if(!listMahasiswa.isEmpty()) {
                    tvEmpty.setVisibility(View.GONE);
                    rvUtama.setVisibility(View.VISIBLE);

                    adapter.setData(listMahasiswa);
                    rvUtama.setAdapter(adapter);
                } else {
                    tvEmpty.setVisibility(View.VISIBLE);
                    rvUtama.setVisibility(View.GONE);
                }
            }
        });

        btnTambah.setOnClickListener(v -> {
            insertDialog();
        });
    }

    private void insertDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_box);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.show();

        EditText etNama = dialog.findViewById(R.id.et_nama);
        Button btnTambah = dialog.findViewById(R.id.btn_tambah);
        btnTambah.setOnClickListener(v -> {
            String nama = etNama.getText().toString();
            if(nama.isBlank()) {
                Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNama(nama);

                viewModel.insertMahasiswa(mahasiswa);
                dialog.dismiss();
            }
        });
    }
}