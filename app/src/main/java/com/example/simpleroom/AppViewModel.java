package com.example.simpleroom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private AppRepository repo;
    private LiveData<List<Mahasiswa>> listMahasiswa;

    public AppViewModel(@NonNull Application application) {
        super(application);
        this.repo = new AppRepository(application);
        this.listMahasiswa = repo.getListMahasiswa();
    }

    public LiveData<List<Mahasiswa>> getListMahasiswa() {
        return repo.getListMahasiswa();
    }

    public void insertMahasiswa(Mahasiswa mahasiswa) {
        repo.insertMahasiswa(mahasiswa);
    }

    public void updateMahasiswa(Mahasiswa mahasiswa) {
        repo.updateMahasiswa(mahasiswa);
    }

    public void deleteMahasiswa(Mahasiswa mahasiswa) {
        repo.deleteMahasiswa(mahasiswa);
    }
}
