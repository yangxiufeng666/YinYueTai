package com.github.yinyuetai.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.yinyuetai.R;
import com.github.yinyuetai.domain.YueDanBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/11
 * YinYueTai
 */
public class YueDanRecycleViewAdapter extends RecyclerView.Adapter<YueDanRecycleViewAdapter.YueDanHolder> {

    static class YueDanHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.poster_img)
        ImageView posterImg;
        @Bind(R.id.profile_image)
        CircleImageView profileImage;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.author)
        TextView author;
        @Bind(R.id.play_count)
        TextView playCount;
        public YueDanHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    List<YueDanBean.PlayListsBean> playLists;
    Activity activity;

    public YueDanRecycleViewAdapter(List<YueDanBean.PlayListsBean> playLists,Activity activity) {
        this.playLists = playLists;
        this.activity = activity;
    }

    @Override
    public YueDanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yue_dan_item, parent, false);
        return new YueDanHolder(view);
    }

    @Override
    public void onBindViewHolder(YueDanHolder holder, int position) {
        YueDanBean.PlayListsBean playListsBean = playLists.get(position);
        holder.title.setText(playListsBean.getTitle());
        holder.author.setText(playListsBean.getCreator().getNickName());
        holder.playCount.setText("收录高清MV"+playListsBean.getVideoCount()+"首");
        Glide.with(activity).load(playListsBean.getPlayListBigPic()).into(holder.posterImg);
        Glide.with(activity).load(playListsBean.getCreator().getLargeAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }
}
