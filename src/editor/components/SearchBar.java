package editor.components;

import editor.SearchThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class SearchBar extends JPanel {
    private SearchThread searchThread =
            null; // placeholder
    private boolean useRegex = false;

    /**
     * Search bar that is responsible for searching user defined matches in
     * text area - text file. User can define if he wants to use regular expressions.
     * If there are multiple matches user can navigate between them by using previous and next buttons.
     * @param textArea content where is searched match
     */
    public SearchBar(JTextArea textArea) {
        super(new FlowLayout());
        JTextField searchBox = new JTextField("", 10);
        searchBox.setName("SearchField");
        searchBox.setMinimumSize(new Dimension(80,20));

        JButton searchButton = new JButton("Search");
        searchButton.setName("StartSearchButton");
        searchButton.addActionListener(x -> {
            searchThread = new SearchThread(textArea, searchBox.getText(), useRegex);
            searchThread.searchText(textArea);
        });

        JButton previousMatchButton = new JButton("Previous");
        previousMatchButton.setName("PreviousMatchButton");
        previousMatchButton.addActionListener(x -> searchThread.previousMatch(textArea));

        JButton nextMatchButton = new JButton("Next");
        nextMatchButton.setName("NextMatchButton");
        nextMatchButton.addActionListener(x -> searchThread.nextMatch(textArea));

        JCheckBox regexMatchButton = new JCheckBox("Use regex");
        regexMatchButton.setName("UseRegExCheckbox");
        regexMatchButton.addItemListener(x -> {
            if (x.getStateChange() == ItemEvent.SELECTED) {
                useRegex = true;
            } else if (x.getStateChange() == ItemEvent.DESELECTED){
                useRegex = false;
            }
        });

        this.add(searchBox);
        this.add(searchButton);
        this.add(previousMatchButton);
        this.add(nextMatchButton);
        this.add(regexMatchButton);
    }
}
