package com.lwq.wallet.panel;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.lwq.wallet.login.R;

public class ConsultActivity extends TabActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consult);
		
		TabHost tabhost=getTabHost();
		TabHost.TabSpec spec=null;
		
		//��ӱ�ǩҳ
		Intent intent=new Intent();
		intent.setClass(this, CardActivity.class);
		spec=tabhost.newTabSpec("tag1");
		spec.setIndicator("������ϸ��ѯ");
		spec.setContent(intent);
		tabhost.addTab(spec);
		
		intent.setClass(this, AccountActivity.class);
		spec=tabhost.newTabSpec("tag2");
		spec.setIndicator("�����˻���ϸ��ѯ");
		spec.setContent(intent);
		tabhost.addTab(spec);
	}

}
