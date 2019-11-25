package com.example.elasticsearch.controller;

import com.example.elasticsearch.bean.LOLEsRespository;
import com.example.elasticsearch.bean.LoLEs;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by hezq on 2019/11/22
 * 欢迎来到英雄联盟
 */
@RestController
public class LoLHeroController {
    @Resource
    private LOLEsRespository lolEsRespository;
    /**
     * 创建英雄
     * @Author hezq
     * @Date 2019/11/22 20:43
     * @param id id
     * @param heroName 英雄名字
     * @param userName 玩家名字
     * @return java.lang.String
     **/
    @GetMapping("/createHero")
    public String create(@RequestParam("id") Long id,
                         @RequestParam("heroName") String heroName,
                         @RequestParam("userName") String userName){
        LoLEs loLEs = new LoLEs();
        loLEs.setId(id);
        loLEs.setUserName(userName);
        loLEs.setHeroName(heroName);
        return lolEsRespository.save(loLEs).toString();
    }
    private String names;
    @GetMapping("/get")
    public String get() {
        names = "";
        Iterable<LoLEs> userES = lolEsRespository.findAll();
        userES.forEach(userES1 -> {
            names += userES1.toString() + "\n";
        });
        return names;
    }
    private String searchs = "";
    @GetMapping("/search")
    public String search(@RequestParam("searchKey") String searchKey) {
        searchs = "";
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("userName", searchKey));
        // 搜索，获取结果
        Page<LoLEs> items = lolEsRespository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        searchs += "总共数据数：" + total + "\n";
        items.forEach(userES -> {
            searchs += userES.toString() + "\n";
        });
        return searchs;
    }

}
