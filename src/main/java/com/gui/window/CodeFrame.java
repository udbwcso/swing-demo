package com.gui.window;

import com.gui.tab.CloseableTabPanel;
import com.gui.tree.DynamicTree;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import com.gui.menu.MenuBar;

/**
 * Created by Administrator on 2016/8/10.
 */
public class CodeFrame extends JFrame{
    private JPanel panel;
    private JMenuBar menuBar;
    private JSplitPane verticalSplit;
    private JSplitPane horizontalSplit;
    private DynamicTree fileTree;
    private CloseableTabPanel tabbedPane1;
    private RSyntaxTextArea logTextArea;

    public CodeFrame(String title){
        createUIComponents();
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle(title);
        this.setSize(1000, 618);
        //窗体显示位置居中
        setLocationRelativeTo(getOwner());
        this.setVisible(true);
        //设置分割比例,必须在setVisible(true)之后使用
        horizontalSplit.setDividerLocation(0.3);
        verticalSplit.setDividerLocation(0.8);
        //当改变窗体大小时
        this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e) {
                horizontalSplit.setDividerLocation(0.3);
                verticalSplit.setDividerLocation(0.8);
            }
        });
    }

    private void createUIComponents() {
        //顶部菜单
        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);
        //右侧的代码显示区
        tabbedPane1 = new CloseableTabPanel();
        //左侧的树
        fileTree = new DynamicTree();
        //底部日志输出窗口
        logTextArea = new RSyntaxTextArea(10, 100);
        logTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        logTextArea.setCodeFoldingEnabled(true);
        RTextScrollPane logSP = new RTextScrollPane(logTextArea);
        //最底部的功能按钮
        JPanel bottomPanel = new JPanel(new GridLayout(0,3));
        bottomPanel.setPreferredSize(new Dimension(1000, 20));
//        JButton addButton = new JButton("Add");
//        addButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//            }
//        });
//        bottomPanel.add(addButton);

        //分隔左侧的树与右侧的代码显示区
        horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        horizontalSplit.setRightComponent(tabbedPane1);
        horizontalSplit.setLeftComponent(fileTree);
        horizontalSplit.setOneTouchExpandable(true);
        //分隔中部的显示区域与底部日志输出窗口
        verticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        verticalSplit.setTopComponent(horizontalSplit);
        verticalSplit.setBottomComponent(logSP);
        verticalSplit.setOneTouchExpandable(true);
        //布局
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(verticalSplit, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
    }


    public static void main(String[] args) {
        CodeFrame frame = new CodeFrame("code");
    }


    public JPanel getPanel() {
        return panel;
    }

    public JSplitPane getVerticalSplit() {
        return verticalSplit;
    }

    public JSplitPane getHorizontalSplit() {
        return horizontalSplit;
    }

    public DynamicTree getFileTree() {
        return fileTree;
    }

    public CloseableTabPanel getTabbedPane1() {
        return tabbedPane1;
    }

    public RSyntaxTextArea getLogTextArea() {
        return logTextArea;
    }
}
