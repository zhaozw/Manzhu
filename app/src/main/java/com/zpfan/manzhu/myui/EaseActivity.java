package com.zpfan.manzhu.myui;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.zpfan.manzhu.R;
import com.zpfan.manzhu.fragment.EaseChatFragment;

import java.util.ArrayList;

public class EaseActivity extends AppCompatActivity {

    private static final int REQUEST_AUDIO = 1;
    private static final int REQUEST_STORAGE = 2;
    private static final int REQUEST_CAMERA = 3;
    private static final int REQUEST_ALL = 4;
    private EaseChatFragment chatFragment;
    private ArrayList<String> permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ease);
        permissions = new ArrayList<>();
        requewstPermission();
        // 这里直接使用EaseUI封装好的聊天界面
        chatFragment = new EaseChatFragment();
        // 将参数传递给聊天界面
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.ed_content, chatFragment).commit();

    }

    private void requewstPermission() {
      /*  //动态申请权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED  ) {

           ActivityCompat.requestPermissions(EaseActivity.this,new String[]{Manifest.permission.RECORD_AUDIO},REQUEST_AUDIO);
            permissions.add(Manifest.permission.RECORD_AUDIO);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED  ) {

           ActivityCompat.requestPermissions(EaseActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_AUDIO);
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED  ) {

            ActivityCompat.requestPermissions(EaseActivity.this,new String[]{Manifest.permission.CAMERA},REQUEST_AUDIO);
            permissions.add(Manifest.permission.CAMERA);
        }*/










    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_AUDIO) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {



            } else {



            }
        }

        if (requestCode == REQUEST_ALL) {

            if (grantResults.length == 3 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {



            } else {
                for (int i = 0; i < grantResults.length; i++) {


                }

            }

        }


    }
}
