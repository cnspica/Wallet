package com.lwq.wallet.panel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.lwq.wallet.login.R;
import com.lwq.wallet.service.infoService;

public class InfoActivity extends Activity implements OnClickListener,OnItemSelectedListener,OnCheckedChangeListener{

	private static final String setting="setting";
	private LinearLayout layout1,layout2,layout3,layout4;
	private LinearLayout text1,text2,text3,text4;
	private EditText old,new1,new2;
	private EditText resetUser,resetPhone,resetEmail;
	private Button submitpassword,resetpassword;
	private TextView []text=new TextView[3];
	private infoService as=new infoService(this);
	private Spinner spinner1,spinner2,spinner3;
	private String [] auto_money,auto;
	private Switch buttonswitch;
	private boolean flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		
		text1=(LinearLayout)findViewById(R.id.press1);
		text2=(LinearLayout)findViewById(R.id.press2);
		text3=(LinearLayout)findViewById(R.id.press3);
		text4=(LinearLayout)findViewById(R.id.press4);
		layout1=(LinearLayout)findViewById(R.id.info_layout1);
		layout2=(LinearLayout)findViewById(R.id.info_layout2);
		layout3=(LinearLayout)findViewById(R.id.info_layout3);
		layout4=(LinearLayout)findViewById(R.id.info_layout4);
		
		text[0]=(TextView)findViewById(R.id.info_username);
		text[1]=(TextView)findViewById(R.id.info_phone);
		text[2]=(TextView)findViewById(R.id.info_mail);
		
		//��ʾ�û���Ϣ
		Vector<String> info=as.getInfo();
		for(int i=0;i<info.size();i++){
			//if(!info[i].equals("")){
				text[i].setText(info.get(i));
			//}
		}
		//��ʼʱ������ʾ�û���Ϣ
		layout1.setVisibility(View.VISIBLE);
		layout2.setVisibility(View.GONE);
		layout3.setVisibility(View.GONE);
		layout4.setVisibility(View.GONE);
		
		//�����޸����벿��
		old=(EditText)findViewById(R.id.oldpassword);
		new1=(EditText)findViewById(R.id.newpassword);
		new2=(EditText)findViewById(R.id.newpassword2);
		submitpassword=(Button)findViewById(R.id.password_change);
		submitpassword.setOnClickListener(this);
		
		//������Ϣ���ò���
		resetUser=(EditText)findViewById(R.id.info_reset_user);
		resetPhone=(EditText)findViewById(R.id.info_reset_phone);
		resetEmail=(EditText)findViewById(R.id.info_reset_email);
		resetpassword=(Button)findViewById(R.id.reset_change);
		resetpassword.setOnClickListener(this);
		
		text1.setOnClickListener(this);
		text2.setOnClickListener(this);
		text3.setOnClickListener(this);
		text4.setOnClickListener(this);
		
		//����ͨ������layout�е�spinner����
		spinner1=(Spinner)findViewById(R.id.quickspinner);
		spinner2=(Spinner)findViewById(R.id.quickspinner1);
		spinner3=(Spinner)findViewById(R.id.quickspinner2);
		auto_money=getResources().getStringArray(R.array.auto_money);
		auto=getResources().getStringArray(R.array.auto);
		ArrayAdapter<String> a1=new ArrayAdapter<String>(this, R.layout.myspinner,auto_money);
		ArrayAdapter<String> a2=new ArrayAdapter<String>(this, R.layout.myspinner,auto);
		a1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		a2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spinner2.setAdapter(a1);
		spinner3.setAdapter(a2);
		spinner2.setOnItemSelectedListener(this);
		spinner3.setOnItemSelectedListener(this);
		
		buttonswitch=(Switch)findViewById(R.id.switchbutton);
		buttonswitch.setOnCheckedChangeListener(this);
		
