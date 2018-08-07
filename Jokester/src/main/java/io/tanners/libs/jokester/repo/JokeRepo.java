package io.tanners.libs.jokester.repo;

import java.util.ArrayList;
import java.util.Scanner;

import io.tanners.libs.jokester.model.Joke;


/**
 * This can be any way to get the data, for now let's just keep it simple
 */
public class JokeRepo {

    public static ArrayList<Joke> getJokes()
    {
        Scanner mScanner = new Scanner("../assets/jokes");

        ArrayList<Joke> mJokes = new ArrayList<Joke>();

        while(mScanner.hasNext())
        {
            String mLine = mScanner.nextLine();

            if(!mLine.isEmpty() || !mLine.equals(" "))
                mJokes.add(new Joke(mLine));
        }

        return mJokes;
    }
}
