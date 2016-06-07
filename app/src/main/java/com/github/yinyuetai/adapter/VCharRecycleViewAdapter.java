package com.github.yinyuetai.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.yinyuetai.R;
import com.github.yinyuetai.mv.MVDetailActivity;
import com.github.yinyuetai.domain.VideoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/12
 * YinYueTai
 */
public class VCharRecycleViewAdapter extends RecyclerView.Adapter<VCharRecycleViewAdapter.ViewHolder> {

    private List<VideoBean> videosBeen = new ArrayList<>();
    private Activity activity;
    private RelativeLayout.LayoutParams layoutParams;

    public VCharRecycleViewAdapter(Activity activity, List<VideoBean> videosBeen, int mWidth, int mHeight) {
        this.activity = activity;
        this.videosBeen = videosBeen;
        layoutParams = new RelativeLayout.LayoutParams(mWidth, mHeight);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vchart_recycleview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final VideoBean videoBean = videosBeen.get(position);
        holder.posterImg.setLayoutParams(layoutParams);
        holder.itemTransbg.setLayoutParams(layoutParams);
        holder.serialNumber.setText("" + (position + 1));
        holder.score.setText(videoBean.getScore());
        holder.author.setText(videoBean.getArtistName());
        holder.title.setText(videoBean.getTitle());
        Glide.with(activity).load(videoBean.getAlbumImg()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.posterImg);
        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(activity, MVDetailActivity.class);
                intent.putExtra("id", videoBean.getId());
                activity.startActivity(intent);
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videosBeen.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.poster_img)
        ImageView posterImg;
        @Bind(R.id.serialNumber)
        TextView serialNumber;
        @Bind(R.id.score)
        TextView score;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.author)
        TextView author;
        @Bind(R.id.item_transbg)
        ImageView itemTransbg;
        @Bind(R.id.item_root)
        RelativeLayout itemRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
