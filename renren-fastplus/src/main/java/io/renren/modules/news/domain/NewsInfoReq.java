package io.renren.modules.news.domain;

import io.renren.common.base.BaseReq;

import java.util.Date;
import java.util.List;

public class NewsInfoReq extends BaseReq {
    private static final long serialVersionUID = -407800297811289523L;
    private Long newsid;

    private String backtitle;

    private String newstype;

    private String covertype;

    private String publishtime;

    private String fronttitle;

    private String istop;

    private String ishot;

    private String newsfrom;

    private String auther;

    private String linkurl;

    private Date createat;

    private Date modityat;

    private Long createuser;

    private Long modifyuser;

    private String status;

    private String attaid;

    private String content;

    private List coverList;

    private String videoUrl;

    private String videoDesc;

    private List<ImgReq> picList;

    public List<ImgReq> getPicList() {
        return picList;
    }

    public void setPicList(List<ImgReq> picList) {
        this.picList = picList;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public List getCoverList() {
        return coverList;
    }

    public void setCoverList(List coverList) {
        this.coverList = coverList;
    }

    public Long getNewsid() {
        return newsid;
    }

    public void setNewsid(Long newsid) {
        this.newsid = newsid;
    }

    public String getBacktitle() {
        return backtitle;
    }

    public void setBacktitle(String backtitle) {
        this.backtitle = backtitle == null ? null : backtitle.trim();
    }

    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype == null ? null : newstype.trim();
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getFronttitle() {
        return fronttitle;
    }

    public void setFronttitle(String fronttitle) {
        this.fronttitle = fronttitle == null ? null : fronttitle.trim();
    }

    public String getIstop() {
        return istop;
    }

    public void setIstop(String istop) {
        this.istop = istop == null ? null : istop.trim();
    }

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot == null ? null : ishot.trim();
    }

    public String getNewsfrom() {
        return newsfrom;
    }

    public void setNewsfrom(String newsfrom) {
        this.newsfrom = newsfrom == null ? null : newsfrom.trim();
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther == null ? null : auther.trim();
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl == null ? null : linkurl.trim();
    }

    public Date getCreateat() {
        return createat;
    }

    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public Date getModityat() {
        return modityat;
    }

    public void setModityat(Date modityat) {
        this.modityat = modityat;
    }

    public Long getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }

    public Long getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(Long modifyuser) {
        this.modifyuser = modifyuser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAttaid() {
        return attaid;
    }

    public void setAttaid(String attaid) {
        this.attaid = attaid == null ? null : attaid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCovertype() {
        return covertype;
    }

    public void setCovertype(String covertype) {
        this.covertype = covertype;
    }
}