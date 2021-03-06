package com.zkb.mapserver.util;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

/**
 * 语音播报组件
 */
public class TTSController {

    private Context mContext;
    private static TTSController ttsManager;
    private SpeechSynthesizer mTts;

    /**
     * 初始化监听。
     */
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d("SHIXIN", "InitListener init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(mContext, "初始化失败,错误码：" + code, Toast.LENGTH_SHORT).show();
                ;
            } else {
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };

    private TTSController(Context context) {
        mContext = context.getApplicationContext();
    }

    public static TTSController getInstance(Context context) {
        if (ttsManager == null) {
            ttsManager = new TTSController(context);
        }
        return ttsManager;
    }

    public void init() {
       String text = "586e3c8c";


        SpeechUtility.createUtility(mContext, "appid=" + text);

        // 初始化合成对象.
        mTts = SpeechSynthesizer.createSynthesizer(mContext, mTtsInitListener);
        initSpeechSynthesizer();
    }

    /**
     * 使用SpeechSynthesizer合成语音，不弹出合成Dialog.
     *
     * @param
     */
    public void startSpeaking(String playText) {
        // 进行语音合成.
        if (mTts != null)
            mTts.startSpeaking(playText, new SynthesizerListener() {

                @Override
                public void onSpeakResumed() {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onSpeakProgress(int arg0, int arg1, int arg2) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onSpeakPaused() {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onSpeakBegin() {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onCompleted(SpeechError arg0) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onBufferProgress(int arg0, int arg1, int arg2, String arg3) {
                    // TODO Auto-generated method stub

                }
            });

    }

    public void stopSpeaking() {
        if (mTts != null)
            mTts.stopSpeaking();
    }

    private void initSpeechSynthesizer() {
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置在线合成发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "50");
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.wav");

    }

    public void destroy() {
        if (mTts != null) {
            mTts.stopSpeaking();
            mTts.destroy();
            ttsManager=null;
        }
    }


}
