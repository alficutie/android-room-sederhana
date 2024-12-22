package com.example.simpleroom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    private Context context;
    private List<Mahasiswa> listMahasiswa;
    private Dialog dialog;
    private AppViewModel viewModel;

    public MahasiswaAdapter(AppViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setData(List<Mahasiswa> listMahasiswa) {
        if (listMahasiswa == null) this.listMahasiswa = new ArrayList<>();
        else this.listMahasiswa = listMahasiswa;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MahasiswaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.ViewHolder holder, int position) {
        Mahasiswa mahasiswa = listMahasiswa.get(position);
        int nim = mahasiswa.getId();
        String nama = mahasiswa.getNama();

        holder.tvNim.setText(String.valueOf(nim));
        holder.tvNama.setText(nama);
        holder.btnMenu.setOnClickListener(v -> {
            showMenu(v, mahasiswa);
        });
    }

    @Override
    public int getItemCount() {
        if (listMahasiswa == null) return 0;
        else return listMahasiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNim, tvNama;
        ImageView btnMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tv_nim);
            tvNama = itemView.findViewById(R.id.tv_nama);
            btnMenu = itemView.findViewById(R.id.btn_menu);
        }
    }

    public void showMenu(View view, Mahasiswa mahasiswa) {
        PopupMenu menu = new PopupMenu(context, view);
        menu.inflate(R.menu.menu);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_ubah) {
                    updateDialog(context, mahasiswa);
                } else {
                    deleteDialog(context, mahasiswa);
                }
                return false;
            }
        });
        menu.show();
    }

    public void updateDialog(Context context, Mahasiswa mahasiswa) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_box);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.show();

        TextView tvHeader = dialog.findViewById(R.id.tv_header);
        Button btnSimpan = dialog.findViewById(R.id.btn_tambah);
        EditText etNama = dialog.findViewById(R.id.et_nama);

        tvHeader.setText("Ubah Data");
        btnSimpan.setText("Simpan");
        etNama.setText(mahasiswa.getNama());

        btnSimpan.setOnClickListener(v -> {
            String nama = etNama.getText().toString();
            if(nama.isBlank()) {
                Toast.makeText(context, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                mahasiswa.setNama(nama);
                viewModel.updateMahasiswa(mahasiswa);
                dialog.dismiss();
            }
        });
    }

    public void deleteDialog(Context context, Mahasiswa mahasiswa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Hapus data?")
                .setItems(new CharSequence[]{"Ya", "Tidak"}, (dialog, pilihan) -> {
                    if (pilihan == 0) {
                        viewModel.deleteMahasiswa(mahasiswa);
                    }
        });
        builder.show();
    }
}
