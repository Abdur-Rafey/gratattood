package com.gratattood.gratattood.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.gratattood.gratattood.Design_Activity;

import java.io.File;

/**
 * Created by Rafey Sheikh on 10/12/2017.
 */

public class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
    String FileName;
    Context context;
    boolean firstTime=true;
    String AudioRetreivePathInDevice=null;
    public CustomOnItemSelectedListener( String FileName,Context context)
    {
        this.FileName=FileName;
        this.context=context;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
       /* Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();*/
       if (firstTime)
       {
   firstTime=false;
           return;
       }
       Log.v("CustomOnItemSLTAG", "Item selected: "+pos);

       if (pos==0)
       {
           Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
           onDeletebtnClicked();
       }
        if (pos==1)
        {
            Intent intent = new Intent(context, Design_Activity.class);
            intent.putExtra("NameofPicture", FileName);
            context.startActivity(intent);
        }
        if (pos==2)
        {
              Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
            onShareBtnClicked();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void onShareBtnClicked(){

        AudioRetreivePathInDevice=  Environment.getExternalStorageDirectory().getAbsolutePath() + "/Gratattood/" + FileName;
        Uri uri = Uri.parse(AudioRetreivePathInDevice);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("audio/*");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(share, "Share Sound File"));


    }
    public void onDeletebtnClicked() {

         String AudioRetreivePathInDevice=  Environment.getExternalStorageDirectory().getAbsolutePath() + "/Gratattood/" + FileName;
        File file = new File(AudioRetreivePathInDevice);
        boolean deleted = file.delete();


    }

}
