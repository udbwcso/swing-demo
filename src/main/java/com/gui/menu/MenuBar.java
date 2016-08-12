package com.gui.menu;

import com.gui.util.ComponentUtil;
import com.gui.window.CodeFrame;
import sun.swing.DefaultLookup;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

/**
 * Created by Administrator on 2016/8/10.
 */
public class MenuBar extends JMenuBar implements ActionListener, ItemListener{

    public MenuBar(){
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
        //Build the first menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        this.add(menu);
        //a group of JMenuItems
        menuItem = new JMenuItem("New", KeyEvent.VK_N);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        Icon icon = DefaultLookup.getIcon(menuItem, menuItem.getUI(), "FileView.directoryIcon");
        menuItem = new JMenuItem("Open", KeyEvent.VK_O);
        menuItem.setIcon(icon);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //createImageIcon("images/middle.gif");
//        menuItem = new JMenuItem(icon);
//        menuItem.setMnemonic(KeyEvent.VK_D);
//        menuItem.addActionListener(this);
//        menu.add(menuItem);
        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        menu.add(rbMenuItem);
//
        //a group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);

        //a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Another Menu");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        this.add(menu);
    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());

        switch (source.getActionCommand()){
            case "Open":
                JFileChooser fc = new JFileChooser();
                //Add a custom file filter and disable the default
                //(Accept All) file filter.
                fc.addChoosableFileFilter(new CodeFileFilter());
                fc.setAcceptAllFileFilterUsed(false);
                //可以选择文件和文件夹
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int returnVal = fc.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    CodeFrame frame = (CodeFrame) this.getRootPane().getParent();
                    JTextArea logTextArea = frame.getLogTextArea();
                    logTextArea.append("open:" + file.getAbsolutePath() + "\n");
                    logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
                    ComponentUtil.getFileTree(this).reload(file);
                } else {
                }
                break;
            default:
                break;
        }
    }

    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
//        String s = "Item event detected."
//                + newline
//                + "    Event source: " + source.getText()
//                + " (an instance of " + getClassName(source) + ")"
//                + newline
//                + "    New state: "
//                + ((e.getStateChange() == ItemEvent.SELECTED) ?
//                "selected":"unselected");
//        output.append(s + newline);
//        output.setCaretPosition(output.getDocument().getLength());
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MenuBar.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
