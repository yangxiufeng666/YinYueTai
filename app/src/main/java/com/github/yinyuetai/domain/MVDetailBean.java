package com.github.yinyuetai.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**<p>详情页，有相关内容</p>
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/20
 * YinYueTai
 */
public class MVDetailBean implements Parcelable {
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
    private boolean favorite;
    private String pauseImageUrl;
    private String pauseTraceUrl;
    private String pauseClickUrl;
    /**
     * artistId : 123
     * artistName : Coldplay
     * artistAvatar : http://img0.yytcdn.com/artist/fan/150824/0/-M-1b20d7302987149a214609a028a898fe_200x200.jpg
     * area : US
     */

    private List<ArtistsBean> artists;

    private List<RelatedVideosBean> relatedVideos;
    private List<String> pauseTraceUrls;

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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getPauseImageUrl() {
        return pauseImageUrl;
    }

    public void setPauseImageUrl(String pauseImageUrl) {
        this.pauseImageUrl = pauseImageUrl;
    }

    public String getPauseTraceUrl() {
        return pauseTraceUrl;
    }

    public void setPauseTraceUrl(String pauseTraceUrl) {
        this.pauseTraceUrl = pauseTraceUrl;
    }

    public String getPauseClickUrl() {
        return pauseClickUrl;
    }

    public void setPauseClickUrl(String pauseClickUrl) {
        this.pauseClickUrl = pauseClickUrl;
    }

    public List<ArtistsBean> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistsBean> artists) {
        this.artists = artists;
    }

    public List<RelatedVideosBean> getRelatedVideos() {
        return relatedVideos;
    }

    public void setRelatedVideos(List<RelatedVideosBean> relatedVideos) {
        this.relatedVideos = relatedVideos;
    }

    public List<String> getPauseTraceUrls() {
        return pauseTraceUrls;
    }

    public void setPauseTraceUrls(List<String> pauseTraceUrls) {
        this.pauseTraceUrls = pauseTraceUrls;
    }

    public static class ArtistsBean implements Parcelable {
        private int artistId;
        private String artistName;
        private String artistAvatar;
        private String area;

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

        public String getArtistAvatar() {
            return artistAvatar;
        }

        public void setArtistAvatar(String artistAvatar) {
            this.artistAvatar = artistAvatar;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.artistId);
            dest.writeString(this.artistName);
            dest.writeString(this.artistAvatar);
            dest.writeString(this.area);
        }

        public ArtistsBean() {
        }

        protected ArtistsBean(Parcel in) {
            this.artistId = in.readInt();
            this.artistName = in.readString();
            this.artistAvatar = in.readString();
            this.area = in.readString();
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

    public static class RelatedVideosBean implements Parcelable {
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
        /**
         * artistId : 38169
         * artistName : 黄子韬
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

        public List<ArtistsBean> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsBean> artists) {
            this.artists = artists;
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
            dest.writeList(this.artists);
        }

        public RelatedVideosBean() {
        }

        protected RelatedVideosBean(Parcel in) {
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
            this.artists = new ArrayList<ArtistsBean>();
            in.readList(this.artists, ArtistsBean.class.getClassLoader());
        }

        public static final Creator<RelatedVideosBean> CREATOR = new Creator<RelatedVideosBean>() {
            @Override
            public RelatedVideosBean createFromParcel(Parcel source) {
                return new RelatedVideosBean(source);
            }

            @Override
            public RelatedVideosBean[] newArray(int size) {
                return new RelatedVideosBean[size];
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
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
        dest.writeString(this.pauseImageUrl);
        dest.writeString(this.pauseTraceUrl);
        dest.writeString(this.pauseClickUrl);
        dest.writeList(this.artists);
        dest.writeList(this.relatedVideos);
        dest.writeStringList(this.pauseTraceUrls);
    }

    public MVDetailBean() {
    }

    protected MVDetailBean(Parcel in) {
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
        this.favorite = in.readByte() != 0;
        this.pauseImageUrl = in.readString();
        this.pauseTraceUrl = in.readString();
        this.pauseClickUrl = in.readString();
        this.artists = new ArrayList<ArtistsBean>();
        in.readList(this.artists, ArtistsBean.class.getClassLoader());
        this.relatedVideos = new ArrayList<RelatedVideosBean>();
        in.readList(this.relatedVideos, RelatedVideosBean.class.getClassLoader());
        this.pauseTraceUrls = in.createStringArrayList();
    }

    public static final Parcelable.Creator<MVDetailBean> CREATOR = new Parcelable.Creator<MVDetailBean>() {
        @Override
        public MVDetailBean createFromParcel(Parcel source) {
            return new MVDetailBean(source);
        }

        @Override
        public MVDetailBean[] newArray(int size) {
            return new MVDetailBean[size];
        }
    };
}
