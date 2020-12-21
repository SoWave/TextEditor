package editor.components;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadButton extends JButton {
    /**
     * Creates load button that opens text file
     * and shows content in text area
     * @param jfc file chooser - points file to open
     * @param textArea content to be saved in specified file
     */
    public LoadButton(JFileChooser jfc, JTextArea textArea) {
        super("Open");
        this.setName("OpenButton");

        this.addActionListener(x -> loadFromFile(jfc, textArea));
    }

    /**
     * Loads content from text file.
     * If file have different type than text prints error message to the console
     * @param jfc file chooser - points file to open
     * @param contentScreen text area where content of the file is showed
     */
    private void loadFromFile(JFileChooser jfc, JTextArea contentScreen) {
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                Path pathToFile = Paths.get(jfc.getSelectedFile().getPath());
                contentScreen.setText(Files.readString(pathToFile));
            } catch (Exception e) {
                System.out.println("No such file / bad type");
                contentScreen.setText("");
            }
        }
    }
}
