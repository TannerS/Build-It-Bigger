package io.tanners.libs.jokester;

import java.security.SecureRandom;
import java.util.ArrayList;
import io.tanners.libs.jokester.model.Joke;


public class Jokester {
    public Joke provideJoke() {
        return randomize(JokeRepo.getJokes());
    }

    private Joke randomize(ArrayList<Joke> mJokes)
    {
        return mJokes.get(
                (new SecureRandom()).nextInt(
                        mJokes.size()
                )
        );
    }
}
