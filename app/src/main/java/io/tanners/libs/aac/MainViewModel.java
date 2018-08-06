package io.tanners.libs.aac;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.io.IOException;

import io.tanners.libs.jokester.model.JokeWrapper;

public class MainViewModel extends AndroidViewModel {
    private LiveData<JokeWrapper> mWrapper;
    private JokeRepository mJokeRepository;


    public MainViewModel(@NonNull Application application, JokeRepository mJokeRepository) {
        super(application);
        this.mJokeRepository = mJokeRepository;
        mWrapper = mJokeRepository.getData();
    }

    public LiveData<JokeWrapper> getmRecipes() throws IOException {
        return mJokeRepository.getData();
    }
}

