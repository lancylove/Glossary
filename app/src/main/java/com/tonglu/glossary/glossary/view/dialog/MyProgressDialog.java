package com.tonglu.glossary.glossary.view.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tonglu on 2016/3/11.
 */
public class MyProgressDialog extends ProgressDialog {

    public static final String TAG = "ProgressDialog";
    private long mTimeOut = 0;// 默认timeOut为0即无限大
    private OnTimeOutListener mTimeOutListener = null;// timeOut后的处理器
    private Timer mTimer = null;// 定时器
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            if (mTimeOutListener != null) {
                mTimeOutListener.onTimeOut(MyProgressDialog.this);
                dismiss();
            }
        }
    };

    public MyProgressDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    /**
     * 设置timeOut长度，和处理器
     *
     * @param t               timeout时间长度
     * @param timeOutListener 超时后的处理器
     */
    public void setTimeOut(long t, OnTimeOutListener timeOutListener) {
        mTimeOut = t;
        if (timeOutListener != null) {
            this.mTimeOutListener = timeOutListener;
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        if (mTimeOut != 0) {
            mTimer = new Timer();
            TimerTask timerTast = new TimerTask() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    //    dismiss();
                    Message msg = mHandler.obtainMessage();
                    mHandler.sendMessage(msg);
                }
            };
            mTimer.schedule(timerTast, mTimeOut);
        }

    }

    /**
     * 通过静态Create的方式创建一个实例对象
     *
     * @param context
     * @param time     timeout时间长度
     * @param listener timeOutListener 超时后的处理器
     * @return MyProgressDialog 对象
     */
    public static MyProgressDialog createProgressDialog(Context context,
                                                        long time, OnTimeOutListener listener) {
        MyProgressDialog progressDialog = new MyProgressDialog(context);
        if (time != 0) {
            progressDialog.setTimeOut(time, listener);
        }
        return progressDialog;
    }

    /**
     * 处理超时的的接口
     */
    public interface OnTimeOutListener {

        /**
         * 当progressDialog超时时调用此方法
         */
        abstract public void onTimeOut(ProgressDialog dialog);
    }

}