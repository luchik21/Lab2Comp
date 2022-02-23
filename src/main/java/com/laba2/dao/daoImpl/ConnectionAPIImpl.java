package com.laba2.dao.daoImpl;

import com.laba2.dao.ConnectionAPI;
import com.laba2.models.Article;
import com.laba2.models.ArticleParser;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Component
@PropertySource("classpath:resources.properties")
public class ConnectionAPIImpl implements ConnectionAPI {

    Logger logger = Logger.getLogger(ConnectionAPIImpl.class);

    private String urlString;

    private ArticleParser articleParser;

    @Autowired
    public void setArticleParser(ArticleParser articleParser) {
        this.articleParser = articleParser;
    }

    @Autowired
    public void setUrlString(@Value("${lab3.url}") String urlString) {
        this.urlString = urlString;
    }

    public HttpURLConnection getConnection(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();//получаем соединение через юрл
            connection.setRequestMethod("GET");//с методом get
            connection.setDoInput(true);
        } catch (IOException e) {
            logger.error("Error in getConnection: " + e.getMessage());
        }
        return connection;
    }

    private String parseStream(InputStream stream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();//получаем информацию с сайта в виде строки
        } catch (IOException e) {
            logger.error("Error in parseStream: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String get() {
        try {
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
                response.append("\r");
            }
            reader.close();

            return response.toString();
        } catch (IOException e) {
            logger.error("Error in DAOAPIConnectionImpl: " + e.getMessage());
        }
        return "Error";
    }

    public List<Article> getNewsAPI(String country, String category) {
        URL url;
        try {
            if (category.equals("all")) {
                url = new URL(urlString + "/country/" + country);
            } else {
                url = new URL(urlString + "/country/" + country + "/category/" + category);
            }
            HttpURLConnection connection = getConnection(url);
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(parseStream(connection.getInputStream())));//получение json объекта с информацией
            if (jsonObject.has("Error")) {
                return null;
            }
            System.out.println(jsonObject);
            return articleParser.articleParser(jsonObject);//вытягиваем новости в лист
        } catch (IOException e) {
            logger.error("error in get " + e.getMessage());
        }
        return null;
    }
}
