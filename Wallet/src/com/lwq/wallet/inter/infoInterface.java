package com.lwq.wallet.inter;

import java.util.Vector;

public interface infoInterface {
	public boolean changePass(String oldpassword,String newpassword);  //������������
	public boolean resetInfo(String user,String phone,String email);   //�����û���Ϣ����
	public Vector<String> getInfo();    //��ȡ�û���Ϣ
}
