package com.jiyun.qcloud.dashixummoban.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by KING on 2017/8/16 15:38
 * 邮箱:992767879@qq.com
 */
@Entity
public class Sousuolei {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "name")
    private String name;
    @Generated(hash = 737766090)
    public Sousuolei(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 885422100)
    public Sousuolei() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
