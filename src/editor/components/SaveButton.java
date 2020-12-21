package editor.components;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveButton extends JButton {
    /**
     * Creates button to save content from textarea to file
     * specified in file chooser
     * @param jfc file chooser - points to file
     * @param textArea content that is saved to file
     */
    public SaveButton(JFileChooser jfc, JTextArea textArea) {
        super("Save");
        this.setName("SaveButton");

        this.addActionListener(x -> saveToFile(jfc, textArea));
    }

    /**
     * Saves content to file
     * @param jfc file chooser - points to file
     * @param content content that is saved to file
     */
    private void saveToFile(JFileChooser jfc, JTextArea content) {
        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                Path pathToFile = Paths.get(jfc.getSelectedFile().getPath());
                Files.writeString(pathToFile, content.getText());
            } catch (Exception e) {
                System.out.println("No such file");
            }
        }
    }
}
