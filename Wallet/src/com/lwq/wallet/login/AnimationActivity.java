package com.lwq.wallet.login;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.lwq.wallet.adapter.ViewPageAdapter;

public class AnimationActivity extends Activity implements OnPageChangeListener{
	private ViewPager viewpager;
	private View page1,page2,page3;
	private ArrayList<View> views;
	private int currIndex=0;
	private ImageView mPage0,mPage1,mPage2;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //�����ޱ�����
		setContentView(R.layout.activity_animation);
		viewpager=(ViewPager)findViewById(R.id.viewpager);
		viewpager.setOnPageChangeListener(this);
		
		mPage0=(ImageView)findViewById(R.id.page0);
		mPage1=(ImageView)findViewById(R.id.page1);
		mPage2=(ImageView)findViewById(R.id.page2);
		
		LayoutInflater li=getLayoutInflater().from(this);
		View page1=li.inflate(R.layout.page_1,null);
		View page2=li.inflate(R.layout.page_2, null);
		View page3=li.inflate(R.layout.page_3, null);
		
		views=new ArrayList<View>();
		views.add(page1);
		views.add(page2);
		views.add(page3);
		
		viewpager.setAdapter(new ViewPageAdapter(views, this));
		viewpager.setOnPageChangeListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		  Animation animation = null;//������������  
          switch (arg0) {  
          case 0: //ҳ��һ             
              mPage0.setImageDrawable(getResources().getDrawable(R.drawable.dot1));//�����һ������ҳ�棬СԲ��Ϊѡ��״̬����һ��ҳ���СԲ����δѡ��״̬��  
              mPage1.setImageDrawable(getResources().getDrawable(R.drawable.dot2));  
              if (currIndex == arg0+1) {  
                  animation = new TranslateAnimation(arg0+1, arg0, 0, 0);//Բ���ƶ�Ч���������ӵ�ǰView�ƶ�����һ��View  
              }   
              break;  
          case 1: //ҳ���  
              mPage1.setImageDrawable(getResources().getDrawable(R.drawable.dot1));//��ǰView  
              mPage0.setImageDrawable(getResources().getDrawable(R.drawable.dot2));//��һ��View  
              mPage2.setImageDrawable(getResources().getDrawable(R.drawable.dot2));//��һ��View  
              if (currIndex == arg0-1) {//�����������һ��View  
                  animation = new TranslateAnimation(arg0-1, arg0, 0, 0); //Բ���ƶ�Ч���������ӵ�ǰView�ƶ�����һ��View  

                    
              } else if (currIndex == arg0+1) {//Բ���ƶ�Ч���������ӵ�ǰView�ƶ�����һ��View����ͬ��  

                  animation = new TranslateAnimation(arg0+1, arg0, 0, 0);  
              }  
              break;  
          case 2: //ҳ����  
              mPage2.setImageDrawable(getResources().getDrawable(R.drawable.dot1));  
              mPage1.setImageDrawable(getResources().getDrawable(R.drawable.dot2));  
              if (currIndex == arg0-1) {  
                  animation = new TranslateAnimation(arg0-1, arg0, 0, 0);  
              } 
              break;  
          }
          currIndex = arg0;//���õ�ǰView  
          animation.setFillAfter(true);// True:����ͼƬͣ�ڶ�������λ��  
          animation.setDuration(300);//���ö�������ʱ��  
    }  
}

