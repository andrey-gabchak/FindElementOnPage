package com.gabchak.jsoup;

import com.gabchak.Executor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class JsoupService {

    private final static String CHARSET_NAME = "utf8";

    private static Logger LOGGER = LoggerFactory.getLogger(Executor.class);

    public Elements getSimilarElements(String originalFilePath, String originalElementId, String[] filePathsForSearch) {

        Elements result = new Elements();

        for (String filePath : filePathsForSearch) {

            try {
                result.addAll(findSimilarElements(filePath,
                        getOriginalElement(originalFilePath, originalElementId)));
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("App could not parse file ", e);
            }
        }
        return result;
    }



    private Elements findSimilarElements(String filePath, Element originalElement) throws IOException {

        Document document = getDocument(filePath);
        Elements result = new Elements();

        Element elementById = document.getElementById(originalElement.id());
        if (elementById != null) {
            result.add(elementById);
        } else {
            Elements elementsByClass = document.getElementsByClass(originalElement.className());
            if (!elementsByClass.isEmpty()) {
                result.addAll(elementsByClass);
            } else {
                Elements titleElements = document
                        .getElementsByAttributeValueContaining("title",
                                originalElement.attr("title"));
                if (!titleElements.isEmpty()) {
                    result.addAll(titleElements);
                } else {
                    Elements elementsContainingText = document.getElementsContainingText(originalElement.text());
                    if (!elementsContainingText.isEmpty()) {
                        result.addAll(elementsContainingText);
                    }
                }
            }
        }

        return result;
    }

    private Element getOriginalElement(String filePath, String elementId) throws IOException {
        return getDocument(filePath).getElementById(elementId);
    }

    private Document getDocument(String filePath) throws IOException {

        File htmlFile = new File(filePath);
        return Jsoup.parse(
                htmlFile,
                CHARSET_NAME,
                htmlFile.getAbsolutePath());
    }
}
