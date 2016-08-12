package com.gui.tree;

/**
 * Created by Administrator on 2016/8/10.
 */
public class Node {
    private String name;
    private String path;
    private String content;

    public Node(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Node(String name, String path, String content) {
        this.name = name;
        this.path = path;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
