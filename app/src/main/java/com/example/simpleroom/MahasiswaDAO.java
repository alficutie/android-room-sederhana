package com.example.simpleroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MahasiswaDAO {
    @Insert
    void insertMahasiswa(Mahasiswa mahasiswa);

    @Update
    void updateMahasiswa(Mahasiswa mahasiswa);

    @Delete
    void deleteMahasiswa(Mahasiswa mahasiswa);

    @Query("SELECT * FROM tb_mahasiswa")
    LiveData<List<Mahasiswa>> getListMahasiswa();
}
