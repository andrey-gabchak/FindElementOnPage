package com.gabchak;

import org.jsoup.select.Elements;

import java.util.Arrays;

public class Executor {


    public static void main(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException("Wrong arguments. You need input at least 2 file paths.");
        }

        String originalFilePath = args[0];
        String[] filePathsForSearch = Arrays.copyOfRange(args, 1, args.length);

        String originalElementId = "make-everything-ok-button";

        Elements similarElements = Factory.getJsoupService()
                .getSimilarElements(originalFilePath, originalElementId, filePathsForSearch);


        similarElements.forEach(element -> System.out.println(
                Factory.getOutputService().getPathAsString(element)));
    }


}
