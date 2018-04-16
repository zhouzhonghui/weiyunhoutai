package io.renren.modules.news.entity;

public class NewsAttachment {
    private Long id;

    private String attatype;

    private String urlpath;

    private String content;

    private Integer sort;

    private String attaid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttatype() {
        return attatype;
    }

    public void setAttatype(String attatype) {
        this.attatype = attatype == null ? null : attatype.trim();
    }

    public String getUrlpath() {
        return urlpath;
    }

    public void setUrlpath(String urlpath) {
        this.urlpath = urlpath == null ? null : urlpath.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAttaid() {
        return attaid;
    }

    public void setAttaid(String attaid) {
        this.attaid = attaid == null ? null : attaid.trim();
    }
}