package com.gabchak.jsoup;

import com.gabchak.Factory;
import com.gabchak.services.OutputService;
import com.gabchak.services.impl.OutputConsoleService;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsoupFileServiceTest {

    @Test
    void getSimilarElements() {
        String originalElementId = "make-everything-ok-button";
        String originalFilePath = "/home/gabchak/Downloads/AgileEngine_pages/sample-0-origin.html";
        String[] filePathsForSearch = new String[]{"/home/gabchak/Downloads/AgileEngine_pages/sample-1-evil-gemini.html",
                "/home/gabchak/Downloads/AgileEngine_pages/sample-2-container-and-clone.html",
                "/home/gabchak/Downloads/AgileEngine_pages/sample-3-the-escape.html",
                "/home/gabchak/Downloads/AgileEngine_pages/sample-4-the-mash.html"};

        Elements similarElements = Factory.getJsoupService().getSimilarElements(originalFilePath,
                originalElementId, filePathsForSearch);

        String expected0 = "html > body > div > div > div > div > div > div > a";
        String expected1 = "html > body > div > div > div > div > div > div > div > a";
        String expected2 = "html > body > div > div > div > div > div > div > a";
        String expected3 = "html > body > div > div > div > div > div > div > a";

        OutputService outputService = Factory.getOutputService();

        assertEquals(expected0, outputService.outputItem(similarElements.get(0)));
        assertEquals(expected1, outputService.outputItem(similarElements.get(1)));
        assertEquals(expected2, outputService.outputItem(similarElements.get(2)));
        assertEquals(expected3, outputService.outputItem(similarElements.get(3)));
    }
}