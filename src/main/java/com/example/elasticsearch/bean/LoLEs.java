package com.example.elasticsearch.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * created by hezq on 2019/11/22
 */
@Document(indexName = "lol", type = "docs", shards = 1, replicas = 0)
public class LoLEs {

    @Id
    private Long id;

    /**
     * 英雄名字
     */
    @Field(type = FieldType.Text)
    private String heroName;

    /**
     * 玩家名字
     */
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserEs{" +
                "id=" + id +
                ", heroName='" + heroName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
