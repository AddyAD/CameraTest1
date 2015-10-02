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

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private Button dissubmit=null;
    private Button submit=null;
    private File message=null;
    private static final String FN = "photo.jpg";
    private static String path="";

    private  ImageView img = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dissubmit = (Button)super.findViewById(R.id.button);
        submit = (Button)super.findViewById(R.id.button2);
        img = (ImageView)findViewById(R.id.imageView);
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
                path = Environment.getExternalStorageDirectory().toString()+File.separator+
                        "heheda"+File.separator+FN;
                message=new File(path);

                if(!message.exists()){
                    if(!message.getParentFile().exists()){
                        message.getParentFile().mkdirs();
                    }
                }
                /*if(!message.exists()){
                    File vpath=message.getParentFile();
                    vpath.mkdir();
                    Toast.makeText(getApplicationContext(),path+"文件不存在!!",Toast.LENGTH_LONG).show();
                    return;
                }*/
                Uri uri=Uri.fromFile(message);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(intent,1);


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == 1 && resultCode == RESULT_OK){
            Bitmap aa = BitmapFactory.decodeFile(path);
            Toast.makeText(getApplicationContext(), path+"", Toast.LENGTH_SHORT).show();
            img.setImageBitmap(aa);
        }


    }
}
