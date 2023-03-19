package com.sifan.livegoods.dao.impl;


import com.sifan.livegoods.dao.ItemDao4ES;
import com.sifan.livegoods.pojo.Item4ES;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ItemDao4ESImpl implements ItemDao4ES {

    @Resource
    private ElasticsearchRestTemplate restTemplate;


    @Override
    public void batchIndex(List<Item4ES> items) {
        List<IndexQuery> queryList = new ArrayList<>();
        for (Item4ES item : items) {
            queryList.add(new IndexQueryBuilder().withObject(item).build());
        }
        // 批量新增
        restTemplate.bulkIndex(queryList, Item4ES.class);
    }


    @Override
    public void index(Item4ES item) {
        this.batchIndex(Arrays.asList(item));
    }

    /**
     * 分页搜索， 高亮处理。
     *
     * @param city    城市
     * @param content 搜索关键字， 在title商品标题字段中匹配。
     * @param page    页码， 从0开始的
     * @param rows    查询行数
     * @return
     */
    @Override
    public Page<Item4ES> queryForPage(String city, String content, int page, int rows) {
        HighlightBuilder.Field field = new HighlightBuilder.Field("title");
        field.preTags("<span style='color:red '>");
        field.postTags("</span>");


        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("city", city))
                .should(QueryBuilders.matchQuery("title", content))
                .should(QueryBuilders.matchQuery("houseType", content))
                .should(QueryBuilders.matchQuery("rentType", content));
        queryBuilder.withQuery(query);
        queryBuilder.withHighlightFields(field);
        queryBuilder.withPageable(PageRequest.of(page, rows)); // 设置分页信息
        SearchHits<Item4ES> searchHits = restTemplate.search(queryBuilder.build(), Item4ES.class);
        List<Item4ES> item4ESList = searchHits.stream()
                .map(searchHit -> {
                            Item4ES item4ES = searchHit.getContent();
                            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
                            if (highlightFields.containsKey("title")) {
                                String highlightedName = highlightFields.get("title").get(0);
                                item4ES.setTitle(highlightedName);
                            }
                            return item4ES;
                        }
                ).collect(Collectors.toList());

        return new PageImpl<>(item4ESList, PageRequest.of(page, rows), searchHits.getTotalHits());
    }
}
