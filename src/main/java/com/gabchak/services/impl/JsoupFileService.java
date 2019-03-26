package com.gabchak.services.impl;

import com.gabchak.Executor;
import com.gabchak.services.JsoupService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;


public class JsoupFileService implements JsoupService {

    private final static String CHARSET_NAME = "utf8";

    private static Logger LOGGER = LoggerFactory.getLogger(Executor.class);

    @Override
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
            result.addAll(
                    document
                            .getElementsByAttributeValueContaining("onclick", "window.ok"));
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
