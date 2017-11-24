package com.gratattood.gratattood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gratattood.gratattood.adapter.MyAudioGalleryListAdapter;
import com.gratattood.gratattood.model.Model_Audio_Gallery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class LocalAudioGallery_Activity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    //  private Spinner spinner1;
    ListView yourListView;
    ArrayList<Model_Audio_Gallery> lstArratList;
    private String AudioRetreivePathInDevice, nameofFile;
    MediaPlayer mediaPlayer;
    TextView txtaudioStart, txtAudioEnd, txtTattooBtn;
    ImageView btnPlayPause, btnFarword, btnBackward;

    SeekBar songProgressBar;
    private int seekForwardTime = 5000; // 5000 milliseconds
    private int seekBackwardTime = 5000; // 5000 milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_audio_gallery_);

        yourListView = (ListView) findViewById(R.id.lst_local_audio_Activity);
        txtAudioEnd = (TextView) findViewById(R.id.txtdurationEnd);
        txtaudioStart = (TextView) findViewById(R.id.txtdurationStart);
        txtTattooBtn = (TextView) findViewById(R.id.txtTattooBtn);
        btnPlayPause = (ImageView) findViewById(R.id.btnPlayPause);
        btnBackward = (ImageView) findViewById(R.id.btnBackward);
        btnFarword = (ImageView) findViewById(R.id.btnFarword);
        songProgressBar = (SeekBar) findViewById(R.id.seekbar);

        songProgressBar.setOnSeekBarChangeListener(this); // Important
        AudioRetreivePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Gratattood/";
        mediaPlayer = new MediaPlayer();
        MyAudioGalleryListAdapter customAdapter = new MyAudioGalleryListAdapter(this, R.layout.audio_gallery_row, getItemList());

        yourListView.setAdapter(customAdapter);
        yourListView.setFastScrollEnabled(true);
        yourListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                getItemList2();
                nameofFile = getItemList2().get(position).getName();
                onAudiobtnClicked(nameofFile);


            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
        songProgressBar.removeCallbacks(mUpdateTimeTask);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (AudioRetreivePathInDevice + nameofFile != null) {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(AudioRetreivePathInDevice + nameofFile);
                mediaPlayer.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

     /*   MyAudioGalleryListAdapter customAdapter = new MyAudioGalleryListAdapter(this, R.layout.audio_gallery_row, getItemList());

        yourListView .setAdapter(customAdapter);
        yourListView.setFastScrollEnabled(true);
        yourListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                getItemList2();
                nameofFile =     getItemList2().get(position).getName();
                onAudiobtnClicked(nameofFile);


            }
        });*/

    }

    public ArrayList<Model_Audio_Gallery> getItemList() {
        String name = new String(), strLong, duration, seconds;
        Long length, dur, lastmodified;
        ArrayList<Model_Audio_Gallery> lst = new ArrayList<Model_Audio_Gallery>();
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();

        File sdCardRoot = Environment.getExternalStorageDirectory();
        File yourDir = new File(sdCardRoot, "/Gratattood/");

        for (File f : yourDir.listFiles()) {
            if (f.isFile()) {
                name = f.getName();
                length = f.length();
                length = length / 1024;
                strLong = Long.toString(length);
                //  lastmodified = f.lastModified();
                Date lastModDate = new Date(f.lastModified());
                Log.d("Audio Gallery Activity", "path  " + AudioRetreivePathInDevice + name);
                mmr.setDataSource(AudioRetreivePathInDevice + name);

                duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                String creat = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE);
                dur = Long.parseLong(duration);
              /* // seconds = String.valueOf((dur % 60000) / 1000);
                // Do your stuff
                Log.d("Audio Gallery Activity", "nmae  " + name);
                Log.d("Audio Gallery Activity", "creat  " + lastModDate);
                Log.d("Audio Gallery Activity", "duraton  " + dur);
                Log.d("Audio Gallery Activity", "lenth  " + strLong);*/
                Model_Audio_Gallery model_audio_galleries = new Model_Audio_Gallery();

                model_audio_galleries.setName(name);
                model_audio_galleries.setDetail(lastModDate.toString());
                model_audio_galleries.setDuration("" + milliSecondsToTimer(dur));
                model_audio_galleries.setSize(strLong + "KB");


                lst.add(model_audio_galleries);
            }
        }
        return lst;
    }

    public ArrayList<Model_Audio_Gallery> getItemList2() {
        String name = new String(), strLong, duration, seconds;
        Long length, dur, lastmodified;
        lstArratList = new ArrayList<Model_Audio_Gallery>();
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();

        File sdCardRoot = Environment.getExternalStorageDirectory();
        File yourDir = new File(sdCardRoot, "/Gratattood/");

        for (File f : yourDir.listFiles()) {
            if (f.isFile()) {
                name = f.getName();
                length = f.length();
                length = length / 1024;
                strLong = Long.toString(length);
                //  lastmodified = f.lastModified();
                Date lastModDate = new Date(f.lastModified());
                Log.d("Audio Gallery Activity", "path  " + AudioRetreivePathInDevice + name);
                mmr.setDataSource(AudioRetreivePathInDevice + name);

                duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                String creat = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE);
                dur = Long.parseLong(duration);
//                seconds = String.valueOf((dur % 60000) / 1000);

                Model_Audio_Gallery model_audio_galleries = new Model_Audio_Gallery();

                model_audio_galleries.setName(name);
                model_audio_galleries.setDetail(lastModDate.toString());
                model_audio_galleries.setDuration("" + milliSecondsToTimer(dur));

                model_audio_galleries.setSize(strLong);

                lstArratList.add(model_audio_galleries);

            }
        }
        return lstArratList;
    }

    public void onAudiobtnClicked(String PathInDevice) {


        String path = AudioRetreivePathInDevice + PathInDevice;

        try {

            mediaPlayer.release();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
        Bitmap icon = BitmapFactory.decodeResource(LocalAudioGallery_Activity.this.getResources(),
                R.drawable.pause_white);
        btnPlayPause.setImageBitmap(icon);
        // Updating progress bar
        updateProgressBar();
        Toast.makeText(LocalAudioGallery_Activity.this, "Recording Playing",
                Toast.LENGTH_SHORT).show();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onBackwardBtnClicked(View view) {

// get current song position
        int currentPosition = mediaPlayer.getCurrentPosition();
        // check if seekBackward time is greater than 0 sec
        if (currentPosition - seekBackwardTime >= 0) {
            // forward song
            mediaPlayer.seekTo(currentPosition - seekBackwardTime);
        } else {
            // backward to starting position
            mediaPlayer.seekTo(0);
        }
    }

    public void onFarwardBtnClicked(View view) {

// get current song position
        int currentPosition = mediaPlayer.getCurrentPosition();
        // check if seekForward time is lesser than song duration
        if (currentPosition + seekForwardTime <= mediaPlayer.getDuration()) {
            // forward song
            mediaPlayer.seekTo(currentPosition + seekForwardTime);
        } else {
            // forward to end position
            mediaPlayer.seekTo(mediaPlayer.getDuration());
        }
    }

    public void onTattooBtnClicked(View view) {

        if (nameofFile == null) {
            Toast.makeText(LocalAudioGallery_Activity.this, "Select Audio to Create Tattoo",
                    Toast.LENGTH_SHORT).show();
        } else {

            Intent intent = new Intent(LocalAudioGallery_Activity.this, Design_Activity.class);
            intent.putExtra("NameofPicture", nameofFile);
            startActivity(intent);
        }
    }

    public void onPlaybtnClicked(View view) {


        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            Bitmap icon = BitmapFactory.decodeResource(LocalAudioGallery_Activity.this.getResources(),
                    R.drawable.play_white);
            btnPlayPause.setImageBitmap(icon);

            Toast.makeText(LocalAudioGallery_Activity.this, "Recording Stop",
                    Toast.LENGTH_SHORT).show();
        } else {

            try {

                mediaPlayer.release();
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(AudioRetreivePathInDevice + nameofFile);
                mediaPlayer.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.start();
            Bitmap icon = BitmapFactory.decodeResource(LocalAudioGallery_Activity.this.getResources(),
                    R.drawable.pause_white);
            btnPlayPause.setImageBitmap(icon);
//            // set Progress bar values
//            songProgressBar.setProgress(0);
//            songProgressBar.setMax(100);
//
//            // Updating progress bar
//            updateProgressBar();
            Toast.makeText(LocalAudioGallery_Activity.this, "Recording Playing",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void updateProgressBar() {
        // mHandler.postDelayed(mUpdateTimeTask, 100);
        songProgressBar.postDelayed(mUpdateTimeTask, 100);
    }

    //Background Runnable thread
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mediaPlayer.getDuration();
            long currentDuration = mediaPlayer.getCurrentPosition();

            // Displaying Total Duration time
            txtAudioEnd.setText("" + milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
            txtaudioStart.setText("" + milliSecondsToTimer(currentDuration));

            // Updating progress bar
            int progress = (int) (getProgressPercentage(currentDuration, totalDuration));
            //Log.d("Progress", ""+progress);
            songProgressBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            // mHandler.postDelayed(this, 100);


            songProgressBar.postDelayed(this, 100);
            /*if (currentDuration==currentDuration)
            {
                songProgressBar.removeCallbacks(this);
            }*/

        }
    };

    /**
     *
     * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }

    /**
     * When user starts moving the progress handler
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // remove message Handler from updating progress bar
        // mHandler.removeCallbacks(mUpdateTimeTask);
        songProgressBar.removeCallbacks(mUpdateTimeTask);
    }

    /**
     * When user stops moving the progress hanlder
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        songProgressBar.removeCallbacks(mUpdateTimeTask);
        // mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mediaPlayer.getDuration();
        int currentPosition = progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mediaPlayer.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();
    }

    ////////////////////////////////////Utilities///////////////////////////////
    public String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    /**
     * Function to get Progress percentage
     *
     * @param currentDuration
     * @param totalDuration
     */
    public int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        // return percentage
        return percentage.intValue();
    }

    /**
     * Function to change progress to timer
     *
     * @param progress      -
     * @param totalDuration returns current duration in milliseconds
     */
    public int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double) progress) / 100) * totalDuration);

        // return current duration in milliseconds
        return currentDuration * 1000;
    }

}
