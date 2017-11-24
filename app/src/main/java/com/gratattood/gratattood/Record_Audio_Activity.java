package com.gratattood.gratattood;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gratattood.gratattood.helper.MyMediaRecorder;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.logging.Handler;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Record_Audio_Activity extends AppCompatActivity {
    private static final String TAG = Record_Audio_Activity.class.getSimpleName();
    ImageButton btnPause;
    ImageButton buttonStop;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder ;
    public static final int RequestPermissionCode = 1;
    TextView dration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record__audio_);

        btnPause= (ImageButton) findViewById(R.id.btnPause);
        buttonStop= (ImageButton) findViewById(R.id.btnStop);
        dration= (TextView) findViewById(R.id.duraion);
        final Chronometer myChronometer = (Chronometer)findViewById(R.id.chronometer);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkPermission()) {
                    File Root = Environment.getExternalStorageDirectory();
                    File DirAudio = new File(Root.getAbsolutePath()+"/Gratattood");
                    if (!DirAudio.exists()) {
                        DirAudio.mkdir();
                    }
                    File DirAudioTemp = new File(Root.getAbsolutePath()+"/Gratattood/"+"temp");
                    if (!DirAudioTemp.exists()) {
                        DirAudioTemp.mkdir();
                    }


                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() +"/Gratattood"+"/temp/" +
                                    "Audi0Temp.mp3";

                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mediaRecorder.setOutputFile(AudioSavePathInDevice);
                mediaRecorder.setMaxDuration(60000);
                mediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener(){
                    @Override
                    public void onInfo(MediaRecorder mr, int what, int extra) {

                            if(what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED){
                                myChronometer.stop();
                                mediaRecorder.stop();
                                myChronometer.setBase(SystemClock.elapsedRealtime());
                                mediaRecorder.release();
                                mediaRecorder = null;
                                Intent intent = new Intent(Record_Audio_Activity.this, SaveAudio_Activity.class);
                                startActivity(intent);
                            }

                        }


                    });


                try {
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                    myChronometer.setBase(SystemClock.elapsedRealtime());
                    myChronometer.start();
                                                      } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                Toast.makeText(Record_Audio_Activity.this, "Recording started", Toast.LENGTH_LONG).show();
                }
                else {
                    requestPermission();
                }

            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaRecorder !=null) {

                    mediaRecorder.stop();
                    myChronometer.stop();
                    myChronometer.setBase(SystemClock.elapsedRealtime());
                    mediaRecorder.release();
                    mediaRecorder = null;
                    Intent intent = new Intent(Record_Audio_Activity.this, SaveAudio_Activity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Record_Audio_Activity.this, "Recording is already stopped", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(Record_Audio_Activity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(Record_Audio_Activity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Record_Audio_Activity.this,"Permission  Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

}
