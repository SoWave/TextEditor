package editor;

import editor.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class TextEditor extends JFrame {
    public TextEditor() {
        super("Text editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setMinimumSize(new Dimension(650, 100));

        addComponents();

        setVisible(true);
    }

    private void addComponents() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jFileChooser.setName("FileChooser");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt"));

        JTextArea textArea = new TextBox();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");

        JButton saveButton = new SaveButton(jFileChooser, textArea);
        JButton loadButton = new LoadButton(jFileChooser, textArea);

        JPanel filePanel = new JPanel(new FlowLayout());
        filePanel.add(loadButton, BorderLayout.EAST);
        filePanel.add(saveButton, BorderLayout.WEST);

        JMenuBar menu = new FileMenu(loadButton, saveButton);

        JPanel searchPanel = new SearchBar(textArea);

        JPanel topBar = new JPanel(new FlowLayout());

        panel.add(jFileChooser);
        setJMenuBar(menu);

        topBar.add(filePanel);
        topBar.add(searchPanel);

        panel.add(topBar, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }
}
