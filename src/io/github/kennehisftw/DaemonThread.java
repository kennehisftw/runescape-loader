package io.github.kennehisftw;

/**
 * Created by Kenneth on 6/15/2014.
 */
public class DaemonThread extends Thread {

    public DaemonThread() {
        setDaemon(true);
    }

}
