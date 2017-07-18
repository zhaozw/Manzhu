package com.hyphenate.easeui.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hyphenate.easeui.R;
import com.hyphenate.easeui.domain.EaseEmojicon;
import com.hyphenate.easeui.domain.EaseEmojiconGroupEntity;
import com.hyphenate.easeui.model.EaseDefaultEmojiconDatas;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.widget.EaseChatExtendMenu.EaseChatExtendMenuItemClickListener;
import com.hyphenate.easeui.widget.EaseChatPrimaryMenuBase.EaseChatPrimaryMenuListener;
import com.hyphenate.easeui.widget.emojicon.EaseEmojiconMenu;
import com.hyphenate.easeui.widget.emojicon.EaseEmojiconMenuBase;
import com.hyphenate.easeui.widget.emojicon.EaseEmojiconMenuBase.EaseEmojiconMenuListener;

import static com.hyphenate.chat.EMGCMListenerService.TAG;


/**
 * input menu
 * 
 * including below component:
 *    EaseChatPrimaryMenu: main menu bar, text input, send button
 *    EaseChatExtendMenu: grid menu with image, file, location, etc
 *    EaseEmojiconMenu: emoji icons
 */
public class EaseChatInputMenu extends LinearLayout {
    FrameLayout primaryMenuContainer, emojiconMenuContainer;
    protected EaseChatPrimaryMenuBase chatPrimaryMenu;
    protected EaseEmojiconMenuBase emojiconMenu;
    protected EaseChatExtendMenu chatExtendMenu;
    protected FrameLayout chatExtendMenuContainer;
    protected LayoutInflater layoutInflater;

    private Handler handler = new Handler();
    private ChatInputMenuListener listener;
    private Context context;
    private boolean inited;
    private ImageView mText;
    private ImageView mTupian;
    private LinearLayout mLlYuyin;
    private ImageView mYuyin;
    private ImageView mBiaoqing;

    public EaseChatInputMenu(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public EaseChatInputMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EaseChatInputMenu(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.ease_widget_chat_input_menu, this);
        //输入框的布局
        primaryMenuContainer = (FrameLayout) findViewById(R.id.primary_menu_container);
        //表情的内容
        emojiconMenuContainer = (FrameLayout) findViewById(R.id.emojicon_menu_container);


        //点击加号以后 展示的界面
        chatExtendMenuContainer = (FrameLayout) findViewById(R.id.extend_menu_container);

         // extend menu
         chatExtendMenu = (EaseChatExtendMenu) findViewById(R.id.extend_menu);
        

    }

