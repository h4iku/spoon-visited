package io.github.h4iku.examples.chapter3;

import spoon.Launcher;

public class ConfigurationOptions {

    public void ignoreComments() {
        Launcher launcher = new Launcher();
        launcher.getEnvironment().setCommentEnabled(false);
    }

    /**
     * More on how Spoon attributes comments:
     * https://spoon.gforge.inria.fr/comments.html
     * 
     * And other configuration options:
     * https://spoon.gforge.inria.fr/mvnsites/spoon-core/apidocs/spoon/compiler/Environment.html
     */

}