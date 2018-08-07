package io.tanners.libs.jokester;

import java.security.SecureRandom;
import java.util.ArrayList;
import io.tanners.libs.jokester.model.Joke;
import io.tanners.libs.jokester.repo.JokeRepo;


public class Jokester {
    public Joke provideJoke() {
        return randomize(JokeRepo.getJokes());
    }

    private Joke randomize(ArrayList<Joke> mJokes)
    {

        System.out.print("DATA:" + mJokes.size());


//        return mJokes.get(
//                (new SecureRandom()).nextInt(
//                        mJokes.size()
//                )

//        );

        return new Joke("hello");
    }
}
