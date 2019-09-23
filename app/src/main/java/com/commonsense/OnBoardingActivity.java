package com.commonsense;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager mViewPagerSlider;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout mLinearLayoutDots;
    private Button mButtonGetStart,mButtonGetStartSkip;
    private int[] slideLayouts;
    private TextView[] dots;
    private int currentPage;
    public TextView mTvSpan1,mTvSpan2,mTvSpan3,mTvSpan4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        mViewPagerSlider = (ViewPager) findViewById(R.id.viewpager_intro);
        mLinearLayoutDots = (LinearLayout) findViewById(R.id.layout_dots);
        mButtonGetStart = (Button) findViewById(R.id.button_get_start);
        mButtonGetStartSkip = findViewById(R.id.button_get_start_skip);

        /*Order 1,2,3,4*/
        slideLayouts = new int[]{R.layout.slider1,
                R.layout.slider2,
                R.layout.slider3,
                R.layout.slider4,
        };


        addBottomDots(0);

        mButtonGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mViewPagerSlider.setAdapter(viewPagerAdapter);
                mViewPagerSlider.setCurrentItem(currentPage+1, true);
//                viewPagerAdapter.notifyDataSetChanged();
            }
        });

        mButtonGetStartSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mViewPagerSlider.setAdapter(viewPagerAdapter);
                Intent i = new Intent(OnBoardingActivity.this,MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                viewPagerAdapter.notifyDataSetChanged();
            }
        });

        viewPagerAdapter = new ViewPagerAdapter();
        mViewPagerSlider.setAdapter(viewPagerAdapter);
        mViewPagerSlider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
                currentPage = position;
                try {
                    if (position == 1) {
                        mTvSpan1.setText(spannableString);
                    }
                }catch (NullPointerException e){

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void addBottomDots(int position) {
        //dots = new TextView[slideLayouts.length];
        ImageView[] dots = new ImageView[slideLayouts.length];
        mLinearLayoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            //dots[i] = new TextView(this);
            dots[i] = new ImageView(this);
            dots[i].setPadding(5, 5, 5, 5);
            if ((i == position)) {
                //dots[i].setText("\u25CF");
                dots[i].setImageResource(R.drawable.ic_dot_filled_white);
                dots[i].setColorFilter(getResources().getColor(R.color.colorAccent));

                System.out.println("///////// pos : "+position);
                if(position == 3){
                    //mButtonGetStart.setText("SKIP");
                    mButtonGetStart.setVisibility(View.GONE);
                    mButtonGetStartSkip.setVisibility(View.VISIBLE);

                    findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(OnBoardingActivity.this,SignupActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        }
                    });
                    findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(OnBoardingActivity.this,LoginActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        }
                    });
                }
            } else {
                //dots[i].setText("\u25CB");
                dots[i].setImageResource(R.drawable.ic_dot_filled_transparent_40_white);
//                dots[i].setTextSize(10);
            }

            mLinearLayoutDots.addView(dots[i]);
        }
    }
    SpannableString spannableString;
    SpannableString spannableStringMsg,spannableStringMsg1;
    TextView tvMesage,tvMesage1;
    class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(slideLayouts[position], container, false);
            mTvSpan1 = view.findViewById(R.id.text_title1);
            tvMesage = view.findViewById(R.id.text_message2);
            tvMesage1 = view.findViewById(R.id.text_message3);

            if(slideLayouts[0]==slideLayouts[position]){
                System.out.println("///////////////// slideLayouts[0]"+slideLayouts[0]);
                System.out.println("///////////////// slideLayouts[position]"+slideLayouts[position]);
                spannableString = new SpannableString("Myself Mr. Bobby , Super excited to see you here....You know i am crazily lazy.. So i will be finding smarter way everytime....No problem as a friend i will guide you to find the smarter ways of clothing experience ...Finally Welcome to COMMON SENSE");
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)),
                        7, 17,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)),
                        237, spannableString.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mTvSpan1.setText(spannableString);
            }
            else if(slideLayouts[1]==slideLayouts[position]){
                System.out.println("///////////////// slideLayouts[1]"+slideLayouts[1]);
                System.out.println("///////////////// slideLayouts[position]"+slideLayouts[position]);
                spannableStringMsg = new SpannableString("Hey , And you know if you are a male we call you as 'Buddy', And the wear is called 'Bubby Wear'");
                spannableStringMsg.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)),
                        53, 58,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringMsg.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)),
                        85, spannableStringMsg.length()-1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvMesage.setText(spannableStringMsg);
            }
            else if(slideLayouts[2]==slideLayouts[position]){
                System.out.println("///////////////// slideLayouts[2]"+slideLayouts[2]);
                System.out.println("///////////////// slideLayouts[position]"+slideLayouts[position]);
                spannableStringMsg1 = new SpannableString("Hey , And you know if you are a female we call you as 'Dimple', And the wear is called 'Dimple Wear'");
                spannableStringMsg1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)),
                        55, 61,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringMsg1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)),
                        88, spannableStringMsg1.length()-1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvMesage1.setText(spannableStringMsg1);
            }
            /*else if(slideLayouts[position]==slideLayouts[3]){
                System.out.println("///////////////// slideLayouts[3]"+slideLayouts[3]);
                System.out.println("///////////////// slideLayouts[position]"+slideLayouts[position]);
                //              //button name change to skip
                mButtonGetStart.setVisibility(View.GONE);
                mButtonGetStartSkip.setVisibility(View.VISIBLE);
                mButtonGetStartSkip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                mViewPagerSlider.setAdapter(viewPagerAdapter);
                        Intent i = new Intent(OnBoardingActivity.this,MainActivity.class);
                        startActivity(i);
//                viewPagerAdapter.notifyDataSetChanged();
                    }
                });
            }*/

            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return slideLayouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            try {
                super.finishUpdate(container);
            }
            catch (NullPointerException nullpointerexception){
                nullpointerexception.printStackTrace();
            }
        }
    }
}
