package com.github.yinyuetai.domain;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/19
 * YinYueTai
 */
public class YinYueProgramBean {

    /**
     * videos : [{"id":2573501,"title":"是谁抢走了他的麦克风 - 潘玮柏专访","description":"#STAR!调查团# 华语乐坛十大未解之谜之一，到底是谁抢走了#潘玮柏# 的麦克风？当 \n事人潘帅#潘玮柏# 亲口揭开谜底啦！台哥也是听得一脸懵圈！给\u201c妻子\u201d#杨丞\n琳# 打分，不及格？！友谊的小船说翻就翻！现场演绎霸道总裁，吓懵节目组，没想到\n你是这样的潘帅！","artists":[{"artistId":30971,"artistName":"STAR!调查团"},{"artistId":28,"artistName":"潘玮柏"}],"artistName":"STAR!调查团 & 潘玮柏","posterPic":"http://img3.yytcdn.com/video/mv/160518/2573501/-M-6df5766e0e1ea03c7fb135c74cfeb80f_240x135.jpg","thumbnailPic":"http://img3.yytcdn.com/video/mv/160518/2573501/-M-6df5766e0e1ea03c7fb135c74cfeb80f_240x135.jpg","albumImg":"http://img3.yytcdn.com/video/mv/160518/2573501/-M-6df5766e0e1ea03c7fb135c74cfeb80f_640x360.jpg","regdate":"2016-05-18 17:55","videoSourceTypeName":"yinyuetai","totalViews":29957,"totalPcViews":13253,"totalMobileViews":16704,"totalComments":30,"url":"http://dd.yinyuetai.com/uploads/videos/common/7CC50154C34AE6BAF29085634043288B.mp4?sc=93f3fa3a9edd763e&br=578&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/6F2C0154C2F18C06B277A0483E6BA162.flv?sc=e4f8b83bbaeacc41&br=781&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/40F10154C34AE6C91DB704F2AC0E4959.flv?sc=459cde93a0ec98cf&br=1103&rd=Android","shdUrl":"http://he.yinyuetai.com/uploads/videos/common/1FBE0154C70A0BCEEF655C4EA4CDBA2E.flv?sc=5d99f4dba867b323&br=3111&rd=Android","videoSize":39292462,"hdVideoSize":53035109,"uhdVideoSize":74862956,"shdVideoSize":211188656,"duration":542,"status":200,"linkId":0,"playListPic":"http://img3.yytcdn.com/video/mv/160518/2573501/-M-6df5766e0e1ea03c7fb135c74cfeb80f_240x135.jpg"}]
     * totalCount : 2027
     */

    private int totalCount;
    /**
     * id : 2573501
     * title : 是谁抢走了他的麦克风 - 潘玮柏专访
     * description : #STAR!调查团# 华语乐坛十大未解之谜之一，到底是谁抢走了#潘玮柏# 的麦克风？当
     事人潘帅#潘玮柏# 亲口揭开谜底啦！台哥也是听得一脸懵圈！给“妻子”#杨丞
     琳# 打分，不及格？！友谊的小船说翻就翻！现场演绎霸道总裁，吓懵节目组，没想到
     你是这样的潘帅！
     * artists : [{"artistId":30971,"artistName":"STAR!调查团"},{"artistId":28,"artistName":"潘玮柏"}]
     * artistName : STAR!调查团 & 潘玮柏
     * posterPic : http://img3.yytcdn.com/video/mv/160518/2573501/-M-6df5766e0e1ea03c7fb135c74cfeb80f_240x135.jpg
     * thumbnailPic : http://img3.yytcdn.com/video/mv/160518/2573501/-M-6df5766e0e1ea03c7fb135c74cfeb80f_240x135.jpg
     * albumImg : http://img3.yytcdn.com/video/mv/160518/2573501/-M-6df5766e0e1ea03c7fb135c74cfeb80f_640x360.jpg
     * regdate : 2016-05-18 17:55
     * videoSourceTypeName : yinyuetai
     * totalViews : 29957
     * totalPcViews : 13253
     * totalMobileViews : 16704
     * totalComments : 30
     * url : http://dd.yinyuetai.com/uploads/videos/common/7CC50154C34AE6BAF29085634043288B.mp4?sc=93f3fa3a9edd763e&br=578&rd=Android
     * hdUrl : http://hc.yinyuetai.com/uploads/videos/common/6F2C0154C2F18C06B277A0483E6BA162.flv?sc=e4f8b83bbaeacc41&br=781&rd=Android
     * uhdUrl : http://hd.yinyuetai.com/uploads/videos/common/40F10154C34AE6C91DB704F2AC0E4959.flv?sc=459cde93a0ec98cf&br=1103&rd=Android
     * shdUrl : http://he.yinyuetai.com/uploads/videos/common/1FBE0154C70A0BCEEF655C4EA4CDBA2E.flv?sc=5d99f4dba867b323&br=3111&rd=Android
     * videoSize : 39292462
     * hdVideoSize : 53035109
     * uhdVideoSize : 74862956
     * shdVideoSize : 211188656
     * duration : 542
     * status : 200
     * linkId : 0
     * playListPic : http://img3.yytcdn.com/video/mv/160518/2573501/-M-6df5766e0e1ea03c7fb135c74cfeb80f_240x135.jpg
     */

    private List<VideoBean> videos;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<VideoBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoBean> videos) {
        this.videos = videos;
    }


}
