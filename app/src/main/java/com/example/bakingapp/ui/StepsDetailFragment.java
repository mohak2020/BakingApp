package com.example.bakingapp.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.steps.Steps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepsDetailFragment extends Fragment {

    private static final String TAG = "DetailFragment1";

    TextView textView;

    Steps mStep;
    Steps mStepItem;
    String mInstructions;
    String mVideoUrl;

    SimpleExoPlayer  mSimpleExoPlayer;
    PlayerView mPlayerView;
    TextView mInstructionView;
    TextView mNoVideoView;

    long mPosition;


    private long mPlayerCurrentPosition;

    public StepsDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){

            mPosition = savedInstanceState.getLong("play-position");

        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mSimpleExoPlayer!=null)
        outState.putLong("play-position", mSimpleExoPlayer.getCurrentPosition());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_steps_detail, container, false);

        mPlayerView = view.findViewById(R.id.player);
        mNoVideoView = view.findViewById(R.id.no_video);
        mInstructionView = view.findViewById(R.id.recipe_step_instruction);

        Intent intent = getActivity().getIntent();

        //Bundle bundle = new Bundle();



        boolean twoPane = getArguments().getBoolean("123");

        Log.d(TAG, "onCreateView: flag " +twoPane);

        if(twoPane ==false){
            mStep = getArguments().getParcelable("step-detail");
            mInstructions = mStep.getDescription();
            mVideoUrl = mStep.getVideoURL();
        }

//        if(getArguments().getParcelable("step-detail")==null){
//
//           // mStep = getArguments().getParcelable("step-detail");
//
//        }


//        if(mStep != null){
//
//            mInstructions = mStep.getDescription();
//            mVideoUrl = mStep.getVideoURL();
//
//        }


        mInstructionView.setText(mInstructions);

        initExoPlayer();
        mPlayerView.setPlayer(mSimpleExoPlayer);


        return view;
    }

    private void initExoPlayer() {
//        DefaultRenderersFactory renderersFactory = new DefaultRenderersFactory(
//                this,
//                null,
//                DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF
//        );

        if (mVideoUrl.isEmpty()) {
            mPlayerView.setVisibility(View.GONE);
            mNoVideoView.setVisibility(View.VISIBLE);

        } else {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector,loadControl);
            String userAgent = Util.getUserAgent(getContext(), "BakingApp");


            ExtractorMediaSource mediaSource = new ExtractorMediaSource(
                    Uri.parse(mVideoUrl), // file audio ada di folder assets
                    new DefaultDataSourceFactory(getContext(), userAgent),
                    new DefaultExtractorsFactory(),
                    null,
                    null
            );
            //DefaultTsPayloadReaderFactory.FLAG_ALLOW_NON_IDR_KEYFRAMES;
            mSimpleExoPlayer.prepare(mediaSource);
            mSimpleExoPlayer.seekTo(mPosition);
            mSimpleExoPlayer.setPlayWhenReady(false);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        if (mSimpleExoPlayer != null) {
            mSimpleExoPlayer.stop();
            mSimpleExoPlayer.release();
            mSimpleExoPlayer=null;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        //releaseExoplayer();
        if (mSimpleExoPlayer != null) {
//
            mSimpleExoPlayer.setPlayWhenReady(false);
            mSimpleExoPlayer.stop();
            mSimpleExoPlayer.release();
            mSimpleExoPlayer=null;
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mSimpleExoPlayer != null) {
//
            mSimpleExoPlayer.setPlayWhenReady(false);
            mSimpleExoPlayer.stop();
            mSimpleExoPlayer.release();
            mSimpleExoPlayer=null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initExoPlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mSimpleExoPlayer!=null)
        initExoPlayer();
//        mSimpleExoPlayer.setPlayWhenReady(false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: ");


        if (mSimpleExoPlayer != null) {
            mSimpleExoPlayer.setPlayWhenReady(false);

        }


    }

    public void setmInstructions(String mInstructions) {
        this.mInstructions = mInstructions;
    }

    public void setmVideoUrl(String mVideoUrl) {
        this.mVideoUrl = mVideoUrl;
    }
}


//  textView =view.findViewById(R.id.text123);
//
//          textView.setText("hello");






//    private long getCurrentPlayerPosition() {
//        return mSimpleExoPlayer.getCurrentPosition();
//    }
//    public static void releaseExoplayer() {
//        //mPlayerCurrentPosition = getCurrentPlayerPosition();
//        mSimpleExoPlayer.setPlayWhenReady(false);
//        mSimpleExoPlayer.release();

//    }
//
//    public static void stopPlayer(){
//        mSimpleExoPlayer.stop();
//        mSimpleExoPlayer.release();
//    }

//    private void resumePlaybackFromPreviousPosition(int prevPosition) {
//        mSimpleExoPlayer.seekTo(mPlayerCurrentPosition );
//    }











//        Bundle bundle = new Bundle();
//        int index = intent.getIntExtra("step", 0);
//        Log.d(TAG, "onCreateView: " + index);
//        bundle.putInt("index1", index);


//
//        mStepItem = intent.getParcelableExtra("step");
//        mInstructions = mStepItem.getDescription();
//        mVideoUrl = mStepItem.getVideoURL();
//
//        mInstructions = mStepItem.getDescription();
//        mVideoUrl = mStepItem.getVideoURL();
//        mInstructionView.setText(mInstructions);
//
//        initExoPlayer();
//        mPlayerView.setPlayer(mSimpleExoPlayer);
//
//
//
//
//
//        mInstructionView.setText(mInstructions);
//
//        initExoPlayer();
//        mPlayerView.setPlayer(mSimpleExoPlayer);

