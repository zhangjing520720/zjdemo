package com.sdy.controller.es;

import java.util.Date;
import java.util.Iterator;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdy.controller.base.BaseController;
import com.sdy.util.elasticsearch.Article;
import com.sdy.util.elasticsearch.ArticleSearchRepository;
import com.sdy.util.elasticsearch.Author;
import com.sdy.util.elasticsearch.Tutorial;

/**
 * Elasticsearch测试
 * 通过docker安装的Elasticsearch
 * 要使用ES需要把yml配置文件放开
 * @author Rex
 */
@RestController
@RequestMapping("/es")
public class ESController extends BaseController{
	
	@Autowired
    private ArticleSearchRepository articleSearchRepository;

    @RequestMapping("/add")
    public String testSaveArticleIndex() {
        Author author = new Author();
        author.setId(1L);
        author.setName("张静");
        author.setRemark("很牛逼的一个叼毛");

        Tutorial tutorial = new Tutorial();
        tutorial.setId(1L);
        tutorial.setName("elastic search");

        Article article = new Article();
        article.setId(1L);
        article.setTitle("springboot integreate elasticsearch");
        article.setAbstracts("springboot integreate elasticsearch is very easy");
        article.setTutorial(tutorial);
        article.setAuthor(author);
        article.setContent("张静真的是一个很牛逼的人物，真的");
        article.setPostTime(new Date());
        article.setClickCount(1L);

        articleSearchRepository.save(article);
        return "{'msg':'success'}";
    }

    @RequestMapping("/query")
    public String query() {
        String queryString = "spring";//搜索关键字
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        Iterable<Article> searchResult = articleSearchRepository.search(builder);
        Iterator<Article> iterator = searchResult.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        
        return json.toJSONString(searchResult);
    }
}