    /**
     * init view 
     * 
     * This method should be called after registerExtendMenuItem(), setCustomEmojiconMenu() and setCustomPrimaryMenu().
     * @param emojiconGroupList --will use default if null
     */
    @SuppressLint("InflateParams")
    public void init(List<EaseEmojiconGroupEntity> emojiconGroupList) {
        if(inited){
            return;
        }
        // primary menu, use default if no customized one
        if(chatPrimaryMenu == null){
            chatPrimaryMenu = (EaseChatPrimaryMenu) layoutInflater.inflate(R.layout.ease_layout_chat_primary_menu, null);
            mText = (ImageView) chatPrimaryMenu.findViewById(R.id.iv_zhaoxiang);
            mTupian = (ImageView) chatPrimaryMenu.findViewById(R.id.iv_tupian);
            mLlYuyin = (LinearLayout) chatPrimaryMenu.findViewById(R.id.ll_luyin);
            mYuyin = (ImageView) chatPrimaryMenu.findViewById(R.id.iv_yuyin);
            mBiaoqing = (ImageView) chatPrimaryMenu.findViewById(R.id.iv_biaoqing);
        }
        primaryMenuContainer.addView(chatPrimaryMenu);

        // emojicon menu, use default if no customized one
        if(emojiconMenu == null){
            emojiconMenu = (EaseEmojiconMenu) layoutInflater.inflate(R.layout.ease_layout_emojicon_menu, null);
            if(emojiconGroupList == null){
                emojiconGroupList = new ArrayList<EaseEmojiconGroupEntity>();
                emojiconGroupList.add(new EaseEmojiconGroupEntity(R.drawable.ee_1,  Arrays.asList(EaseDefaultEmojiconDatas.getData())));
            }
            ((EaseEmojiconMenu)emojiconMenu).init(emojiconGroupList);
        }
        emojiconMenuContainer.addView(emojiconMenu);

        processChatMenu();
        chatExtendMenu.init();
        
        inited = true;


        mBiaoqing.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleEmojicon();

                hideKeyboard();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        mLlYuyin.setVisibility(GONE);
                    }
                }, 50);

            }
        });
    }
    
    public void init(){
        init(null);
    }

    public ImageView getmyZhao(){

        return mText;
    }


    public ImageView getmyPic(){

        return mTupian;
    }


    public  LinearLayout getLlYuyin(){

        return mLlYuyin;

    }

    public ImageView getYuyin(){

        return mYuyin;

    }



    
    /**
     * set custom emojicon menu
     * @param customEmojiconMenu
     */
    public void setCustomEmojiconMenu(EaseEmojiconMenuBase customEmojiconMenu){
        this.emojiconMenu = customEmojiconMenu;
    }
    
    /**
     * set custom primary menu
     * @param customPrimaryMenu
     */
    public void setCustomPrimaryMenu(EaseChatPrimaryMenuBase customPrimaryMenu){
        this.chatPrimaryMenu = customPrimaryMenu;
    }
    
    public EaseChatPrimaryMenuBase getPrimaryMenu(){
        return chatPrimaryMenu;
    }
    
    public EaseChatExtendMenu getExtendMenu(){
        return chatExtendMenu;
    }
    
    public EaseEmojiconMenuBase getEmojiconMenu(){
        return emojiconMenu;
    }
    

    /**
     * register menu item
     * 
     * @param name
     *            item name
     * @param drawableRes
     *            background of item
     * @param itemId
     *             id
     * @param listener
     *            on click event of item
     */
    public void registerExtendMenuItem(String name, int drawableRes, int itemId,
            EaseChatExtendMenuItemClickListener listener) {
        chatExtendMenu.registerMenuItem(name, drawableRes, itemId, listener);
    }

    /**
     * register menu item
     * 
     * @param name
     *            resource id of item name
     * @param drawableRes
     *            background of item
     * @param itemId
     *             id
     * @param listener
     *            on click event of item
     */
    public void registerExtendMenuItem(int nameRes, int drawableRes, int itemId,
            EaseChatExtendMenuItemClickListener listener) {
        chatExtendMenu.registerMenuItem(nameRes, drawableRes, itemId, listener);
    }


    protected void processChatMenu() {
        // send message button
        chatPrimaryMenu.setChatPrimaryMenuListener(new EaseChatPrimaryMenuListener() {

            @Override
            public void onSendBtnClicked(String content) {
                if (listener != null)
                    listener.onSendMessage(content);
            }

            @Override
            public void onToggleVoiceBtnClicked() {
                hideExtendMenuContainer();
            }

            @Override
            public void onToggleExtendClicked() {
                toggleMore();
            }

            @Override
            public void onToggleEmojiconClicked() {
                toggleEmojicon();
            }

            @Override
            public void onEditTextClicked() {
                hideExtendMenuContainer();
            }


            @Override
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {
                if(listener != null){
                    return listener.onPressToSpeakBtnTouch(v, event);
                }
                return false;
            }
        });

        // emojicon menu
        emojiconMenu.setEmojiconMenuListener(new EaseEmojiconMenuListener() {

            @Override
            public void onExpressionClicked(EaseEmojicon emojicon) {
                if(emojicon.getType() != EaseEmojicon.Type.BIG_EXPRESSION){
                    if(emojicon.getEmojiText() != null){
                        chatPrimaryMenu.onEmojiconInputEvent(EaseSmileUtils.getSmiledText(context,emojicon.getEmojiText()));
                        String path = emojicon.getIconPath();
                        Log.i("emoji", "onExpressionClicked:  按了表情的小控件" + emojicon.getIcon() + "type" + emojicon.getType());
                        // TODO: 2017/7/18 0018  这个地方是表情变成文字的地方



                    }
                }else{
                    if(listener != null){
                        Log.i("emoji", "onExpressionClicked:  按了表情的小控件2");
                        listener.onBigExpressionClicked(emojicon);
                    }
                }
            }

            @Override
            public void onDeleteImageClicked() {
                //按了删除的按钮
                Log.i("emoji", "onExpressionClicked:  按了表情的小控件3");
                chatPrimaryMenu.onEmojiconDeleteEvent();
            }
        });

    }
    
   
    /**
     * insert text
     * @param text
     */
    public void insertText(String text){
        getPrimaryMenu().onTextInsert(text);
    }

    /**
     * show or hide extend menu
     * 
     */
    protected void toggleMore() {
        if (chatExtendMenuContainer.getVisibility() == View.GONE) {
            hideKeyboard();
            handler.postDelayed(new Runnable() {
                public void run() {
                    chatExtendMenuContainer.setVisibility(View.VISIBLE);
                    chatExtendMenu.setVisibility(View.VISIBLE);
                    emojiconMenu.setVisibility(View.GONE);
                }
            }, 50);
        } else {
            if (emojiconMenu.getVisibility() == View.VISIBLE) {
                emojiconMenu.setVisibility(View.GONE);
                chatExtendMenu.setVisibility(View.VISIBLE);
            } else {
                chatExtendMenuContainer.setVisibility(View.GONE);
            }
        }
    }

    /**
     * show or hide emojicon
     */
    public  void toggleEmojicon() {
        if (chatExtendMenuContainer.getVisibility() == View.GONE) {
            hideKeyboard();
            handler.postDelayed(new Runnable() {
                public void run() {
                    chatExtendMenuContainer.setVisibility(View.VISIBLE);
                    chatExtendMenu.setVisibility(View.GONE);
                    emojiconMenu.setVisibility(View.VISIBLE);
                }
            }, 50);
        } else {
            if (emojiconMenu.getVisibility() == View.VISIBLE) {
                chatExtendMenuContainer.setVisibility(View.GONE);
                emojiconMenu.setVisibility(View.GONE);
            } else {
                chatExtendMenu.setVisibility(View.GONE);
                emojiconMenu.setVisibility(View.VISIBLE);
            }

        }
    }


    public void hideEmoji(){
        chatExtendMenuContainer.setVisibility(View.GONE);
        emojiconMenu.setVisibility(View.GONE);
    }



    /**
     * hide keyboard
     */
    private void hideKeyboard() {
        chatPrimaryMenu.hideKeyboard();
    }

    /**
     * hide extend menu
     */
    public void hideExtendMenuContainer() {
        chatExtendMenu.setVisibility(View.GONE);
        emojiconMenu.setVisibility(View.GONE);
        chatExtendMenuContainer.setVisibility(View.GONE);
        chatPrimaryMenu.onExtendMenuContainerHide();
    }

    /**
     * when back key pressed
     * 
     * @return false--extend menu is on, will hide it first
     *         true --extend menu is off 
     */
    public boolean onBackPressed() {
        if (chatExtendMenuContainer.getVisibility() == View.VISIBLE) {
            hideExtendMenuContainer();
            return false;
        } else {
            return true;
        }

    }
    

    public void setChatInputMenuListener(ChatInputMenuListener listener) {
        this.listener = listener;
    }

    public interface ChatInputMenuListener {
        /**
         * when send message button pressed
         * 
         * @param content
         *            message content
         */
        void onSendMessage(String content);
        
        /**
         * when big icon pressed
         * @param emojicon
         */
        void onBigExpressionClicked(EaseEmojicon emojicon);

        /**
         * when speak button is touched
         * @param v
         * @param event
         * @return
         */
        boolean onPressToSpeakBtnTouch(View v, MotionEvent event);
    }
    
}
