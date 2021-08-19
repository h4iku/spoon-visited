package io.github.h4iku.examples.chapter2;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

public class MethodNamePrinter {

    private static final String FILE_PATH = "src/main/java/io/github/h4iku/samples/ReversePolishNotation.java";

    public static void main(String[] args) {

        Launcher launcher = new Launcher();
        launcher.addInputResource(FILE_PATH);
        CtModel model = launcher.buildModel();

        model.getElements(new TypeFilter<CtMethod<?>>(CtMethod.class))
                .forEach(method -> System.out.println(method.getSimpleName()));

    }
}
