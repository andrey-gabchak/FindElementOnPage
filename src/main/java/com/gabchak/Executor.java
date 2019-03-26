package com.gabchak;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Executor {


    private static String ORIGINAL_ELEMENT_ID = "make-everything-ok-button";

    public static void main(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException("Wrong arguments. You need input at least 2 paths to file.");
        }

        String originalFilePath = args[0];
        String[] filePathsForSearch = Arrays.copyOfRange(args, 1, args.length);


        Elements similarElements = Factory.getJsoupService()
                .getSimilarElements(originalFilePath, ORIGINAL_ELEMENT_ID, filePathsForSearch);


        similarElements.forEach(element -> System.out.println(
                Factory.getOutputService().getPathAsString(element)));
    }


}
