package com.hyphenate.easeui.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.ui.FlikerProgressBar;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.RecordUtil;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.PathUtil;

import java.io.File;



/**
 * primary menu
 *
 */
public class EaseChatPrimaryMenu extends EaseChatPrimaryMenuBase implements OnClickListener {
    private EditText editText;
    private View buttonSetModeKeyboard;
    private RelativeLayout edittext_layout;
    private View buttonSetModeVoice;
    private View buttonSend;
    private View buttonPressToSpeak;
    private ImageView faceNormal;
    private ImageView faceChecked;
    private Button buttonMore;
    private boolean ctrlPress = false;
    private EditText mEdMessage;
    private ImageView mIvBiaoqing;
    private ImageView mIvYuyin;
    private ImageView mIvTupian;
    private ImageView mIvZhaoXiang;
    private Button mSend;
    private File cameraFile;
    protected static final int REQUEST_CODE_CAMERA = 2;
    private LinearLayout mLlLuyin;
    private ImageView mIvluyin;
    private boolean isluyin = false;
    private RecordUtil mUtil;
    private String mPath;
    private LinearLayout mLuyin;
    private FlikerProgressBar mLuyinpro;

    public EaseChatPrimaryMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public EaseChatPrimaryMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EaseChatPrimaryMenu(Context context) {
        super(context);
        init(context, null);
    }

