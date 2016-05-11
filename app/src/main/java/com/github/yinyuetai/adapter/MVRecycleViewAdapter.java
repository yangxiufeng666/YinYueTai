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
import com.github.yinyuetai.domain.MVListBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/11
 * YinYueTai
 */
public class MVRecycleViewAdapter extends RecyclerView.Adapter<MVRecycleViewAdapter.ViewHolder> {

    private ArrayList<MVListBean.VideosBean> videoList = new ArrayList<>();
    private Activity activity;

    public MVRecycleViewAdapter(ArrayList<MVListBean.VideosBean> videoList,Activity activity) {
        this.videoList = videoList;
        this.activity = activity;
    }

    @Override
    public MVRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mv_recycleview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MVRecycleViewAdapter.ViewHolder holder, int position) {
        MVListBean.VideosBean videosBean = videoList.get(position);
        holder.emptyLogo.setVisibility(View.INVISIBLE);
        holder.name.setText(videosBean.getTitle());
        holder.author.setText(videosBean.getDescription());
        holder.playCount.setText("播放次数："+videosBean.getTotalViews());
        Glide.with(activity).load(videosBean.getAlbumImg()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.posterImg);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.empty_logo)
        ImageView emptyLogo;
        @Bind(R.id.poster_img)
        ImageView posterImg;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.author)
        TextView author;
        @Bind(R.id.play_count)
        TextView playCount;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
