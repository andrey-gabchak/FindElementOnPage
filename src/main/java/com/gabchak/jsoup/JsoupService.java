package com.gabchak.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


public class JsoupService {

    private final static String CHARSET_NAME = "utf8";

    public Document getDocument(File htmlFile) throws IOException {
            return Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());

    }

    public Elements findSimilarElements(File htmlFile, Element originalElement) throws IOException {

        Document document = getDocument(htmlFile);
        Elements result = new Elements();

        Element elementById = document.getElementById(originalElement.id());
        if (elementById != null) {
            result.add(elementById);
        } else {
            Elements elementsByClass = document.getElementsByClass(originalElement.className());
            if (!elementsByClass.isEmpty()) {
                result.addAll(elementsByClass);
            } else {
                Elements titleElements = document.getElementsByAttributeValueContaining("title", originalElement.attr("title"));
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


    public String getPathAsString(Element element) {
        StringBuilder stringBuilder = new StringBuilder();
        Elements parents = element.parents();

        for (int i = parents.size() - 1; i >= 0; i--) {
            stringBuilder.append(parents.get(i).nodeName()).append(" > ");
        }
        stringBuilder.append(element.nodeName());
        return stringBuilder.toString();
    }

}
