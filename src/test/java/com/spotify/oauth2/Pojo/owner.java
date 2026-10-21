package com.spotify.oauth2.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class owner {

    private String display_name;

    @JsonProperty("external_urls")
    private external_urls externalUrls;

    private  String href ;
    private String id;
    private String type;
    private String uri ;

    public owner() {
    }

    public owner(String display_name, external_urls externalUrls, String href, String id, String type, String uri) {
        this.display_name = display_name;
        this.externalUrls = externalUrls;
        this.href = href;
        this.id = id;
        this.type = type;
        this.uri = uri;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public external_urls getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(external_urls externalUrls) {
        this.externalUrls = externalUrls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
