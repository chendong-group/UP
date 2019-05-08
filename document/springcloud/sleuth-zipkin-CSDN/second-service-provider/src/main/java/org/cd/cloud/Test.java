package org.cd.cloud;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @classname: Test
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-26 21:14
 */
public class Test {

    static final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public void call() {
        String rssUrl = "https://blog.csdn.net/aharddreamer/rss/list";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> responseEntity = restTemplate.exchange(rssUrl, HttpMethod.GET, null, String.class);
        String html = responseEntity.getBody();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new InputSource(new ByteArrayInputStream(html.getBytes())));
            NodeList bookList = document.getElementsByTagName("item");
            for (int i = 0; i < bookList.getLength(); i++) {
                Node item = bookList.item(i);
                NodeList list = item.getChildNodes();
                if (list.getLength() > 0) {
                    Node link = list.item(3);
                    restTemplate.exchange(link.getTextContent(), HttpMethod.GET, null, String.class);
                    System.out.println("Successful for " + link.getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        int times = 1;
        for (int i = 0; i < times ; i++) {
            threadPool.execute(new Runnable() {
                public void run() {
                    System.out.println("======================Thread Name: "+ Thread.currentThread().getName() +"========================");
                    Test test = new Test();
                    test.call();
                }
            });
            //Thread.sleep(30000);
        }
        threadPool.shutdown();
    }


}
