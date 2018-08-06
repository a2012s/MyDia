package wmz.myapplication;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    MyLoadingDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //使用 CommonBaseDialog核心代码
                CommonBaseDialog.showDialog1Button(MainActivity.this)
                        .setTitle("提示")
                        .setContent("确认取消订单?")
                        .setViewListenerButton(new CommonBaseDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, int viewId) {
                                if (viewId == R.id.confirm) {
                                    Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else if (viewId == R.id.cancel) {
                                    dialog.dismiss();
                                }

                            }
                        });
//                        .setViewListener(new CommonBaseDialog.OnCloseListener() {
//                            @Override
//                            public void onClick(Dialog dialog, int viewId) {
//                                switch (viewId) {
//                                    case R.id.confirm:
//                                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
//                                    case R.id.cancel:
//                                        dialog.dismiss();
//                                        break;
//                                }
//                            }
//                        }, R.id.cancel, R.id.confirm);
            }
        });


        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        // .setIcon(R.mipmap.icon)//设置标题的图片
                        .setTitle("我是对话框")//设置对话框的标题
                        .setMessage("我是对话框的内容")//设置对话框的内容
                        //设置对话框的按钮
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();

            }
        });

        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buildProgressDialog();

            }
        });

        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog = new MyLoadingDialog();
                myDialog.show(MainActivity.this);


            }
        });
    }

    /**
     * 系统加载框
     */
    public void buildProgressDialog() {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.BUTTON_NEUTRAL);
        }
        progressDialog.setMessage("加载中");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }


    /**
     * @Description: TODO 取消加载框
     * @author Sunday
     * @date 2015年12月25日
     */
    public void cancelProgressDialog() {
        if (progressDialog != null)
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
    }

    // @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDialog.dismiss();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        }
    }
}