package com.lwq.wallet.panel;

import java.util.ArrayList;

import com.lwq.wallet.adapter.RecordAdapter;
import com.lwq.wallet.login.R;
import com.lwq.wallet.service.GetBalanceService;
import com.lwq.wallet.service.RecordService;
import com.lwq.wallet.utils.RecordItem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class AccountActivity extends Activity{
	private static final String accountRecord="accountRecord";
	//�����ʾ
	private TextView textview_balance;
	
	//��ʾ���Ѽ�¼
	private ListView record_list;
	
	//������Ѽ�¼��list
	private ArrayList<RecordItem> list=new ArrayList<RecordItem>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		
		textview_balance=(TextView)findViewById(R.id.accountbalance);
		textview_balance.setText(getBalance()+"Ԫ");
		
		record_list=(ListView)findViewById(R.id.recordlist2);
		list=getList();
		record_list.setAdapter(new RecordAdapter(this, list));
	}

	public String getBalance(){
		GetBalanceService as=new GetBalanceService(this);
		int balance=as.getBalance(accountRecord);
		return String.valueOf(balance);
	}
	
	public ArrayList<RecordItem> getList(){
		RecordService as=new RecordService(this);
		return as.getRecordList(accountRecord);
	}
}
