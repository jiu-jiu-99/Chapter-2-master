package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relativelayout);
        TextView tv_center = findViewById(R.id.tv_center);
        View view = findViewById(R.id.root);
        tv_center.setText(getAllChildViewCount(view)+"");
        }

    public int getAllChildViewCount(View view) {
        //todo 补全你的代码
        int viewCount = 0;
        if (null == view) {
            return 0;
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (child instanceof ViewGroup) {
                    viewCount += getAllChildViewCount(((ViewGroup) view).getChildAt(i));
                }
                else {
                    viewCount++;
                }
            }
        }
        else {
            viewCount++;
        }
        return viewCount;
    }

}
