package io.github.h4iku.examples.chapter2;

import java.util.regex.Pattern;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtField;
import spoon.reflect.factory.TypeFactory;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.reflect.visitor.DefaultTokenWriter;
import spoon.reflect.visitor.filter.TypeFilter;

public class IntegerLiteralModifier {

    private static final String FILE_PATH = "src/main/java/io/github/h4iku/samples/ReversePolishNotation.java";
    private static final Pattern LOOK_AHEAD_THREE = Pattern.compile("(\\d)(?=(\\d{3})+$)");

    public static void main(String[] args) {

        Launcher launcher = new Launcher();
        launcher.addInputResource(FILE_PATH);
        CtModel model = launcher.buildModel();

        model.getElements(new TypeFilter<CtField<?>>(CtField.class)).forEach(field -> {
            CtExpression<?> initializer = field.getAssignment();

            if (initializer != null && initializer.getType().isSubtypeOf(new TypeFactory().INTEGER_PRIMITIVE)) {

                initializer.getFactory().getEnvironment().setPrettyPrinterCreator(() -> {
                    DefaultJavaPrettyPrinter defaultJavaPrettyPrinter = new DefaultJavaPrettyPrinter(
                            launcher.getFactory().getEnvironment());
                    defaultJavaPrettyPrinter.setPrinterTokenWriter(new DefaultTokenWriter() {

                        @Override
                        public DefaultTokenWriter writeLiteral(String token) {
                            getPrinterHelper().write(formatWithUnderscores(token));
                            return this;
                        }

                    });
                    return defaultJavaPrettyPrinter;
                });

            }

        });

        System.out.println(model.getAllTypes().toArray()[0]);

    }

    static String formatWithUnderscores(String value) {
        String withoutUnderscores = value.replaceAll("_", "");
        return LOOK_AHEAD_THREE.matcher(withoutUnderscores).replaceAll("$1_");
    }

}
