package com.gracecode.iZhihu.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.PowerManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.gracecode.iZhihu.Dao.Question;
import com.gracecode.iZhihu.Dao.QuestionsDatabase;
import com.gracecode.iZhihu.Fragments.DetailFragment;
import com.gracecode.iZhihu.Fragments.ScrollDetailFragment;
import com.gracecode.iZhihu.R;
import com.gracecode.iZhihu.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Detail extends BaseActivity implements ViewPager.OnPageChangeListener {
    private static final String TAG = Detail.class.getName();

    public static final String INTENT_EXTRA_CURRENT_QUESTION = "currentQuestion";
    public static final String INTENT_EXTRA_QUESTIONS = "questions";
    public static final String INTENT_EXTRA_CURRENT_POSITION = "currentPosition";
    public static final String INTENT_MODIFIED_LISTS = "modifiedLists";
    private static final int DEFAULT_POSITION = 0;

    private Menu menuItem;
    private PowerManager.WakeLock wakeLock;

    private static final int MESSAGE_UPDATE_START_SUCCESS = 0;
    private static final int MESSAGE_UPDATE_START_FAILD = -1;

    private QuestionsDatabase questionsDatabase;

    private DetailFragment fragCurrentQuestionDetail = null;
    private ScrollDetailFragment fragListQuestions = null;

    private Question currentQuestion = null;
    private ArrayList<Question> questionsList = new ArrayList<>();
    private int currentPosition = DEFAULT_POSITION;

    private boolean isShareByTextOnly = false;
    private boolean isShareAndSave = true;
    private boolean isSetScrollRead = true;


    /**
     * 标记当前条目（未）收藏
     */
    private final Runnable MarkAsStared = new Runnable() {
        @Override
        public void run() {
            Boolean isStared = currentQuestion.isStared();

            if (questionsDatabase.markQuestionAsStared(currentQuestion.getId(), !isStared) > 0) {
                currentQuestion.setStared(!isStared);
                UIChangedChangedHandler.sendEmptyMessage(MESSAGE_UPDATE_START_SUCCESS);
            } else {
                UIChangedChangedHandler.sendEmptyMessage(MESSAGE_UPDATE_START_FAILD);
            }
        }
    };


    /**
     * 刷新 UI 线程集中的地方
     */
    private final android.os.Handler UIChangedChangedHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_UPDATE_START_SUCCESS:
                    Util.showShortToast(context,
                            getString(currentQuestion.isStared() ?
                                    R.string.mark_as_starred : R.string.cancel_mark_as_stared));

                    updateMenu();
                    break;
                case MESSAGE_UPDATE_START_FAILD:
                    Util.showLongToast(context, getString(R.string.database_faild));
                    break;
            }
        }
    };


    /**
     * 更新 ActionBar 的收藏图标，并返回状态
     */
    private void updateMenu() {
        if (menuItem != null) {
            menuItem.findItem(R.id.menu_favorite).setIcon(currentQuestion.isStared() ?
                    R.drawable.ic_action_star_selected : R.drawable.ic_action_star);

            if (!Util.isExternalStorageExists() && !isShareByTextOnly) {
                menuItem.findItem(R.id.menu_share).setEnabled(false);
            }
        }
    }


    /**
     * 判断是否需要常亮屏幕
     *
     * @return boolean
     */
    private boolean isNeedScreenWakeLock() {
        return sharedPreferences.getBoolean(getString(R.string.key_wake_lock), true);
    }


    /**
     * 获取截图文件
     *
     * @return
     */
    private File getScreenShotFile() throws IOException {
        if (currentQuestion == null || currentQuestion.getId() == DetailFragment.ID_NOT_FOUND) {
            throw new IOException();
        }

        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(pictureDirectory, currentQuestion.getId() + ".png");
    }


    private void updateCurrentQuestion(int index) {
        currentQuestion = questionsList.get(index);
        if (currentQuestion != null) {
            updateMenu();

            currentPosition = index;
            questionsDatabase.markAsRead(currentQuestion.getId());
            currentQuestion.setUnread(false);

            if (fragListQuestions != null) {
                fragCurrentQuestionDetail = fragListQuestions.getItem(currentPosition);
            }
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 屏幕常亮控制
        PowerManager powerManager = ((PowerManager) getSystemService(POWER_SERVICE));
        this.wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, Detail.class.getName());

        // 配置项
        this.isShareByTextOnly = sharedPreferences.getBoolean(getString(R.string.key_share_text_only), false);
        this.isShareAndSave = sharedPreferences.getBoolean(getString(R.string.key_share_and_save), true);
        this.isSetScrollRead = sharedPreferences.getBoolean(getString(R.string.key_scroll_read), true);

        // Database for questions.
        this.questionsDatabase = new QuestionsDatabase(context);

        // 获取当权选定的条目
        if (savedInstanceState != null) {
            this.currentQuestion = savedInstanceState.getParcelable(INTENT_EXTRA_CURRENT_POSITION);
            this.questionsList = savedInstanceState.getParcelableArrayList(INTENT_EXTRA_QUESTIONS);
            this.currentPosition = savedInstanceState.getInt(INTENT_EXTRA_CURRENT_POSITION);
        } else {
            this.currentQuestion = getIntent().getParcelableExtra(INTENT_EXTRA_CURRENT_QUESTION);
            this.questionsList = getIntent().getParcelableArrayListExtra(INTENT_EXTRA_QUESTIONS);
            this.currentPosition = getIntent().getIntExtra(INTENT_EXTRA_CURRENT_POSITION, DEFAULT_POSITION);
        }

        // 是否是滚动阅读
        if (isSetScrollRead && questionsList.size() > 0) {
            this.fragListQuestions = new ScrollDetailFragment(this, questionsList, currentPosition);
        } else {
            this.fragCurrentQuestionDetail = new DetailFragment(this, currentQuestion);
        }

        // ActionBar 的样式
        actionBar.setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, (isSetScrollRead) ? fragListQuestions : fragCurrentQuestionDetail)
                .commit();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(INTENT_EXTRA_QUESTIONS, questionsList);
        outState.putParcelable(INTENT_EXTRA_CURRENT_POSITION, currentQuestion);
        outState.putInt(INTENT_EXTRA_CURRENT_POSITION, currentPosition);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onResume() {
        super.onResume();

        // Mark and update current item.
        updateCurrentQuestion(currentPosition);

        // 弱化 Navigation Bar
        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);

        if (isNeedScreenWakeLock()) {
            wakeLock.acquire();
        }

        // 是否保留分享时用的图片
        try {
            File screenshots = getScreenShotFile();
            if (!isShareByTextOnly && !isShareAndSave && screenshots != null && screenshots.exists()) {
                screenshots.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (isNeedScreenWakeLock()) {
            wakeLock.release();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        menuItem = menu;
        updateMenu();
        return true;
    }


    private void returnModifiedListsAndFinish() {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(INTENT_MODIFIED_LISTS, questionsList);
        setResult(Intent.FILL_IN_PACKAGE, intent);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            // Home(Back button)
            case android.R.id.home:
                returnModifiedListsAndFinish();
                return true;

            // Toggle star
            case R.id.menu_favorite:
                new Thread(MarkAsStared).start();
                return true;

            // View question via zhihu.com
            case R.id.menu_view_at_zhihu:
                String url =
                        String.format(getString(R.string.url_zhihu_questioin_pre),
                                currentQuestion.getQuestionId(), currentQuestion.getAnswerId());

                Util.openWithBrowser(this, url);
                return true;

            // Share question by intent
            case R.id.menu_share:
                String shareString = currentQuestion.getShareString(context);

                if (isShareByTextOnly) {
                    Util.openShareIntentWithPlainText(this, shareString);
                    return true;
                }

                try {
                    File screenShotFile = getScreenShotFile();
                    if (fragCurrentQuestionDetail.isTempScreenShotsFileCached()) {
                        Util.copyFile(fragCurrentQuestionDetail.getTempScreenShotsFile(), screenShotFile);
                        Util.openShareIntentWithImage(this, shareString, Uri.fromFile(screenShotFile));
                    } else {
                        throw new IOException();
                    }

                } catch (IOException e) {
                    Util.openShareIntentWithPlainText(this, shareString);
                    e.printStackTrace();
                } finally {
                    return true;
                }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            returnModifiedListsAndFinish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
        // ...
    }

    @Override
    public void onPageSelected(int i) {
        updateCurrentQuestion(i);
    }


    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        questionsDatabase.close();
    }
}
