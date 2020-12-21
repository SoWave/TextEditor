package editor.components;

import javax.swing.*;

public class TextBox extends JTextArea {
    /**
     * Simple text area box.
     */
    public TextBox() {
        super();
        this.setName("TextArea");
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setText("");
    }
}
