package io.github.h4iku.examples.chapter2;

import spoon.Launcher;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtMethod;

public class MethodNamePrinterProcessor {

    private static final String FILE_PATH = "src/main/java/io/github/h4iku/samples/ReversePolishNotation.java";

    public static void main(String[] args) {

        Launcher launcher = new Launcher();
        launcher.addInputResource(FILE_PATH);
        launcher.addProcessor(new MethodNameProcessor());
        launcher.run();

    }

    public static class MethodNameProcessor extends AbstractProcessor<CtMethod<?>> {

        @Override
        public void process(CtMethod<?> method) {
            System.out.println(method.getSimpleName());
        }
    }
}
