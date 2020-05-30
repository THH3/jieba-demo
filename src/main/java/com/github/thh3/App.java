package com.github.thh3;

import org.jsoup.Jsoup;
import org.manlier.analysis.jieba.JiebaSegmenter;
import org.manlier.analysis.jieba.SegToken;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * jieba doing tags
 */
public class App {
    public static void main(String[] args) {
        String url = "http://www.qiche365.org.cn/index.php?m=all&c=news&a=detail&id=24978.html";
        String html = getHtml(url);
        // todo get aside content
        // xpath = /html/body/div[1]/div[2]/div/div/div/aside[2]
        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        List<SegToken> segTokens = jiebaSegmenter.process(html, JiebaSegmenter.SegMode.INDEX);
        String[] words = {"哥瑞", "GREIZ", "竞瑞", "GIENIA", "杰德", "JADE", "思域", "CIVIC", "思铂睿", "SPIRIOR"};
        List<String> dicts = Arrays.asList(words);
        Set<String> tags = segTokens.stream().filter(e -> dicts.contains(e.word)).map(e -> e.word).collect(Collectors.toSet());
        System.out.println(tags);
    }
    public static String getHtml(String url) {
        try {
//            Connection connection = Jsoup.connect(url);
//            // 3s seconds timeout
//            connection.timeout(3500);
//            return connection.get().html();
            return Jsoup.connect(url).get().html();
        } catch (Exception e) {

        }
        return "";
    }
}
