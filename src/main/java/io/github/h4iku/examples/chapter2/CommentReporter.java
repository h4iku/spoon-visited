package io.github.h4iku.examples.chapter2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtComment;
import spoon.reflect.declaration.CtCompilationUnit;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.filter.TypeFilter;

public class CommentReporter {

    private static final String FILE_PATH = "src/main/java/io/github/h4iku/samples/ReversePolishNotation.java";

    public static void main(String[] args) {

        Launcher launcher = new Launcher();
        launcher.addInputResource(FILE_PATH);
        CtModel model = launcher.buildModel();

        List<CtComment> comments = model.getElements(new TypeFilter<CtComment>(CtComment.class));

        // To get orphan comments outside the class
        CtCompilationUnit cu = ((CtType<?>) model.getAllTypes().toArray()[0]).getPosition().getCompilationUnit();
        comments.addAll(cu.getComments());

        // A set of comments that are associated with an element
        Set<CtComment> nonOrphanComments = getNonOrphanComments(model);

        List<CommentReportEntry> commentEntries = comments.stream()
                .map((CtComment comment) -> new CommentReportEntry(comment.getCommentType().toString(),
                        comment.getContent(),
                        comment.getPosition().isValidPosition() ? comment.getPosition().getLine() : -1,
                        !nonOrphanComments.contains(comment)))
                .collect(Collectors.toList());

        commentEntries.forEach(System.out::println);

    }

    private static Set<CtComment> getNonOrphanComments(CtModel model) {

        Set<CtComment> commentsSet = new HashSet<CtComment>();
        model.getElements(new TypeFilter<>(CtElement.class)).forEach(element -> {
            commentsSet.addAll(element.getComments());
        });
        return commentsSet;
    }

    private static class CommentReportEntry {
        private String type;
        private String text;
        private int lineNumber;
        private boolean isOrphan;

        CommentReportEntry(String type, String text, int lineNumber, boolean isOrphan) {
            this.type = type;
            this.text = text;
            this.lineNumber = lineNumber;
            this.isOrphan = isOrphan;
        }

        @Override
        public String toString() {
            return lineNumber + "|" + type + "|" + isOrphan + "|" + text.replaceAll("\\n", " ").trim();
        }
    }

}
