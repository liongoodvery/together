package org.lion.together.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import javax.inject.Inject;

/**
 * Created by lion on 2016-11-27
 */
@Entity
public class Todo {
    @Id
    public Long id;
    public String title;
    public long createTime;
    public long updateTime;
    public long planDoTime;
    public int importance;
    public String description;

    @Inject
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

    @Generated(hash = 225872573)
    public Todo(Long id, String title, long createTime, long updateTime, long planDoTime, int importance,
            String description) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.planDoTime = planDoTime;
        this.importance = importance;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getPlanDoTime() {
        return this.planDoTime;
    }

    public void setPlanDoTime(long planDoTime) {
        this.planDoTime = planDoTime;
    }

    public int getImportance() {
        return this.importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
