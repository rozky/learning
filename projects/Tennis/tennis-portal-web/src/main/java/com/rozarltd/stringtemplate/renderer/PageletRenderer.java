package com.rozarltd.stringtemplate.renderer;

import com.rozarltd.stringtemplate.Pagelet;
import com.watchitlater.spring.Renderer;
import com.watchitlater.spring.StringTemplateView;

public class PageletRenderer implements Renderer {

    @Override
    public Class getTypeToRender() {
        return Pagelet.class;
    }

    @Override
    public String toString(Object o) {
        return render((Pagelet) o);
    }

    @Override
    public String toString(Object o, String formatName) {
        return render((Pagelet) o);
    }

    private String render(Pagelet pagelet) {
        StringTemplateView view = new StringTemplateView();
        return pagelet.toString();
    }
}
