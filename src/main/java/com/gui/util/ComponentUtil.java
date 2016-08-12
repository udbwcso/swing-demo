package com.gui.util;

import com.gui.tab.CloseableTabPanel;
import com.gui.tree.DynamicTree;
import com.gui.window.CodeFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/8/12.
 */
public class ComponentUtil {

    public static CodeFrame getCodeFrame(JComponent component) {
        JRootPane rootPane = component.getRootPane();
        Container container = rootPane.getParent();
        while (!(container instanceof CodeFrame)){
            container = container.getParent();
        }
        return  (CodeFrame) container;
    }

    public static DynamicTree getFileTree(JComponent component) {
        return getCodeFrame(component).getFileTree();
    }

    public static CloseableTabPanel getCloseableTabPanel(JComponent component) {
        return getCodeFrame(component).getTabbedPane1();
    }
}
