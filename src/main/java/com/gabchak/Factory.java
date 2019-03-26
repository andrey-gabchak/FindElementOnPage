package com.gabchak;

import com.gabchak.jsoup.JsoupService;
import com.gabchak.jsoup.OutputService;

public class Factory {

    public static JsoupService getJsoupService() {
        return new JsoupService();
    }

    public static OutputService getOutputService() {
        return new OutputService();
    }
}
