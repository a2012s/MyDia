package wmz.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 常用的Dialog
 * 标题，内容，确定和取消
 */

public class CommonBaseDialog extends Dialog implements View.OnClickListener {
    private int itemLayoutId;
    private OnCloseListener mListener;
    private boolean mIsDismiss = false;

    private int confirmId;//确定
    private int cancelId;//取消

    public int getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(int confirmId) {
        this.confirmId = confirmId;
    }

    public int getCancelId() {
        return cancelId;
    }

    public void setCancelId(int cancelId) {
        this.cancelId = cancelId;
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, int viewId);
    }

    private CommonBaseDialog(Context context, int theme, int itemLayoutId) {
        super(context, theme);
        this.itemLayoutId = itemLayoutId;
    }

    public static CommonBaseDialog showDialog(Context mContext, int itemLayoutId) {
        CommonBaseDialog dialog = new CommonBaseDialog(mContext, R.style.dialog, itemLayoutId);
        dialog.show();
        return dialog;
    }


    /**
     * 显示两个按钮（确定和取消）
     *
     * @param mContext
     * @return
     */
    public static CommonBaseDialog showDialog2Button(Context mContext) {
        return showDialog(mContext, R.layout.common_dialog);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(itemLayoutId);
        setCanceledOnTouchOutside(mIsDismiss);
    }

    public CommonBaseDialog setDialogLocation(int gravity, int left, int top, int right, int bottom) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = gravity;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(left, top, right, bottom);
        getWindow().setAttributes(layoutParams);
        return this;
    }

    /**
     * 设置点击Dialog其他位置是否消失 默认false
     */
    public CommonBaseDialog setOnTouchOutside(boolean isDismiss) {
        this.mIsDismiss = isDismiss;
        setCanceledOnTouchOutside(isDismiss);
        return this;
    }

    /**
     * 设置点击事件
     *
     * @param viewIds 需要设置的点击事件控件id
     */
    public CommonBaseDialog setViewListener(OnCloseListener listener, int... viewIds) {
        this.mListener = listener;
        for (int viewId : viewIds) {
            findViewById(viewId).setOnClickListener(this);
        }
        return this;
    }

    public CommonBaseDialog setViewListener2Button(OnCloseListener listener) {
        this.mListener = listener;
        confirmId = R.id.confirm;
        cancelId = R.id.cancel;
        setCancelId(R.id.confirm);
        setConfirmId(confirmId);
        findViewById(confirmId).setOnClickListener(this);
        findViewById(cancelId).setOnClickListener(this);
        return this;
    }


    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onClick(this, v.getId());
        }
        this.dismiss();
    }

    public <T extends View> T getView(int viewId) {
        return (T) findViewById(viewId);
    }


    public CommonBaseDialog setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }


    /**
     * 设置标题title
     *
     * @param text
     * @return
     */
    public CommonBaseDialog setTitle(String text) {
        return setText(R.id.title, text);
    }

    /**
     * 设置内容
     *
     * @param text
     * @return
     */
    public CommonBaseDialog setContent(String text) {
        return setText(R.id.content, text);
    }


}