package org.lion.together.dev.todo.model;

/**
 * Created by lion on 2016-11-27
 */

public class Todo {
    public String title;
    public long createTime;
    public long updateTime;
    public long planDoTime;
    public int importance;
    public String description;

    public Todo() {
    }

    public Todo(String title, long createTime, long updateTime, long planDoTime, int importance, String description) {
        this.title = title;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.planDoTime = planDoTime;
        this.importance = importance;
        this.description = description;
    }
}
