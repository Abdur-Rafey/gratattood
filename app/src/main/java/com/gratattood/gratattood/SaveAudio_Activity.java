package com.gratattood.gratattood;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.gratattood.gratattood.app.AppController;
import com.gratattood.gratattood.helper.MyMediaRecorder;

import java.io.File;
import java.io.IOException;

public class SaveAudio_Activity extends AppCompatActivity {
    CardView card;
    MediaPlayer mediaPlayer;
    private ProgressBar pDialog;
    EditText etxtAudioName;
    TextView txtAudioQuality, txtAudioProperties;
    ImageView btnSave, btnPlayAudio, imgWave;
    String AudioSavePathInDevice = null;
    String AudioRetreivePathInDevice = null;
    String nameofFile;
    ObjectAnimator anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_audio_);
        btnSave = (ImageView) findViewById(R.id.btnSaveAudio);
        btnPlayAudio = (ImageView) findViewById(R.id.btnPlayAudio);
        imgWave = (ImageView) findViewById(R.id.imageWave);
        mediaPlayer = new MediaPlayer();

        pDialog = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        anim = ObjectAnimator.ofInt(pDialog, "progress", 0, 100);
        anim.setDuration(10000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

        etxtAudioName = (EditText) findViewById(R.id.etxtAudioName);
        txtAudioQuality = (TextView) findViewById(R.id.txtAudioQuality);
        txtAudioProperties = (TextView) findViewById(R.id.txtAudioProperties);

        card = (CardView) findViewById(R.id.card_view1);
        card.setCardBackgroundColor(Color.parseColor("#f1b10e"));
        getBitmapFromURL();
        pDialog.clearAnimation();
        AudioRetreivePathInDevice =
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Gratattood/" + "temp/" +
                        "Audi0Temp.mp3";

        File file = new File(AudioRetreivePathInDevice);
        Long length = file.length();
        length = length / 1024;
        String strLong = Long.toString(length);

        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(AudioRetreivePathInDevice);

        String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long dur = Long.parseLong(duration);
        String seconds = String.valueOf((dur % 60000) / 1000);

        String frequency = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);

        mmr.release();
        txtAudioQuality.setText("WAV," + frequency + "Hz");
        txtAudioProperties.setText(strLong + "kb," + seconds + "sec");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (AudioRetreivePathInDevice != null) {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(AudioRetreivePathInDevice);
                mediaPlayer.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onPlaybtnClicked(View view) {
        // pDialog.setVisibility(View.VISIBLE);

//        Log.d("Save Activity", "profressbar is running  " + anim.isStarted());
//        Log.d("Save Activity", "profressbar is running  " + anim.isRunning());

        boolean b = mediaPlayer.isPlaying();
        // Log.d("SaveActivity", "bolean value: " + b);
        if (b) {
            mediaPlayer.stop();
            Toast.makeText(SaveAudio_Activity.this, "Recording Stop",
                    Toast.LENGTH_SHORT).show();
        } else {

            try {

                mediaPlayer.release();
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(AudioRetreivePathInDevice);
                mediaPlayer.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.start();
            Toast.makeText(SaveAudio_Activity.this, "Recording Playing",
                    Toast.LENGTH_SHORT).show();
        }
        // pDialog.setVisibility(View.GONE);


    }

    public void onSaveBtnClicked(View view) {
        nameofFile = etxtAudioName.getText().toString();
        if (nameofFile.isEmpty()) {
            Toast.makeText(SaveAudio_Activity.this, "Name is required to save the audio",
                    Toast.LENGTH_SHORT).show();

        } else {
            AudioSavePathInDevice =
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/Gratattood/" + nameofFile + ".mp3";

            File from = new File(AudioRetreivePathInDevice);
            File to = new File(AudioSavePathInDevice);
            from.renameTo(to);

            Toast.makeText(SaveAudio_Activity.this, "Recording Save In Gratattood",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SaveAudio_Activity.this, LocalAudioGallery_Activity.class);
            startActivity(intent);
        }

    }

    public void onShareBtnClicked(View view) {
        Uri uri = Uri.parse(AudioRetreivePathInDevice);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("audio/*");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share Sound File"));
    }

    public void onDeletebtnClicked(View view) {

        if (AudioSavePathInDevice == null) {

            deleteDirectory(AudioRetreivePathInDevice);
            Toast.makeText(SaveAudio_Activity.this, "recording Deleted ",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SaveAudio_Activity.this, Record_Audio_Activity.class);
            startActivity(intent);

        } else {

            deleteDirectory(AudioSavePathInDevice);
            Toast.makeText(SaveAudio_Activity.this, "recording Deleted Save Path ",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SaveAudio_Activity.this, Record_Audio_Activity.class);
            startActivity(intent);
        }
    }

    public static boolean deleteDirectory(String path) {
        File file = new File(path);
        boolean deleted = file.delete();
        return deleted;
    }

    public void getBitmapFromURL() {
        pDialog.setVisibility(View.VISIBLE);
        String src = "https://www.google.com.pk/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
        ImageRequest ir = new ImageRequest(src,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        pDialog.setVisibility(View.GONE);
                        imgWave.setVisibility(View.VISIBLE);
                        imgWave.setImageBitmap(response);

                    }
                }, 0, 0, null, null);
        AppController.getInstance().addToRequestQueue(ir, "test_image_req");
        Log.d("Save Activity", "progress  " + pDialog.getProgress());

        // pDialog.setVisibility(View.GONE);
    }
}
