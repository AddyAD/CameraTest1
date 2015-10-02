package com.example.hello.cameratest1;

import java.io.File;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Environment;


public class MainActivity extends Activity {
    private Button dissubmit=null;
    private Button submit=null;
    private File message=null;
    private static final String FN = "photo.jpg";
    private static String path="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dissubmit = (Button)super.findViewById(R.id.button);
        submit = (Button)super.findViewById(R.id.button2);
        dissubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ){
                    Toast.makeText(getApplicationContext(),"读取失败,SD存储卡不存在!",Toast.LENGTH_LONG).show();
                    return;
                }
                path = Environment.getExternalStorageState().toString()+File.separator+
                        "hehe"+File.separator+FN;
                message=new File(path);
                /*if(!message.exists()){
                    File vpath=message.getParentFile();
                    vpath.mkdir();
                    Toast.makeText(getApplicationContext(),"文件不存在!!",Toast.LENGTH_LONG).show();
                    return;
                }*/
                Uri uri=Uri.fromFile(message);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(intent,1);


            }
        });
    }



    }
}
