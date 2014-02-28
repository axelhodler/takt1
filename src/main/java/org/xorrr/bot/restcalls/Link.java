package org.xorrr.bot.restcalls;

public class Link {

    private String title;
    private String url;
    private String user;

    public String getTitle() {
        return title;
    }

    public Link setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Link setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUser() {
        return user;
    }

    public Link setUser(String user) {
        this.user = user;
        return this;
    }
}