		SharedPreferences sharedPreferences=getSharedPreferences(setting, MODE_PRIVATE);
		flag=sharedPreferences.getBoolean("isChecked", false);
		buttonswitch.setChecked(flag);
		initspinner(flag);
	}
	@Override
	public void onClick(View v) {
		String oldpassword,newpassword1,newpassword2;
		String user,phone,email;
		//����ס��һ��������
		if(v.getId()==R.id.press1){
			layout1.setVisibility(View.VISIBLE);
			layout2.setVisibility(View.GONE);
			layout3.setVisibility(View.GONE);
			layout4.setVisibility(View.GONE);
			Vector<String> info=as.getInfo();
			for(int i=0;i<info.size();i++){
				//if(!info[i].equals("")){
					text[i].setText(info.get(i));
				//}
			}
		}
		else if(v.getId()==R.id.press2){
			layout2.setVisibility(View.VISIBLE);
			layout1.setVisibility(View.GONE);
			layout3.setVisibility(View.GONE);
			layout4.setVisibility(View.GONE);
		}
		else if(v.getId()==R.id.press3){
			layout3.setVisibility(View.VISIBLE);
			layout2.setVisibility(View.GONE);
			layout1.setVisibility(View.GONE);
			layout4.setVisibility(View.GONE);
		}
		else if(v.getId()==R.id.press4){
			layout4.setVisibility(View.VISIBLE);
			layout2.setVisibility(View.GONE);
			layout3.setVisibility(View.GONE);
			layout1.setVisibility(View.GONE);
		}
		else if(v.getId()==R.id.password_change){
			oldpassword=old.getText().toString();
			newpassword1=new1.getText().toString();
			newpassword2=new2.getText().toString();
			if(oldpassword.equals("")){
				Toast.makeText(this, "������ԭ����", Toast.LENGTH_SHORT).show();
				old.setFocusable(true);
			}
			else if(newpassword1.equals("")){
				Toast.makeText(this, "������������", Toast.LENGTH_SHORT).show();
				new1.setFocusable(true);
			}
			else if(newpassword2.equals("")){
				Toast.makeText(this, "���ٴ�����������", Toast.LENGTH_SHORT).show();
				new2.setFocusable(true);
			}
			else if(!newpassword1.equals(newpassword2))
			{
				Toast.makeText(this, "��������ͬ��������", Toast.LENGTH_SHORT).show();
				new1.setText("");
				new2.setText("");
			}
			else{
				boolean flag=as.changePass(oldpassword, newpassword1);
				//��������������������֤
				if(flag){
					new AlertDialog.Builder(this).setTitle("����").setMessage("�����޸ĳɹ�")
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							old.setText("");
							new1.setText("");
							new2.setText("");
						}
					}).show();
				}
				else{
					new AlertDialog.Builder(this).setTitle("����").setMessage("�����޸�ʧ��")
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
						}
					}).show();
				}
			}
		}
		
		//���������ð�ť
		else if(v.getId()==R.id.reset_change){
			user=resetUser.getText().toString();
			phone=resetPhone.getText().toString();
			email=resetEmail.getText().toString();

			boolean flag=as.resetInfo(user, phone, email);
			//����Ϣ���������������޸�
			if(flag){
				new AlertDialog.Builder(this).setTitle("����").setMessage("��Ϣ�޸ĳɹ�")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						resetPhone.setText("");
						resetUser.setText("");
						resetEmail.setText("");
					}
				}).show();
			}
			else{
				new AlertDialog.Builder(this).setTitle("����").setMessage("��Ϣ�޸�ʧ��")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
					}
				}).show();
			}
		}
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		String auto1="5Ԫ";
		String auto2="10Ԫ";
		if(arg0.getId()==R.id.quickspinner1){
			auto1=auto_money[arg2];
		}
		if(arg0.getId()==R.id.quickspinner2){
			auto2=auto[arg2];
		}
		SharedPreferences preferences=getSharedPreferences(setting, MODE_PRIVATE);
		Editor editor=preferences.edit();
		editor.putString("auto1", auto1);
		editor.putString("auto2", auto2);
		editor.commit();
	}

	public void onNothingSelected(AdapterView<?> arg0) {		
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(buttonView.getId()==R.id.switchbutton){
			//��switch��ť״̬����sharedpreference��
			SharedPreferences preferences=getSharedPreferences(setting, MODE_PRIVATE);
			Editor editor=preferences.edit();
			editor.putBoolean("isChecked", isChecked);
			editor.commit();
			
			//��ʼ��spinner��ť
			initspinner(isChecked);
		}
	}
	
	public void initspinner(boolean flag){
		if(flag){
			spinner1.setClickable(true);
			spinner2.setClickable(true);
			spinner3.setClickable(true);
		}
		else{
			spinner1.setClickable(false);
			spinner2.setClickable(false);
			spinner3.setClickable(false);
		}
	}
	
}
