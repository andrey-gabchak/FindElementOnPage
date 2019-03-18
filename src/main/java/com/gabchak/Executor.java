package com.gabchak;

import com.gabchak.jsoup.JsoupService;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Executor {

    private static Logger LOGGER = LoggerFactory.getLogger(Executor.class);

    private static String ORIGINAL_ELEMENT_ID = "make-everything-ok-button";

    public static void main(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException("Wrong arguments. You need input at least 2 paths to file.");
        }

        String originalFilePath = args[0];
        String[] filesPathsForSearch = Arrays.copyOfRange(args, 1, args.length);


        JsoupService jsoupService = new JsoupService();

        Element originalElement = null;
        try {
            originalElement = jsoupService.getDocument(new File(originalFilePath))
                    .getElementById(ORIGINAL_ELEMENT_ID);
        } catch (IOException e) {
            LOGGER.error("Error reading [{}] file", originalFilePath, e);
        }

        Elements result = new Elements();
        for (String filesPath : filesPathsForSearch) {
            try {
                result.addAll(
                        jsoupService.findSimilarElements(new File(filesPath), originalElement));
            } catch (IOException e) {
                LOGGER.error("Error reading [{}] file", filesPath, e);

            }
        }

        result.forEach(element -> System.out.println(jsoupService.getPathAsString(element)));
    }


}
