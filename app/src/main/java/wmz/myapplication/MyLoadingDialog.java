package wmz.myapplication;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wjj on 2018/8/4 11:44
 * E-Mail ：wjj99@qq.com
 * 描述：自定义加载框
 */

public class MyLoadingDialog {


    Dialog dialog;

    private Animation anim;
    private ImageView spaceshipImage;


    /**
     * 自定义的progressDialog
     *
     * @param context 上下文
     * @param msg     加载数据时显示的信息
     * @return Dialog
     */
    @SuppressWarnings("deprecation")
    public Dialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        //加载loading_dialog.xml
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view

        // loading_dialog.xml中的LinearLayout
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局

        // loading_dialog.xml中的TextView
        TextView tipTextView = (TextView) v.findViewById(R.id.loading_tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息（如：登录中，请稍候...）

        // loading_dialog.xml中的ImageView
        spaceshipImage = (ImageView) v.findViewById(R.id.iv_loading_img);
        // 加载动画load_animation.xml
        anim = AnimationUtils.loadAnimation(context, R.anim.load_animation);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(anim);


        // 创建自定义样式loading_dialog
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        loadingDialog.setCanceledOnTouchOutside(false);//点击屏幕，不会消失
        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        // 设置布局
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        return loadingDialog;

    }


    public void startAnim() {
        if (spaceshipImage != null && anim != null) {
            spaceshipImage.startAnimation(anim);
        }
    }

    /**
     * 显示加载框
     *
     * @param context
     */
    public void show(Context context) {
        if (dialog == null) {
            dialog = createLoadingDialog(context, "加载中……");
        } else {
            startAnim();
        }
        dialog.show();
    }


    /**
     * 隐藏加载框
     */
    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

    }

    /**
     * 清除
     */
    public void clear() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }
    }


}