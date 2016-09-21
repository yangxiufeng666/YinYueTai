package com.github.yinyuetai.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/20
 * YinYueTai
 */
public class VideoBean implements Parcelable {
    private int id;
    private String title;
    private String description;
    private String artistName;
    private String posterPic;
    private String thumbnailPic;
    private String albumImg;
    private String regdate;
    private String videoSourceTypeName;
    private int totalViews;
    private int totalPcViews;
    private int totalMobileViews;
    private int totalComments;
    private String url;
    private String hdUrl;
    private String uhdUrl;
    private String shdUrl;
    private long videoSize;
    private long hdVideoSize;
    private long uhdVideoSize;
    private long shdVideoSize;
    private int duration;
    private int status;
    private int linkId;
    private String playListPic;
    private String type;
    private String traceUrl;
    private String clickUrl;
    private String score;
    private boolean ad;
    /**
     * artistId : 30971
     * artistName : STAR!调查团
     */

    private List<ArtistsBean> artists;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
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

    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getVideoSourceTypeName() {
        return videoSourceTypeName;
    }

    public void setVideoSourceTypeName(String videoSourceTypeName) {
        this.videoSourceTypeName = videoSourceTypeName;
    }

    public int getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(int totalViews) {
        this.totalViews = totalViews;
    }

    public int getTotalPcViews() {
        return totalPcViews;
    }

    public void setTotalPcViews(int totalPcViews) {
        this.totalPcViews = totalPcViews;
    }

    public int getTotalMobileViews() {
        return totalMobileViews;
    }

    public void setTotalMobileViews(int totalMobileViews) {
        this.totalMobileViews = totalMobileViews;
    }

    public int getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getUhdUrl() {
        return uhdUrl;
    }

    public void setUhdUrl(String uhdUrl) {
        this.uhdUrl = uhdUrl;
    }

    public String getShdUrl() {
        return shdUrl;
    }

    public void setShdUrl(String shdUrl) {
        this.shdUrl = shdUrl;
    }

    public long getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(long videoSize) {
        this.videoSize = videoSize;
    }

    public long getHdVideoSize() {
        return hdVideoSize;
    }

    public void setHdVideoSize(long hdVideoSize) {
        this.hdVideoSize = hdVideoSize;
    }

    public long getUhdVideoSize() {
        return uhdVideoSize;
    }

    public void setUhdVideoSize(long uhdVideoSize) {
        this.uhdVideoSize = uhdVideoSize;
    }

    public long getShdVideoSize() {
        return shdVideoSize;
    }

    public void setShdVideoSize(long shdVideoSize) {
        this.shdVideoSize = shdVideoSize;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getPlayListPic() {
        return playListPic;
    }

    public void setPlayListPic(String playListPic) {
        this.playListPic = playListPic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<ArtistsBean> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistsBean> artists) {
        this.artists = artists;
    }

    public boolean isAd() {
        return ad;
    }

    public void setAd(boolean ad) {
        this.ad = ad;
    }

    public VideoBean() {
    }

    public static class ArtistsBean implements Parcelable {
        private int artistId;
        private String artistName;

        public int getArtistId() {
            return artistId;
        }

        public void setArtistId(int artistId) {
            this.artistId = artistId;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.artistId);
            dest.writeString(this.artistName);
        }

        public ArtistsBean() {
        }

        protected ArtistsBean(Parcel in) {
            this.artistId = in.readInt();
            this.artistName = in.readString();
        }

        public static final Creator<ArtistsBean> CREATOR = new Creator<ArtistsBean>() {
            @Override
            public ArtistsBean createFromParcel(Parcel source) {
                return new ArtistsBean(source);
            }

            @Override
            public ArtistsBean[] newArray(int size) {
                return new ArtistsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.artistName);
        dest.writeString(this.posterPic);
        dest.writeString(this.thumbnailPic);
        dest.writeString(this.albumImg);
        dest.writeString(this.regdate);
        dest.writeString(this.videoSourceTypeName);
        dest.writeInt(this.totalViews);
        dest.writeInt(this.totalPcViews);
        dest.writeInt(this.totalMobileViews);
        dest.writeInt(this.totalComments);
        dest.writeString(this.url);
        dest.writeString(this.hdUrl);
        dest.writeString(this.uhdUrl);
        dest.writeString(this.shdUrl);
        dest.writeLong(this.videoSize);
        dest.writeLong(this.hdVideoSize);
        dest.writeLong(this.uhdVideoSize);
        dest.writeLong(this.shdVideoSize);
        dest.writeInt(this.duration);
        dest.writeInt(this.status);
        dest.writeInt(this.linkId);
        dest.writeString(this.playListPic);
        dest.writeString(this.type);
        dest.writeString(this.traceUrl);
        dest.writeString(this.clickUrl);
        dest.writeString(this.score);
        dest.writeByte(this.ad ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.artists);
    }

    protected VideoBean(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.artistName = in.readString();
        this.posterPic = in.readString();
        this.thumbnailPic = in.readString();
        this.albumImg = in.readString();
        this.regdate = in.readString();
        this.videoSourceTypeName = in.readString();
        this.totalViews = in.readInt();
        this.totalPcViews = in.readInt();
        this.totalMobileViews = in.readInt();
        this.totalComments = in.readInt();
        this.url = in.readString();
        this.hdUrl = in.readString();
        this.uhdUrl = in.readString();
        this.shdUrl = in.readString();
        this.videoSize = in.readLong();
        this.hdVideoSize = in.readLong();
        this.uhdVideoSize = in.readLong();
        this.shdVideoSize = in.readLong();
        this.duration = in.readInt();
        this.status = in.readInt();
        this.linkId = in.readInt();
        this.playListPic = in.readString();
        this.type = in.readString();
        this.traceUrl = in.readString();
        this.clickUrl = in.readString();
        this.score = in.readString();
        this.ad = in.readByte() != 0;
        this.artists = in.createTypedArrayList(ArtistsBean.CREATOR);
    }

    public static final Creator<VideoBean> CREATOR = new Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel source) {
            return new VideoBean(source);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };
}
