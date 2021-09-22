package io.github.h4iku.examples.chapter3;

import java.util.List;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtComment;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.filter.TypeFilter;

public class PrisonRulesCommentsAttribution {

    private static final String FILE_PATH = "src/main/java/io/github/h4iku/samples/PrisonRules.java";

    public static void main(String[] args) {

        Launcher launcher = new Launcher();
        launcher.getEnvironment().setAutoImports(true);
        launcher.addInputResource(FILE_PATH);
        CtModel model = launcher.buildModel();

        model.getElements(new TypeFilter<>(CtElement.class)).forEach(element -> {
            List<CtComment> comments = element.getComments();
            if (!comments.isEmpty()) {
                System.out.println(comments);
                System.out.println("attributed to");
                System.out.println(element);
                System.out.println();
            }
        });

    }

}
