package com.example.simpleroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_mahasiswa")
public class Mahasiswa {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nim")
    private int id;

    private String nama;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
