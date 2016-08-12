
package com.gui.tab;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.HashMap;

/**
 * A JTabbedPane which has a close ('X') icon on each tab.
 * To add a tab, use the method addTab(String, Component)
 * To have an extra icon on each tab (e.g. like in JBuilder,
 * showing the file type) use the method
 * addTab(String, Component, Icon).
 * Only clicking the 'X' closes the tab. */
public class CloseableTabPanel extends JTabbedPane implements MouseListener {

    private final HashMap<String, Component> maps = new HashMap<String, Component>();

    public CloseableTabPanel() {
        super();
        addMouseListener(this);
    }

    public void addTab(String title, String content){
        RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        if(content != null) {
            textArea.setText(content);
            RTextScrollPane sp = new RTextScrollPane(textArea);
            this.add(title, sp);
        }
    }

    @Override
    public void addTab(String title, Component component) {
        URL imgURL = CloseableTabPanel.class.getResource("close.gif");
        Icon icon = null;
        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
        }

//        this.addTab(title, component, null);
        this.addTab(title, component, icon);
        setSelectedComponent(getComponent(title));
    }

    public Component getComponent(String title) {
        return maps.get(title);
    }

    public void addTab(String title, Component component, Icon extraIcon) {
        if (maps.containsKey(title)) {
            setSelectedComponent(getComponent(title));
            return;
        }
        super.addTab(title, new CloseableTabIcon(extraIcon), component);
        component.setName(title);
        maps.put(title, component);
    }

    @Override
    public void insertTab(String title, Icon icon, Component component, String tip, int index) {
        maps.put(tip, component);
        super.insertTab(title, icon, component, tip, index);
    }

    @Override
    public void removeTabAt(int index) {
        Component component = getComponentAt(index);
        maps.remove(component.getName());
        super.removeTabAt(index);
    }
    
    @Override
    public void remove(Component component){
        maps.remove(component.getName());
        super.remove(component);
    }
    @Override
    public void removeAll(){
       super.removeAll();
       maps.clear();
    }
    public void mouseClicked(MouseEvent e) {
        int tabNumber = getUI().tabForCoordinate(this, e.getX(), e.getY());
        if (tabNumber < 0) {
            return;
        }
        Rectangle rect = ((CloseableTabIcon) getIconAt(tabNumber)).getBounds();
        if (rect.contains(e.getX(), e.getY())) {
            //the tab is being closed
            this.removeTabAt(tabNumber);
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}

/**
 * The class which generates the 'X' icon for the tabs. The constructor
 * accepts an icon which is extra to the 'X' icon, so you can have tabs
 * like in JBuilder. This value is null if no extra icon is required.
 */
class CloseableTabIcon implements Icon {

    private int x_pos;
    private int y_pos;
    private final int width;
    private final int height;
    private final Icon fileIcon;

    public CloseableTabIcon(Icon fileIcon) {
        this.fileIcon = fileIcon;
        width = 16;
        height = 16;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {

        this.x_pos = x;
        this.y_pos = y;
//        Color col = g.getColor();
//        g.setColor(new Color(250, 100, 100));
//        int y_p = y + 2;
//        g.drawLine(x + 1, y_p, x + 12, y_p);
//        g.drawLine(x + 1, y_p + 13, x + 12, y_p + 13);
//        g.drawLine(x, y_p + 1, x, y_p + 12);
//        g.drawLine(x + 13, y_p + 1, x + 13, y_p + 12);
//        g.drawLine(x + 3, y_p + 3, x + 10, y_p + 10);
//        g.drawLine(x + 3, y_p + 4, x + 9, y_p + 10);
//        g.drawLine(x + 4, y_p + 3, x + 10, y_p + 9);
//        g.drawLine(x + 10, y_p + 3, x + 3, y_p + 10);
//        g.drawLine(x + 10, y_p + 4, x + 4, y_p + 10);
//        g.drawLine(x + 9, y_p + 3, x + 3, y_p + 9);
//        g.setColor(col);
        if (fileIcon != null) {
//            fileIcon.paintIcon(c, g, x + width, y_p);
            fileIcon.paintIcon(c, g, x + 1, y + 1);
        }
    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x_pos, y_pos, width, height);
    }

}
