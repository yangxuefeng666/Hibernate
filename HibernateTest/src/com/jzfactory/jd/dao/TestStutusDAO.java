package com.jzfactory.jd.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jzfactory.jd.bean.Master;
import com.jzfactory.jd.util.BaseHibernateDAO;

/**
 * ����Hibernate����״̬��ת��
 * 
 * @author yangxuefeng 2016-10-11
 *
 */
public class TestStutusDAO extends BaseHibernateDAO {

	/**
	 * ������ʱ״̬���־�״̬��ת��
	 */
	public void testT2P() {
		Master master = new Master();
		//��ʱ̬
		master.setName("����ʦ");
		master.setSex(1);
		Session session = getSession();
		Transaction trans = getSession().beginTransaction();
		session.save(master);
		trans.commit();
		// �־�̬
		master.setSex(0);
		session.close();
		// ����̬
		master.setName("llll");
		// ��ʱ̬
		master.setId(1000);
	}

	/**
	 * ������ʱ״̬���־�״̬�󣬸��������ύ��
	 */
	public void testT2P2Update() {
         Master master=new Master();
         master.setName("����ʦ");
         master.setSex(1);
     	 Session session = getSession();
		 Transaction trans = session.beginTransaction();
		 session.save(master);
		 master.setName("����ʦ");
		 master.setSex(0);
		 trans.commit();
		 session.close();
		 
	}

	/**
	 * ���Գ־�״̬�޸ĺ��ύ
	 */
	public void testP2Update() {
		 Session session = getSession();
		 Master master=(Master) session.get(Master.class, 3);
		 Transaction trans=getSession().beginTransaction();
		 
		 //�˴�����������
		 session.save(master);
		 
		 master.setSex(1);
		 master.setName("����ʦ");
		 trans.commit();
		 session.close();
	}

	/**
	 * ���־�̬ת��Ϊ����̬������ύ clear �� evict �� session�ر�
	 */
	public void testP2D2Update() {
		 Session session = getSession();
		 Master master=(Master) session.get(Master.class, 2);
		 //���־ö���ת��Ϊ�������
		 session.evict(master);
		 Transaction trans=getSession().beginTransaction();
		 //����̬
		 master.setName("��");
		 trans.commit();
		 session.close();
		

	}

	/**
	 * ���־�̬ת��Ϊ��ʱ̬
	 */
	public void testP2T() {
		 Session session = getSession();
		 //�־�̬
		 Master master=(Master) session.get(Master.class, 3);
		 Transaction trans=getSession().beginTransaction();
		 session.delete(master);
		 master.setName("kkk");
		 trans.commit();
		 session.close();

	}

	/**
	 * ͬ���־û�����
	 */
	public void testRefresh() {
		Session session = getSession();
		Master master=(Master) session.get(Master.class, 2);
		System.out.println("before:"+master.getName());
		Transaction trans=getSession().beginTransaction();
		trans.commit();
		session.refresh(master);
		System.out.println("after:"+master.getName());
		session.close();
	}

	/**
	 * ������̬ת���ɳ־�̬
	 */
	public void testD2P() {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		//��ʱ̬
		Master master = new Master();
		//����̬
		master.setId(1);
		master.setName("�Ž�");
		master.setSex(1);
		session.update(master);
		//�־�̬
		String name=master.getName();
		trans.commit();
	}

	/**
	 * �־�״̬�޸�id (����)
	 */
	public void testP2EditId() {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		Master master=(Master) session.get(Master.class, 5);
		master.setId(50);
        trans.commit();
        session.close();

	}

	/**
	 * ����̬ת��Ϊ��ʱ״̬
	 */
	public void testD2S() {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		//��ʱ̬
		Master master=new Master();
		//����̬
		master.setId(5);
		session.delete(master);
		trans.commit();
		session.close();

	}

	/**
	 * �����ظ��ĳ־û�����(����)
	 */
	public void testDuplicateP() {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		//�־�̬
		Master master=(Master) session.get(Master.class, 4);
		//��ʱ̬
		Master master2=new Master();
		//����̬
		master2.setId(4);
		master2.setName("99999");
		session.update(master2);
		//�־�̬
		System.out.println(master2);
		trans.commit();
		session.close();
	}
	/**
	 * �����ظ��ĳ־û�����
	 */
	public void testRemoveDupli() {
		
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		//�־�̬
		Master master=(Master) session.get(Master.class, 4);
		//��ʱ̬
		Master master2=new Master();
		//����̬
		master2.setId(4);
		master2.setName("99999");
		session.merge(master2);
		//�־�̬
		System.out.println(master2);
		//master.setName("8888");
		//����̬
		master2.setName("444");
		trans.commit();
		session.close();

	}

	public static void main(String[] args) {
		
		TestStutusDAO  dao=new TestStutusDAO();
		//dao.testT2P2Update();
		//dao.testP2Update();
		//dao.testP2D2Update();
		//dao.testP2T();
		//dao.testRefresh();
		//dao.testD2P();
		//dao.testP2EditId();
		//dao.testD2S();
		//dao.testDuplicateP();
		//dao.testRemoveDupli();

	}
}
