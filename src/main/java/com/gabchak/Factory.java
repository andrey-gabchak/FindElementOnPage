package com.gabchak;

import com.gabchak.services.JsoupService;
import com.gabchak.services.OutputService;
import com.gabchak.services.impl.JsoupFileService;
import com.gabchak.services.impl.OutputConsoleService;

public class Factory {

    public static JsoupService getJsoupService() {
        return new JsoupFileService();
    }

    public static OutputService getOutputService() {
        return new OutputConsoleService();
    }
}
