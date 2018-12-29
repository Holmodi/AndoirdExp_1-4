package com.holmodi.musicplayer.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.holmodi.musicplayer.R;
import com.holmodi.musicplayer.service.PlayerService;
import com.holmodi.musicplayer.utils.Utils;

public class MainActivity extends AppCompatActivity{

    public static Uri trackURI;
    public static String artistName;
    public static String trackName;
    public static Boolean isPlaying = false;
    public static ImageButton playPauseBtn;
    public static ImageButton stopBtn;
//    public SeekBar skb;
    public static TextView artistNameTV;
    public static TextView trackNameTV;
    public static TextView infoLabel;
    public static ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPauseBtn = findViewById(R.id.play_pause_button);
        stopBtn = findViewById(R.id.stop_button);
        artistNameTV = findViewById(R.id.artist_name_tv);
        trackNameTV = findViewById(R.id.track_name_tv);
        icon = findViewById(R.id.icon);
        infoLabel = findViewById(R.id.info_label);
        //进度条

        playPauseBtn.setOnClickListener(view -> {
            if (!isPlaying) {
                Utils.sendPendingIntent("play", this);
                playPauseBtn.setImageResource(R.drawable.ic_pause_black_24dp);
            } else {
                Utils.sendPendingIntent("pause", this);
                playPauseBtn.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }
        });
//        skb = (SeekBar) findViewById(R.id.seek_bar);
//        skb.setOnSeekBarChangeListener(this);
        //设置监听
//        skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {               //seekbar设置监听，实现指哪放到哪
//            //如果点击进度条
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if (fromUser) {
//                   if(isPlaying)
//                }
//            }
//
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

        icon.setOnClickListener(view -> {
            if (trackURI == null) {
                Intent intent = new Intent();
                intent.setType("audio/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose Track"), 1);
            }
        });

        stopBtn.setOnClickListener(view -> {
            Utils.sendPendingIntent("stop", this);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            trackURI = data.getData();

            Bundle bundle = new Bundle();
            bundle.putString("action", "first_start");
            bundle.putParcelable("URI", trackURI);
            Intent serviceIntent = new Intent(this, PlayerService.class);
            serviceIntent.putExtras(bundle);
            this.startService(serviceIntent);

            MediaMetadataRetriever mData = new MediaMetadataRetriever();
            mData.setDataSource(this, trackURI);
            artistName = mData.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            trackName = mData.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            playPauseBtn.setImageResource(R.drawable.ic_pause_black_24dp);
            initView();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.clearNotificationBar(this);
    }

    private void initView() {
        artistNameTV.setText(artistName);
        trackNameTV.setText(trackName);
        playPauseBtn.setVisibility(View.VISIBLE);
        stopBtn.setVisibility(View.VISIBLE);
        infoLabel.setVisibility(View.INVISIBLE);
    }

    public static void clearView() {
        trackNameTV.setText("");
        artistNameTV.setText("");
        playPauseBtn.setVisibility(View.INVISIBLE);
        stopBtn.setVisibility(View.INVISIBLE);
        infoLabel.setVisibility(View.VISIBLE);
    }

    public static void initResume(Context context) {
        playPauseBtn.setOnClickListener(view -> {
            Utils.sendPendingIntent("pause", context);
        });
        playPauseBtn.setImageResource(R.drawable.ic_pause_black_24dp);
    }

    public static void initPause(Context context) {
        playPauseBtn.setOnClickListener(view -> {
            Utils.sendPendingIntent("play", context);
        });
        playPauseBtn.setImageResource(R.drawable.ic_play_arrow_black_24dp);
    }

}
