package com.laba2.models;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleParser {
    private static final Logger logger = Logger.getLogger(ArticleParser.class);

    public List<Article> articleParser(JSONObject JSONArticle) {

        List<Article> newsList = new ArrayList<>();
        String author;
        try {
            JSONArray articlesObject = JSONArticle.getJSONArray("articles");

            for (int i = 0; i < articlesObject.length(); i++) {
                JSONObject arrayElement = articlesObject.getJSONObject(i);
                JSONObject source = arrayElement.getJSONObject("source");
                String sourceName = source.getString("name");
                if (!arrayElement.isNull("author")) {
                    author = arrayElement.getString("author");
                } else {
                    author = sourceName;
                }
                String title = arrayElement.optString("title");
                String description = arrayElement.optString("description");
                String url = arrayElement.getString("url");
                String urlToImage = arrayElement.optString("urlToImage");
                String publishedAt = arrayElement.optString("publishedAt");
                newsList.add(new Article(sourceName, author, title, description, url, urlToImage, publishedAt));
            }
        } catch (JSONException e) {
            logger.error("Article parser error " + e.getMessage());
        }
        return newsList;
    }
}
