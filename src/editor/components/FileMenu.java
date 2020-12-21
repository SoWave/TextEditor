package editor.components;

import javax.swing.*;

public class FileMenu extends JMenuBar {
    /**
     * Load and save file menu with exit option.
     * Needs button reference with proper operation.
     * @param load reference to load file button
     * @param save reference to save file button
     */
    public FileMenu(JButton load, JButton save) {
        super();
        JMenu fileMenu = new JMenu("File");

        fileMenu.setName("MenuFile");
        JMenuItem menuLoad = new JMenuItem("Open");
        menuLoad.setName("MenuOpen");
        menuLoad.addActionListener(x -> load.doClick());

        JMenuItem menuSave = new JMenuItem("Save");
        menuSave.setName("MenuSave");
        menuSave.addActionListener(x -> save.doClick());

        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.setName("MenuExit");
        menuExit.addActionListener(x -> System.exit(0));

        fileMenu.add(menuLoad);
        fileMenu.add(menuSave);
        fileMenu.addSeparator();
        fileMenu.add(menuExit);

        this.add(fileMenu);
    }
}
