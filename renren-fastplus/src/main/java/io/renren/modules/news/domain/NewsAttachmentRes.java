package io.renren.modules.news.domain;

import io.renren.common.base.BaseRes;

public class NewsAttachmentRes extends BaseRes {
    private static final long serialVersionUID = 4125318148612353572L;

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
        this.attatype = attatype;
    }

    public String getUrlpath() {
        return urlpath;
    }

    public void setUrlpath(String urlpath) {
        this.urlpath = urlpath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        this.attaid = attaid;
    }
}
