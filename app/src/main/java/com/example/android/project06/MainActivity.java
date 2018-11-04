package com.example.android.project06;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listview;
    // 模拟数据
    private List<String> dataList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataList = new ArrayList<>();
        // 初始化数据
        for (int i = 0; i < 20; i++) {
            dataList.add("第" + i + "条数据");
        }
        // 设置adapter(所在的activity,使用的显示样式,数据源)
        ListAdapter adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, dataList);
        listview = (ListView) findViewById(R.id.ListView);
        listview.setAdapter(adapter);
        // 绑定item点击事件
        listview.setOnItemClickListener(this);

    }


    @Override
    // 这是实现OnItemClickListener接口必须实现的方法，在这里进行item的点击事件的处理，最常用的是position，可以根据position获得点击的item的数据
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast.makeText(MainActivity.this, "点击了第" + position + "条数据",Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_1 :
                Toast.makeText(this, "您选择了: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_2 :
                Toast.makeText(this, "您选择了: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_3 :
                Toast.makeText(this, "您选择了: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_4 :
                sendNotification();
                break;

            case R.id.action_5 :
                openDialog();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void sendNotification(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channeltest = new NotificationChannel(
                "rua",
                "Test Channel",
                NotificationManager.IMPORTANCE_HIGH);
        channeltest.setDescription("测试");
        channeltest.enableLights(true);
        channeltest.enableVibration(true);
        notificationManager.createNotificationChannel(channeltest);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "rua")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        notificationManager.notify(1, mBuilder.build());
    }

    public void openDialog(){
        AlertDialog ad = new AlertDialog.Builder
                (MainActivity.this).create();
        ad.setTitle("Test");
        ad.setMessage("This is a test message");
        ad.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        ad.show();
    }
}
