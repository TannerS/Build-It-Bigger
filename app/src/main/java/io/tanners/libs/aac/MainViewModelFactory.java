package io.tanners.libs.aac;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import java.util.HashSet;
import io.tanners.libs.jokester.model.Joke;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    // repo to get the data
    private JokeRepository mJokeRepository;
    private Application application;
    private static volatile MainViewModelFactory INSTANCE;

    public MainViewModelFactory(Application application, JokeRepository mJokeRepository){
        this.application = application;
        this.mJokeRepository = mJokeRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(application, mJokeRepository);
    }

    public static MainViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (MainViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainViewModelFactory(application, JokeRepository.getInstance(application.getApplicationContext()));
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<HashSet<Joke>> getJokes()
    {
        return null;
    }
}