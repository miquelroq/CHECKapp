package com.example.alarm_doc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 *
 * Code adapted from @joselfrias
 *
 * Series of Questions asked to the user in order
 * to instruct on how should they prepare their devices
 *
 */

public class CheckupProcess extends Activity {

        private ViewPager viewPager;
        private MyViewPagerAdapter myViewPagerAdapter;
        private LinearLayout dotsLayout;
        private ImageView[] dots;
        private int[] layouts;
        private Button btnBack, btnNext;
        private int dotscount;
        Button btnToApp;
        RadioGroup radioGroup;

        private int fatigue, chills;


        public void checkButton(View v){

            int q = viewPager.getCurrentItem();
            int selectedFreq = -1;

            switch(v.getId()) {

                case R.id.never:
                    selectedFreq = 0;
                    break;

                case R.id.almost_never:
                    selectedFreq = 1;
                    break;

                case R.id.sometimes:
                    selectedFreq = 2;
                    break;

                case R.id.almost_always:
                    selectedFreq = 3;
                    break;

                case R.id.always:
                    selectedFreq = 4;
                    break;

            }

            // Depending on what fragment we are we will update our dictionary differently
            if(q == 0){
                chills = selectedFreq;
            } else if(q == 1){
                fatigue = selectedFreq;
            }

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int current = getItem(+1);
                    if (current < layouts.length) {
                        viewPager.setCurrentItem(current);
                    }
                }
            });

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_checkup_process);


            viewPager =  findViewById(R.id.view_pager);
            dotsLayout = findViewById(R.id.layoutDots);
            btnBack = findViewById(R.id.btn_back);
            btnNext =  findViewById(R.id.btn_next);

            // layouts of questions
            layouts = new int[]{
                    R.layout.fragment_question1,
                    R.layout.fragment_question2,
                    R.layout.fragment_question3,
                    R.layout.fragment_question4,
                    R.layout.fragment_question5,
                    R.layout.fragment_question6,
                    R.layout.fragment_question7,
                    R.layout.fragment_question8,
                    R.layout.fragment_question9,
                    R.layout.fragment_question10,
                    R.layout.fragment_question11,
                    R.layout.fragment_question12,
                    R.layout.fragment_question13,
                    R.layout.fragment_question14,
                    R.layout.fragment_question15
            };

            // adding bottom dots
            dotscount = 15;
            dots = new ImageView[dotscount];

            for(int i = 0; i < dotscount; i++) {

                dots[i] = new ImageView(this);
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_active_dot));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8, 0, 8, 0);
                dotsLayout.addView(dots[i], params);

            }

            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            myViewPagerAdapter = new MyViewPagerAdapter();
            viewPager.setAdapter(myViewPagerAdapter);
            viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
            btnBack.setBackgroundResource(R.drawable.logo);

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int current = getItem(-1);
                    if (current < layouts.length) {
                        viewPager.setCurrentItem(current);
                    }
                }
            });

            if(viewPager.getCurrentItem() != 0 && viewPager.getCurrentItem() != 1) {
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int current = getItem(+1);
                        if (current < layouts.length) {
                            viewPager.setCurrentItem(current);
                        }
                    }
                });
            }

        }

        private int getItem(int i) {
            return viewPager.getCurrentItem() + i;
        }

        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

                if (position == layouts.length - 1) {
                    // Grey out right arrow
                    btnNext.setBackgroundResource(R.drawable.ic_baseline_check_24);

                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(CheckupProcess.this, DeviceSelect.class);
                            intent.putExtra("chills", chills);
                            intent.putExtra("fatigue", fatigue);

                            // Store chills

                            // Store fatigue


                            startActivity(intent);
                        }
                    });
                } else if (position == 0) {
                    // Grey out left arrow
                    btnBack.setBackgroundResource(R.drawable.ic_arrow_left_light);
                } else {
                    // Have them both available
                    btnBack.setBackgroundResource(R.drawable.ic_arrow_left);
                    btnNext.setBackgroundResource(R.drawable.ic_arrow_right);

                    if(viewPager.getCurrentItem() != 0 && viewPager.getCurrentItem() != 1) {
                        btnNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int current = getItem(+1);
                                if (current < layouts.length) {
                                    viewPager.setCurrentItem(current);
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        };

        public class MyViewPagerAdapter extends PagerAdapter {
            private LayoutInflater layoutInflater;

            public MyViewPagerAdapter() {
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View view = layoutInflater.inflate(layouts[position], container, false);

                container.addView(view);

                return view;
            }

            @Override
            public int getCount() {
                return layouts.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }


            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View view = (View) object;
                container.removeView(view);
            }
        }

    }