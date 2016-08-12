package com.gui.tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * Created by Administrator on 2016/8/12.
 */
public class DynamicTreeTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Node nodeInfo = (Node) node.getUserObject();
        if(new File(nodeInfo.getPath()).isDirectory()){
            this.setIcon(openIcon);
        } else if(nodeInfo.getName().endsWith(".java")){
            URL imgURL = DynamicTreeTreeCellRenderer.class.getResource("class_obj.gif");
            if (imgURL != null) {
                this.setIcon(new ImageIcon(imgURL));
            }
        } else if(nodeInfo.getName().endsWith(".txt")){
            URL imgURL = DynamicTreeTreeCellRenderer.class.getResource("file_obj.gif");
            if (imgURL != null) {
                this.setIcon(new ImageIcon(imgURL));
            }
        }
        return this;
    }
}
