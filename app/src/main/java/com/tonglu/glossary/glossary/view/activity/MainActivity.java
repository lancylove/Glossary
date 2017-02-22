package com.tonglu.glossary.glossary.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.model.ContextBean;
import com.tonglu.glossary.glossary.view.fragment.GlossaryFragment;
import com.tonglu.glossary.glossary.view.fragment.MeFragment;
import com.tonglu.glossary.glossary.view.fragment.NoteFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public RadioGroup radioGroup;
    private List<Fragment> fragments = new ArrayList<Fragment>();

    private FragmentManager fragmentManager;
    private Fragment mCurFragment;
    ContextBean contextBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextBean = getIntent().getParcelableExtra("context");

        initView();
    }

    @Override
    public void initView() {
        super.initView();
        radioGroup = (RadioGroup) findViewById(R.id.tabs_rg);


        fragments.add(new NoteFragment());
        fragments.add(new GlossaryFragment());
        fragments.add(new MeFragment());

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment_context, fragments.get(0));
        ft.commit();
        mCurFragment = fragments.get(0);


        radioGroup.check(radioGroup.getChildAt(0).getId());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    if (radioGroup.getChildAt(i).getId() == checkedId) {
                        checkFragment(i);
                        break;
                    }
                }
            }
        });
    }


    private void checkFragment(int i) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment to = fragments.get(i);
        for (Fragment fragment : fragments) {
            if (fragment.isAdded()) {
                ft.hide(fragment);
            }
        }
        if (mCurFragment != to) {
            if (!to.isAdded()) {
                ft.hide(mCurFragment).add(R.id.fragment_context, to).commitAllowingStateLoss();
            } else {
                ft.hide(mCurFragment).show(to).commitAllowingStateLoss();   // 隐藏当前的fragment，显示下一个
            }
        }
        mCurFragment = to;
    }

}
