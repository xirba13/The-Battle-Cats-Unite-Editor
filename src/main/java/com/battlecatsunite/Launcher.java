package com.battlecatsunite;

/**
 * Non-JavaFX entry point used by the shaded JAR.
 *
 * Launching an Application subclass directly makes the JDK look for JavaFX on
 * its module path before the libraries embedded in the JAR can be loaded.
 */
public final class Launcher {
    private Launcher() {
    }

    public static void main(String[] args) {
        App.main(args);
    }
}
