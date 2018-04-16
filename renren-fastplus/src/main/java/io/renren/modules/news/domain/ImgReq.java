package io.renren.modules.news.domain;

import java.io.Serializable;

public class ImgReq implements Serializable {
    private static final long serialVersionUID = 4468993917422587213L;

    private String imgSrc;
    private String textarea;
    private String dataId;

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTextarea() {
        return textarea;
    }

    public void setTextarea(String textarea) {
        this.textarea = textarea;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    @Override
    public String toString() {
        return "ImgReq{" +
                "imgSrc='" + imgSrc + '\'' +
                ", textarea='" + textarea + '\'' +
                ", dataId='" + dataId + '\'' +
                '}';
    }
}
