package com.rozarltd.module.atpwebsite.page;

import com.rozarltd.html.PageElement;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TournamentTableRow extends PageElement {

    private Elements fields;

    public TournamentTableRow(Element element) {
        super(element);
        this.fields = this.element.select("td");
    }

    public String getCategoryImageAlt() {
        Elements img = fields.get(0).select("img");
        return img != null && img.size() > 0 ? img.get(0).attr("alt") : "";
    }

    public String getStartDate() {
        return fields.get(1).text();
    }

    public String getName() {
        return fields.size() > 3 ? fields.get(2).getElementsByTag("strong").get(0).text() : "";
    }

    public String getLocation() {
        return fields.size() > 3 ? fields.get(2).getElementsByTag("strong").get(1).text() : "";
    }

    public String getPrizeMoney() {
        return fields.size() > 5 ? fields.get(4).getElementsByTag("span").text() : "";
    }

    public String getSurface() {
        return fields.size() > 4 ? fields.get(3).text() : "";
    }
}
