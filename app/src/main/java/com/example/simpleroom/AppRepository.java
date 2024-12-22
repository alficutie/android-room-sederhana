package com.example.simpleroom;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private AppDatabase db;
    private MahasiswaDAO dao;
    private LiveData<List<Mahasiswa>> listMahasiswa;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AppRepository(Application application) {
        this.db = AppDatabase.getDatabase(application);
        this.dao = db.dao();
        this.listMahasiswa = dao.getListMahasiswa();
    }

    public LiveData<List<Mahasiswa>> getListMahasiswa() {
        return db.dao().getListMahasiswa();
    }

    public void insertMahasiswa(Mahasiswa mahasiswa) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.dao().insertMahasiswa(mahasiswa);
            }
        });
    }

    public void updateMahasiswa(Mahasiswa mahasiswa) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.dao().updateMahasiswa(mahasiswa);
            }
        });
    }

    public void deleteMahasiswa(Mahasiswa mahasiswa) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.dao().deleteMahasiswa(mahasiswa);
            }
        });
    }
}