    private void init(final Context context, AttributeSet attrs) {
        Context context1 = context;
        LayoutInflater.from(context).inflate(R.layout.ease_widget_chat_primary_menu, this);
        editText = (EditText) findViewById(R.id.et_sendmessage);
        buttonSetModeKeyboard = findViewById(R.id.btn_set_mode_keyboard);
        edittext_layout = (RelativeLayout) findViewById(R.id.edittext_layout);
        buttonSetModeVoice = findViewById(R.id.btn_set_mode_voice);
        buttonSend = findViewById(R.id.btn_send);
        buttonPressToSpeak = findViewById(R.id.btn_press_to_speak);
        faceNormal = (ImageView) findViewById(R.id.iv_face_normal);
        faceChecked = (ImageView) findViewById(R.id.iv_face_checked);
        RelativeLayout faceLayout = (RelativeLayout) findViewById(R.id.rl_face);
        buttonMore = (Button) findViewById(R.id.btn_more);
        edittext_layout.setBackgroundResource(R.drawable.ease_input_bar_bg_normal);

        mEdMessage = (EditText) findViewById(R.id.ed_message);   //我自己的输入框
        mIvBiaoqing = (ImageView) findViewById(R.id.iv_biaoqing);  //我自己的表情按钮
        mIvYuyin = (ImageView) findViewById(R.id.iv_yuyin);   //我自己的语音
        mIvTupian = (ImageView) findViewById(R.id.iv_tupian);  //我自己的图片
        mIvZhaoXiang = (ImageView) findViewById(R.id.iv_zhaoxiang); //我自己的照相
        mSend = (Button) findViewById(R.id.bt_send_text);  //我的发送
        mLlLuyin = (LinearLayout) findViewById(R.id.ll_luyin); //我的录音
        mIvluyin = (ImageView) findViewById(R.id.iv_lu);   //我的录音的按钮
        mLuyin = (LinearLayout) findViewById(R.id.ll_luyin);
        //mLuyinpro = (FlikerProgressBar) findViewById(R.id.luyinpro);

        buttonSend.setOnClickListener(this);
        buttonSetModeKeyboard.setOnClickListener(this);
        buttonSetModeVoice.setOnClickListener(this);
        buttonMore.setOnClickListener(this);
        faceLayout.setOnClickListener(this);
        editText.setOnClickListener(this);


        //我自己的点击事件的监听
        mEdMessage.setOnClickListener(this);
        mIvBiaoqing.setOnClickListener(this);
        mIvYuyin.setOnClickListener(this);
        mSend.setOnClickListener(this);




        editText.requestFocus();
        
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edittext_layout.setBackgroundResource(R.drawable.ease_input_bar_bg_active);
                } else {
                    edittext_layout.setBackgroundResource(R.drawable.ease_input_bar_bg_normal);
                }

            }
        });
        // listen the text change
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    buttonMore.setVisibility(View.GONE);
                    buttonSend.setVisibility(View.VISIBLE);
                } else {
                    buttonMore.setVisibility(View.VISIBLE);
                    buttonSend.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                EMLog.d("key", "keyCode:" + keyCode + " action:" + event.getAction());

                // test on Mac virtual machine: ctrl map to KEYCODE_UNKNOWN
                if (keyCode == KeyEvent.KEYCODE_UNKNOWN) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        ctrlPress = true;
                    } else if (event.getAction() == KeyEvent.ACTION_UP) {
                        ctrlPress = false;
                    }
                }
                return false;
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                EMLog.d("key", "keyCode:" + event.getKeyCode() + " action" + event.getAction() + " ctrl:" + ctrlPress);
                if (actionId == EditorInfo.IME_ACTION_SEND ||
                        (event.getKeyCode() == KeyEvent.KEYCODE_ENTER &&
                         event.getAction() == KeyEvent.ACTION_DOWN &&
                         ctrlPress == true)) {
                    String s = editText.getText().toString();
                    editText.setText("");
                    listener.onSendBtnClicked(s);
                    return true;
                }
                else{
                    return false;
                }
            }
        });

        
        buttonPressToSpeak.setOnTouchListener(new OnTouchListener() {
            
            @Override 
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        if(listener != null){
                            return listener.onPressToSpeakBtnTouch(v, event);
                        }
                            mLlLuyin.setVisibility(GONE);
                        break;
                    case MotionEvent.ACTION_UP:


                        break;
                }

                return false;
            }
        });

       /* mIvYuyin.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(listener != null){
                    return listener.onPressToSpeakBtnTouch(view, event);
                }
                return false;
            }
        });*/





       /* mIvYuyin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("gai", "onClick:    点击了自己的语音按钮");
                //弹出一个window 开始录音


                if (!isluyin) {
                    //打开录音
                    mLlLuyin.setVisibility(VISIBLE);

                } else {
                    mLlLuyin.setVisibility(GONE);
                }

                isluyin = !isluyin;

            }
        });*/





    }





    
    /**
     * set recorder view when speak icon is touched
     * @param voiceRecorderView
     */
    public void setPressToSpeakRecorderView(EaseVoiceRecorderView voiceRecorderView){
        EaseVoiceRecorderView voiceRecorderView1 = voiceRecorderView;
    }

    /**
     * append emoji icon to editText
     * @param emojiContent
     */
    public void onEmojiconInputEvent(CharSequence emojiContent){
        editText.append(emojiContent);
        mEdMessage.append(emojiContent);
    }
    
    /**
     * delete emojicon
     */
    public void onEmojiconDeleteEvent(){
        if (!TextUtils.isEmpty(editText.getText())) {
            KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
            mEdMessage.dispatchKeyEvent(event);
        }
    }
    
    /**
     * on clicked event
     * @param view
     */
    @Override
    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.btn_send) {
            //执行具体的发送功能
            Log.i("gai", "onClick:     点击了发送的按钮 ");
            if(listener != null){
                String s = editText.getText().toString();
                editText.setText("");
                listener.onSendBtnClicked(s);
            }
        } else if (id == R.id.btn_set_mode_voice) {
            Log.i("gai", "onClick:     点击了语音的按钮 ");
            setModeVoice();
            showNormalFaceImage();
            if(listener != null)
                listener.onToggleVoiceBtnClicked();
        } else if (id == R.id.btn_set_mode_keyboard) {
            Log.i("gai", "onClick:     点击了键盘的按钮 ");
            setModeKeyboard();
            showNormalFaceImage();
            if(listener != null)
                listener.onToggleVoiceBtnClicked();
        } else if (id == R.id.btn_more) {
            Log.i("gai", "onClick:   点击了 加号的按钮");
            buttonSetModeVoice.setVisibility(View.VISIBLE);
            buttonSetModeKeyboard.setVisibility(View.GONE);
            edittext_layout.setVisibility(View.VISIBLE);
            buttonPressToSpeak.setVisibility(View.GONE);
            showNormalFaceImage();
            if(listener != null)
                listener.onToggleExtendClicked();
        } else if (id == R.id.et_sendmessage) {
            Log.i("gai", "onClick:      点击了 发送信息的按钮");
            edittext_layout.setBackgroundResource(R.drawable.ease_input_bar_bg_active);
            faceNormal.setVisibility(View.VISIBLE);
            faceChecked.setVisibility(View.INVISIBLE);
            if(listener != null)
                listener.onEditTextClicked();
        } else if (id == R.id.rl_face) {
            //表情按钮 的功能
            Log.i("gai", "onClick:   点击了 表情的按钮");
            toggleFaceImage();
            if(listener != null){
                listener.onToggleEmojiconClicked();
            }
        } else if (id == R.id.ed_message) {

            Log.i("gai", "onClick:      点击了 自己的发送信息的按钮");
            edittext_layout.setBackgroundResource(R.drawable.ease_input_bar_bg_active);
            faceNormal.setVisibility(View.VISIBLE);
            faceChecked.setVisibility(View.INVISIBLE);
            if(listener != null) listener.onEditTextClicked();

        } else if (id == R.id.bt_send_text) {
            //点击了发送的按钮
            Log.i("emoji", "onClick:    点击了自己的发送按钮");

                String s = mEdMessage.getText().toString();
            if(listener != null && !s.equals("")){
                mEdMessage.setText("");
                listener.onSendBtnClicked(s);
            }


        } else if (id == R.id.iv_biaoqing) {
            //点击了 自己的表情
            /*toggleFaceImage();
            if(listener != null){
                listener.onToggleEmojiconClicked();
            }*/

        }  else if (id == R.id.iv_zhaoxiang){
            Log.i("gai", "onClick:    点击了自己的照相");
            selectPicFromCamera();
            if(listener != null)
                listener.onToggleVoiceBtnClicked();
        }





    }

    /**
     * capture new image
     */
    protected void selectPicFromCamera() {
        if (!EaseCommonUtils.isSdcardExist()) {
            Toast.makeText(getContext(), R.string.sd_card_does_not_exist, Toast.LENGTH_SHORT).show();
            return;
        }

        cameraFile = new File(PathUtil.getInstance().getImagePath(), EMClient.getInstance().getCurrentUser()
                + System.currentTimeMillis() + ".jpg");
        //noinspection ResultOfMethodCallIgnored
        cameraFile.getParentFile().mkdirs();
        startActivityForResult(
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile)),
                REQUEST_CODE_CAMERA);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }
    
    
    /**
     * show voice icon when speak bar is touched
     * 
     */
    protected void setModeVoice() {
        hideKeyboard();
        edittext_layout.setVisibility(View.GONE);
        buttonSetModeVoice.setVisibility(View.GONE);
        buttonSetModeKeyboard.setVisibility(View.VISIBLE);
        buttonSend.setVisibility(View.GONE);
        buttonMore.setVisibility(View.VISIBLE);
        buttonPressToSpeak.setVisibility(View.VISIBLE);
        faceNormal.setVisibility(View.VISIBLE);
        faceChecked.setVisibility(View.INVISIBLE);

    }

    /**
     * show keyboard
     */
    protected void setModeKeyboard() {
        edittext_layout.setVisibility(View.VISIBLE);
        buttonSetModeKeyboard.setVisibility(View.GONE);
        buttonSetModeVoice.setVisibility(View.VISIBLE);
        // mEditTextContent.setVisibility(View.VISIBLE);
        editText.requestFocus();
        // buttonSend.setVisibility(View.VISIBLE);
        buttonPressToSpeak.setVisibility(View.GONE);
        if (TextUtils.isEmpty(editText.getText())) {
            buttonMore.setVisibility(View.VISIBLE);
            buttonSend.setVisibility(View.GONE);
        } else {
            buttonMore.setVisibility(View.GONE);
            buttonSend.setVisibility(View.VISIBLE);
        }

    }
    
    
    protected void toggleFaceImage(){
        if(faceNormal.getVisibility() == View.VISIBLE){
            showSelectedFaceImage();
        }else{
            showNormalFaceImage();
        }
    }
    
    private void showNormalFaceImage(){
        faceNormal.setVisibility(View.VISIBLE);
        faceChecked.setVisibility(View.INVISIBLE);
    }
    
    private void showSelectedFaceImage(){
        faceNormal.setVisibility(View.INVISIBLE);
        faceChecked.setVisibility(View.VISIBLE);
    }
    

    @Override
    public void onExtendMenuContainerHide() {
        showNormalFaceImage();
    }

    @Override
    public void onTextInsert(CharSequence text) {
       int start = editText.getSelectionStart();
       Editable editable = editText.getEditableText();
       editable.insert(start, text);
       setModeKeyboard();
    }

    @Override
    public EditText getEditText() {
        return editText;
    }

}
