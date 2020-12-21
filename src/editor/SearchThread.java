package editor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchThread extends SwingWorker<String, Object> {

    private final Matcher matcher;
    private final List<Integer> startIndexes = new ArrayList<>();
    private final List<Integer> endIndexes = new ArrayList<>();
    private int currentIndex = 0;

    public SearchThread(JTextArea content, String searchingText, boolean useRegex) {
        Pattern pattern = useRegex ? Pattern.compile(searchingText) : Pattern.compile(searchingText, Pattern.LITERAL);
        matcher = pattern.matcher(content.getText());
        try {
            doInBackground();
        } catch (Exception ignored) { }
    }

    @Override
    protected String doInBackground() {
        if (matcher.find()) {
            do {
                int start = matcher.start();
                int end = matcher.group().length();
                startIndexes.add(start);
                endIndexes.add(end);
            } while (matcher.find());
        }

        return "";
    }

    public void nextMatch(JTextArea content) {
        currentIndex++;
        currentIndex = currentIndex > startIndexes.size() - 1 ? 0 : currentIndex;

        searchText(content);
    }

    public void previousMatch(JTextArea content) {
        currentIndex--;
        currentIndex = currentIndex < 0 ? startIndexes.size() - 1 : currentIndex;

        searchText(content);
    }

    public void searchText(JTextArea content) {
        try {
            changeMatch(content, startIndexes.get(currentIndex), endIndexes.get(currentIndex));
        } catch (Exception noMatch) {
            System.out.println("No matches");
        }
    }

    private void changeMatch(JTextArea content, int index, int endIndex) {
        content.setCaretPosition(index + endIndex);
        content.select(index, index + endIndex);
        content.grabFocus();
    }
}
