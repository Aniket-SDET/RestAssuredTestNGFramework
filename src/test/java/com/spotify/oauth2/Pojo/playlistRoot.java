package com.spotify.oauth2.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter @Setter @Builder @Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class playlistRoot {

    private String collaborative;
    private String description;
    @JsonProperty("external_urls")
    private external_urls externalUrls;
    private followers followers;
    private String href;
    private String id;
    private List<Object> images;
    private String name;
    private owner owner;
    private String primary_color;
    @JsonProperty("public")
    private Boolean isPublic;
    private String snapshot_id;
    private items items;
    private String type;
    private String uri;

    public playlistRoot(){

    }

    public playlistRoot(String collaborative, String description, external_urls externalUrls, followers followers, String href, String id, List<Object> images, String name, owner owner, String primary_color, Boolean isPublic, String snapshot_id, items items, String type, String uri) {
        this.collaborative = collaborative;
        this.description = description;
        this.externalUrls = externalUrls;
        this.followers = followers;
        this.href = href;
        this.id = id;
        this.images = images;
        this.name = name;
        this.owner = owner;
        this.primary_color = primary_color;
        this.isPublic = isPublic;
        this.snapshot_id = snapshot_id;
        this.items = items;
        this.type = type;
        this.uri = uri;
        //Any code after this is Lombok generated code
    }


    /*public String getCollaborative() {
        return collaborative;
    }

    public void setCollaborative(String collaborative) {
        this.collaborative = collaborative;
    }

    public String getDescription() {
        return description;
    }

    public playlistRoot setDescription(String description) {

        this.description = description;
        return this;

    }

    public external_urls getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(external_urls externalUrls) {
        this.externalUrls = externalUrls;
    }

    public followers getFollowers() {
        return followers;
    }

    public void setFollowers(followers followers) {
        this.followers = followers;
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

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public playlistRoot setName(String name) {
        this.name = name;
        return this;
    }

    public owner getOwner() {
        return owner;
    }

    public void setOwner(owner owner) {
        this.owner = owner;
    }

    public String getPrimary_color() {
        return primary_color;
    }

    public void setPrimary_color(String primary_color) {
        this.primary_color = primary_color;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public playlistRoot setPublic(Boolean aPublic) {

        isPublic = aPublic;
        return this;
    }

    public String getSnapshot_id() {
        return snapshot_id;
    }

    public void setSnapshot_id(String snapshot_id) {
        this.snapshot_id = snapshot_id;
    }

    public items getItems() {
        return items;
    }

    public void setItems(items items) {
        this.items = items;
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
    }*/
}
