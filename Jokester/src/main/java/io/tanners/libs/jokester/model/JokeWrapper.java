package io.tanners.libs.jokester.model;

import java.util.HashSet;

/**
 * This is used due to google clound endpoint return types
 */
public class JokeWrapper {
    private HashSet<Joke> mJokes;
    private int count;

    public JokeWrapper() { }

    public HashSet<Joke> getmJokes() {
        return mJokes;
    }

    public void setmJokes(HashSet<Joke> mJokes) {
        this.mJokes = mJokes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
