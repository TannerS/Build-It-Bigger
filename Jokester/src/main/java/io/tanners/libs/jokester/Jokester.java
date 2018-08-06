package io.tanners.libs.jokester;

import java.util.HashSet;

import io.tanners.libs.jokester.model.Joke;
import io.tanners.libs.jokester.model.JokeWrapper;
import io.tanners.libs.jokester.repo.JokeRepo;

public class Jokester {
    public JokeWrapper provideJokes() {
        JokeWrapper mWrapper = new JokeWrapper();

        HashSet<Joke> mJokes =  JokeRepo.getJokes();

        mWrapper.setCount(mJokes.size());
        mWrapper.setmJokes(mJokes);

        return mWrapper;
    }
}
