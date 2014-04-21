package com.lwq.wallet.save;

import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.lwq.wallet.login.R;

public class SaveCardActivity extends Activity implements OnItemSelectedListener{

	private static final String records="record";
	private static final String accountRecord="accountRecord";
	private Spinner spinner;
	private String []ways;
	private LinearLayout layout1,layout3,layout4;
	private ScrollView layout2;
	//layout1�����
	private EditText quick_money,quick_pass;
	private Button button;
	
	//layout2�����
	private EditText bank_money,bank_user,bank_userid,bank_id,phone,confirm,bank_pass;
	private Spinner bank;
	private Button button2,buttonconfirm;
	
	//layout3�����
	private EditText account_money,account_pass;
	private Button button3;
	
	//layout4�����
	private EditText pre_num,pre_pass;
	private Button button4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_savecard);
		
		initlayout();
		initspinner();
	}
	
	public void initlayout(){
		layout1=(LinearLayout)findViewById(R.id.savecard_layout1);
		layout2=(ScrollView)findViewById(R.id.savecard_layout2);
		layout3=(LinearLayout)findViewById(R.id.savecard_layout3);
		layout4=(LinearLayout)findViewById(R.id.savecard_layout4);
		layout1.setVisibility(View.VISIBLE);
		layout2.setVisibility(View.GONE);
		layout3.setVisibility(View.GONE);
		layout4.setVisibility(View.GONE);
	}
	public void initspinner(){
		spinner=(Spinner)findViewById(R.id.spinner);
		ways=getResources().getStringArray(R.array.saveways);
		ArrayAdapter<String> a=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ways);
		spinner.setAdapter(a);
		spinner.setOnItemSelectedListener(this);
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		switch(arg2){
		case 0:
			initlayout1();
			break;
		case 1:
			initlayout2();
			break;
		case 2:
			initlayout3();
			break;
		case 3:
			initlayout4();
		}		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {	
	}
	
	public void initlayout1(){
		layout1.setVisibility(View.VISIBLE);
		layout2.setVisibility(View.GONE);
		layout3.setVisibility(View.GONE);
		layout4.setVisibility(View.GONE);
		quick_money=(EditText)findViewById(R.id.savecardmoney);
		quick_pass=(EditText)findViewById(R.id.savecardpassword);
		button=(Button)findViewById(R.id.savecardbutton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String quickpay_money=quick_money.getText().toString();
				String quickpay_pass=quick_pass.getText().toString();
				if(quickpay_money.equals("")){
					Toast.makeText(SaveCardActivity.this, "������֧�����", Toast.LENGTH_SHORT).show();
					quick_money.setFocusable(true);
				}
				else if(quickpay_pass.equals("")){
					Toast.makeText(SaveCardActivity.this, "������֧������", Toast.LENGTH_SHORT).show();
					quick_pass.setFocusable(true);
				}
				else{
					//���ýӿ���֤
					boolean flag=true;
					if(flag){
						showdialog("֧���ɹ�");
						try {
							OutputStreamWriter os=new OutputStreamWriter(openFileOutput(records, MODE_APPEND));
							os.write("���֧��ת��:"+"\t"+quickpay_money+"\t"+getTime()+"\r\n");
							os.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						showdialog("֧��ʧ��");				
					}
					quick_pass.setText("");
					quick_money.setText("");
				}
			}
		});
	}
	
	public void initlayout2(){
		layout2.setVisibility(View.VISIBLE);
		layout1.setVisibility(View.GONE);
		layout3.setVisibility(View.GONE);
		layout4.setVisibility(View.GONE);
		bank_money=(EditText)findViewById(R.id.savecardbankmoney);
		bank=(Spinner)findViewById(R.id.savecardbank);
		bank_user=(EditText)findViewById(R.id.savecarduser);
		bank_userid=(EditText)findViewById(R.id.savecardid);
		bank_id=(EditText)findViewById(R.id.savecardbankid);
		phone=(EditText)findViewById(R.id.savecardphone);
		confirm=(EditText)findViewById(R.id.savecardconfirm);
		bank_pass=(EditText)findViewById(R.id.savecardbankpassword);
		button2=(Button)findViewById(R.id.savecardbankbutton);
		
		String bankname[]=getResources().getStringArray(R.array.bankname);
		bank.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,bankname));
		final mySpinner myspinner=new mySpinner(this,bankname);
		bank.setOnItemSelectedListener(myspinner);
		buttonconfirm=(Button)findViewById(R.id.savecardgetconfirm);
		buttonconfirm.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//��ȡ��֤��
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String money=bank_money.getText().toString();
				String user=bank_user.getText().toString();
				String userid=bank_userid.getText().toString();
				String id=bank_id.getText().toString();
				String phonenum=phone.getText().toString();
				String confirmnu=confirm.getText().toString();
				String pass=bank_pass.getText().toString();
				String choosebank=myspinner.getBank();
				if(money.equals("")){
					Toast.makeText(SaveCardActivity.this, "֧������Ϊ��", Toast.LENGTH_SHORT).show();
					bank_money.setFocusable(true);
				}
				else if(choosebank.equals("")){
					Toast.makeText(SaveCardActivity.this, "��ѡ������", Toast.LENGTH_SHORT).show();
				}
				else if(user.equals("")){
					Toast.makeText(SaveCardActivity.this, "��������Ϊ��", Toast.LENGTH_SHORT).show();
					bank_user.setFocusable(true);
				}
				else if(userid.equals("")){
					Toast.makeText(SaveCardActivity.this, "���֤�Ų���Ϊ��", Toast.LENGTH_SHORT).show();
					bank_userid.setFocusable(true);
				}
				else if(id.equals("")){
					Toast.makeText(SaveCardActivity.this, "���Ų���Ϊ��", Toast.LENGTH_SHORT).show();
					bank_id.setFocusable(true);
				}
				else if(phonenum.equals("")){
					Toast.makeText(SaveCardActivity.this, "�ֻ��Ų���Ϊ��", Toast.LENGTH_SHORT).show();
					phone.setFocusable(true);
				}
				else if(confirmnu.equals("")){
					Toast.makeText(SaveCardActivity.this, "��֤�벻��Ϊ��", Toast.LENGTH_SHORT).show();
					confirm.setFocusable(true);
				}
				else if(pass.equals("")){
					Toast.makeText(SaveCardActivity.this, "���벻��Ϊ��", Toast.LENGTH_SHORT).show();
					bank_pass.setFocusable(true);
				}
				else{
					//���ýӿ��ж�
					boolean flag=true;
					if(flag){
						showdialog("֧���ɹ�");
						try {
							OutputStreamWriter os=new OutputStreamWriter(openFileOutput(records, MODE_APPEND));
							os.write(choosebank+"����ת��:"+"\t"+money+"\t"+getTime()+"\r\n");
							os.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else{
						showdialog("֧��ʧ��");	
					}
					bank_money.setText("");
					bank_user.setText("");
					bank_userid.setText("");
					bank_id.setText("");
					phone.setText("");
					confirm.setText("");
					bank_pass.setText("");
				}
			}
		});
	}
	
	public void initlayout3(){
		layout3.setVisibility(View.VISIBLE);
		layout2.setVisibility(View.GONE);
		layout1.setVisibility(View.GONE);
		layout4.setVisibility(View.GONE);
		account_money=(EditText)findViewById(R.id.savecardaccountmoney);
		account_pass=(EditText)findViewById(R.id.savecardaccountpassword);
		button3=(Button)findViewById(R.id.savecardaccountbutton);
		button3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				String money=account_money.getText().toString();
				String password=account_pass.getText().toString();
				if(money.equals("")){
					Toast.makeText(SaveCardActivity.this, "������֧�����", Toast.LENGTH_SHORT).show();
					account_money.setFocusable(true);
				}
				else if(password.equals("")){
					Toast.makeText(SaveCardActivity.this, "������֧������", Toast.LENGTH_SHORT).show();
					account_pass.setFocusable(true);
				}
				else{
					//���ýӿ���֤
					boolean flag=true;
					if(flag){
						showdialog("֧���ɹ�");
						try {
							OutputStreamWriter os=new OutputStreamWriter(openFileOutput(records, MODE_APPEND));
							os.write("�˻�֧��ת��:"+"\t"+money+"\t"+getTime()+"\r\n");
							os.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						try{
							OutputStreamWriter os=new OutputStreamWriter(openFileOutput(accountRecord, MODE_APPEND));
							os.write("�˻�֧��ת��:"+"\t"+"-"+money+"\t"+getTime()+"\r\n");
							os.close();
						}catch (Exception e){
							e.printStackTrace();
						}
					}else{
						showdialog("֧��ʧ��");				
					}
					account_pass.setText("");
					account_money.setText("");
				}
			}
		});
	}
	
	public void initlayout4(){
		layout4.setVisibility(View.VISIBLE);
		layout2.setVisibility(View.GONE);
		layout3.setVisibility(View.GONE);
		layout1.setVisibility(View.GONE);
		pre_num=(EditText)findViewById(R.id.savecardprenum);
		pre_pass=(EditText)findViewById(R.id.savecardprepassword);
		button4=(Button)findViewById(R.id.savecardprebutton);
		button4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				String num=pre_num.getText().toString();
				String password=pre_pass.getText().toString();
				if(num.equals("")){
					Toast.makeText(SaveCardActivity.this, "������Ԥ������", Toast.LENGTH_SHORT).show();
					pre_num.setFocusable(true);
				}
				else if(password.equals("")){
					Toast.makeText(SaveCardActivity.this, "������֧������", Toast.LENGTH_SHORT).show();
					pre_pass.setFocusable(true);
				}
				else{
					//���ýӿ���֤
					boolean flag=true;
					if(flag){
						showdialog("֧���ɹ�");
						try {
							OutputStreamWriter os=new OutputStreamWriter(openFileOutput(records, MODE_APPEND));
							os.write("Ԥ����֧��ת��:"+"\t"+"30Ԫ"+"\t"+getTime()+"\r\n");
							os.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						showdialog("֧��ʧ��");				
					}
					pre_pass.setText("");
					pre_num.setText("");
				}
			}
		});
	}
	
	public void showdialog(String title){
		AlertDialog.Builder ab=new AlertDialog.Builder(this)
			.setTitle("��ʾ")
			.setTitle(title)
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
				}
			});
		ab.show();
	}
	
	public String getTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
		String date=sdf.format(new Date());
		return date;
	}
}

class mySpinner implements OnItemSelectedListener{

	Context context;
	String []bankname;
	String bank;
	public mySpinner(Context context,String bankname[]){
		this.context=context;
		this.bankname=bankname;
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		bank=bankname[arg2];
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		bank="";
	}
	
	public String getBank(){
		return this.bank;
	}
}
