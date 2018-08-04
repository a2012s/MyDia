package wmz.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Dialog dialog2;
    MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //使用 CommonBaseDialog核心代码
                CommonBaseDialog.showDialog(MainActivity.this, R.layout.common_dialog)
                        .setText(R.id.title, "提示")
                        //.setDialogLocation(Gravity.CENTER, 100, 0, 100, 0)
                        .setText(R.id.content, "确认取消订单?")
                        .setViewListener(new CommonBaseDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, int viewId) {
                                switch (viewId) {
                                    case R.id.confirm:
                                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                                        buildProgressDialog();
                                    case R.id.cancel:
                                        dialog.dismiss();
                                        cancelProgressDialog();
                                        break;
                                }
                            }
                        }, R.id.cancel, R.id.confirm);
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

                myDialog=new MyDialog();
                myDialog.show(MainActivity.this);


            }
        });
    }

    /**
     * 加载框
     */
    public void buildProgressDialog() {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.BUTTON_NEUTRAL);
        }
        progressDialog.setMessage("加载中");
        // progressDialog.setIcon(R.mipmap.ic_launcher);


        progressDialog.setCancelable(true);
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


}