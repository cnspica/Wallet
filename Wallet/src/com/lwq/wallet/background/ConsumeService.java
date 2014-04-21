package com.lwq.wallet.background;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.lwq.wallet.login.R;
import com.lwq.wallet.panel.AccountActivity;

public class ConsumeService extends Service{

	private static final int Notifyme=1234;  //����֪ͨID
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		//��������
		service();
		return START_STICKY;
	}

	@SuppressLint("NewApi")
	public void service(){ 
		Intent i =new Intent(this,AccountActivity.class);	
		//����������flag��������һ����Ψһ��һ��activity��������棩��������ǰ�ˡ�
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);           
        Notification myNotify = new Notification.Builder(this) 
                                .setSmallIcon(R.drawable.icon) 
                                .setTicker("������һ���µ�����")
                                .setContentTitle("���ι�������:") 
                                .setContentText("5Ԫ")
                                .setContentIntent(pi) 
                                .build();  
        startForeground(Notifyme,myNotify);
	}
	
}
