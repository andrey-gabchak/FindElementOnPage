package com.gabchak.services;

import org.jsoup.select.Elements;

public interface JsoupService {

    Elements getSimilarElements(String originalFilePath,
                                String originalElementId,
                                String[] filePathsForSearch);
}
