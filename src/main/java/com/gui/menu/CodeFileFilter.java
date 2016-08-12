package com.gui.menu;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class CodeFileFilter extends FileFilter {

    public boolean accept(File f) {
//        if (f.isDirectory()) {
//            return true;
//        }
        return true;
    }

    //The description of this filter
    public String getDescription() {
        return "All Files";
    }
}
