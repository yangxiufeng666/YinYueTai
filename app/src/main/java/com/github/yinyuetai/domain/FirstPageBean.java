package com.github.yinyuetai.domain;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/10
 * YinYueTai
 */
public class FirstPageBean {

    /**
     * type : PLAYLIST
     * id : 4196907
     * title : 防弹少年团《Fire》舞蹈版+MV
     * posterPic : http://img1.c.yinyuetai.com/others/mobile_front_page/160509/0/-M-c28d3c79398bf761f8605cd3762c8184_0x0.jpg
     * thumbnailPic : http://img1.yytcdn.com/video/mv/160508/2566040/-M-b7e499a5755cea010f48cdb1e68ba3e3_120x67.png
     * videoSize : 0
     * hdVideoSize : 0
     * uhdVideoSize : 0
     * status : 0
     * traceUrl : http://www.yinyuetai.com/v?a=102437&un=53a621a9362eb7ed4e46425ac834f4b545fe1eff443acb1e2ba5fdc547da9314f66a78b03b640904a24e6f25376102b0c1dc16842b2b37e0d446aaffccd10a8cf69d2ebc6c2e79bfe31b925f005aee7e12ef159d573c37c97845c34d5e9dc329d8763c9a0e375997
     * clickUrl : http://mapi.yinyuetai.com/statistics/click.json?id=3659
     */

    private String type;
    private int id;
    private String title;
    private String posterPic;
    private String thumbnailPic;
    private long videoSize;
    private long hdVideoSize;
    private long uhdVideoSize;
    private int status;
    private String traceUrl;
    private String clickUrl;
    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPic() {
        return posterPic;
    }

    public void setPosterPic(String posterPic) {
        this.posterPic = posterPic;
    }

    public String getThumbnailPic() {
        return thumbnailPic;
    }

    public void setThumbnailPic(String thumbnailPic) {
        this.thumbnailPic = thumbnailPic;
    }

    public long getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(int videoSize) {
        this.videoSize = videoSize;
    }

    public long getHdVideoSize() {
        return hdVideoSize;
    }

    public void setHdVideoSize(int hdVideoSize) {
        this.hdVideoSize = hdVideoSize;
    }

    public long getUhdVideoSize() {
        return uhdVideoSize;
    }

    public void setUhdVideoSize(int uhdVideoSize) {
        this.uhdVideoSize = uhdVideoSize;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTraceUrl() {
        return traceUrl;
    }

    public void setTraceUrl(String traceUrl) {
        this.traceUrl = traceUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FirstPageBean{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", posterPic='" + posterPic + '\'' +
                ", thumbnailPic='" + thumbnailPic + '\'' +
                ", videoSize=" + videoSize +
                ", hdVideoSize=" + hdVideoSize +
                ", uhdVideoSize=" + uhdVideoSize +
                ", status=" + status +
                ", traceUrl='" + traceUrl + '\'' +
                ", clickUrl='" + clickUrl + '\'' +
                '}';
    }
}
