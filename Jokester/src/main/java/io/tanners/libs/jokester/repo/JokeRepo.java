package io.tanners.libs.jokester.repo;


import java.util.HashSet;
import java.util.Scanner;

import io.tanners.libs.jokester.model.Joke;

/**
 * This can be any way to get the data, for now let's just keep it simple
 */
public class JokeRepo {

    public static HashSet<Joke> getJokes()
    {
        Scanner mScanner = new Scanner("../assets/jokes");

        HashSet<Joke> mJokesSet = new HashSet<Joke>();

        while(mScanner.hasNext())
        {
            String mLine = mScanner.nextLine();

            if(!mLine.isEmpty() || !mLine.equals(" "))
                mJokesSet.add(new Joke(mLine));
        }

        return mJokesSet;
    }

//    public static String objToJson(JokeWrapper mWrapper)
//    {
//        return (new Gson()).toJson(mWrapper);
//    }
}
