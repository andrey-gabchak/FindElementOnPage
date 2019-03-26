package com.gabchak.services.impl;

import com.gabchak.services.OutputService;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OutputConsoleService implements OutputService {

    @Override
    public String outputItem(Element element) {
        StringBuilder stringBuilder = new StringBuilder();
        Elements parents = element.parents();

        for (int i = parents.size() - 1; i >= 0; i--) {
            stringBuilder.append(parents.get(i).nodeName()).append(" > ");
        }
        stringBuilder.append(element.nodeName());
        return stringBuilder.toString();
    }
}
