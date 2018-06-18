package comman;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import AES.AESUtils;
import tool.ORMTool;
import tool.SessionTool;

public class LoginAction extends ActionSupport{
	
	HttpSession msession = ServletActionContext.getRequest().getSession();
	private String inputUsername;
	private String inputPassword;
	private String inputUsertype;
	
	private String result = "log_error";
	
	private String message_debug = "";
	
	private String ip;
	
	public String getInputUsername() {
		return inputUsername;
	}
	public void setInputUsername(String inputUsername) {
		this.inputUsername = inputUsername;
	}
	
	public String getInputPassword() {
		return inputPassword;
	}
	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}

	public String getInputUsertype() {
		return inputUsertype;
	}
	public void setInputUsertype(String inputUsertype) {
		this.inputUsertype = inputUsertype;
	}
	//HttpSession session = ServletActionContext.getRequest().getSession();
	
	//��ȡIP����ѧ��
		public String getClientIP() {

			HttpServletRequest request = ServletActionContext.getRequest();

			String ip = request.getHeader("x-forwarded-for");
			if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			}
			if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
			}
			if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			}

			return ip;
			}
		
	

	@SuppressWarnings("unchecked")
	public String execute()
	{
		
		//��Կ
		String key="666";
		SessionTool sTool = new SessionTool();
		sTool.init(ActionContext.getContext());
		this.message_debug = "��½���ͣ�" + this.inputUsertype + "\n��½�û���" + this.inputUsername + "\n��½���룺" + this.inputPassword;
		
		switch(this.inputUsertype)
		{
			case "����Ա":
//				if(this.inputUsername.equals("admin")&&this.inputPassword.equals("admin"))
//				{
//					this.result = "admin";
//					sTool.setSession(inputUsername);
//				}
//				break;
				ORMTool ormtool0 = new ORMTool();
				ormtool0.initSession();
				String hql0 = "select t.teacherpassword,t.teachernumber,t.isadmin from Teacher as t where t.teachername=?";
				Query query0 = ormtool0.getQuery(hql0);
				
				//����
				String en_inputUsername0=AESUtils.encrypt(key, this.inputUsername);		
				String en_inputPassword0=AESUtils.encrypt(key, inputPassword);
				query0.setString(0, "" + en_inputUsername0);
				//query1.setString(0, "" + this.inputUsername);
				
				List<Object[]> list0 = query0.list();
				for(Object[] obj : list0) {
					if(obj[0].equals(en_inputPassword0)&&obj[2].equals("��"))
					{
						this.result = "admin";
						
						//jiemi
						String de_inputUsername0=AESUtils.decrypt(key, en_inputUsername0);
						String de_inputPassword0=AESUtils.decrypt(key, en_inputPassword0);
						
						
						sTool.setSession(de_inputUsername0,de_inputPassword0,obj[1].toString());
						
						msession.setAttribute("teachernumber", obj[1].toString());
						System.out.println("��½:" + obj[1].toString());
					}
				}
				break;
			case "��ʦ":
				
				ORMTool ormtool1 = new ORMTool();
				ormtool1.initSession();
				String hql1 = "select t.teacherpassword,t.teachernumber from Teacher as t where t.teachername=?";
				Query query1 = ormtool1.getQuery(hql1);
				
				//����
				String en_inputUsername=AESUtils.encrypt(key, this.inputUsername);		
				String en_inputPassword=AESUtils.encrypt(key, inputPassword);
				query1.setString(0, "" + en_inputUsername);
				//query1.setString(0, "" + this.inputUsername);
				
				List<Object[]> list1 = query1.list();
				for(Object[] obj : list1) {
					if(obj[0].equals(en_inputPassword))
					{
						this.result = "teacher";
						
						//jiemi
						String de_inputUsername=AESUtils.decrypt(key, en_inputUsername);
						String de_inputPassword=AESUtils.decrypt(key, en_inputPassword);
						
						
						sTool.setSession(de_inputUsername,de_inputPassword,obj[1].toString(),en_inputUsername);
						
						msession.setAttribute("teachernumber", obj[1].toString());
						System.out.println("��½:" + obj[1].toString());
					}
				}
				break;
			case "ѧ��":
//				ORMTool ormtool2 = new ORMTool();
//				ormtool2.initSession();
//				String hql2 = "select s.studentnumber,s.studenttag from Student as s where s.studentname=?";
//				Query query2 = ormtool2.getQuery(hql2);
//				
//				String en_inputUsername2=AESUtils.encrypt(key, this.inputUsername);		
//				String en_inputPassword2=AESUtils.encrypt(key, inputPassword);
//				query2.setString(0, "" + en_inputUsername2);
//				
//				//query2.setString(0, "" + this.inputUsername);
//				List<Object[]> list2 = query2.list();
//				for(Object[] obj : list2) {
//					if(obj[0].equals(en_inputPassword2))
//					{
//						this.result = "student";
//						
//						//jiemi
//						String de_inputUsername2=AESUtils.decrypt(key, en_inputUsername2);
//						String de_inputPassword2=AESUtils.decrypt(key, en_inputPassword2);
//						
//						sTool.setSession(de_inputUsername2,de_inputPassword2,obj[1].toString());
//						System.out.println("��½:" + obj[1].toString());
//					}
//				}
//				break;
				
				//��ȡѧ�ţ�IP,���Ա��
				ORMTool ormtool2 = new ORMTool();
				ormtool2.initSession();
				String hql2 = "select s.studentnumber,s.studenttag,s.ipAdress from Student as s where s.studentname=?";
				Query query2 = ormtool2.getQuery(hql2);
				//����
				String en_inputUsername2=AESUtils.encrypt(key, this.inputUsername);		
				String en_inputPassword2=AESUtils.encrypt(key, inputPassword);
				query2.setString(0, "" + en_inputUsername2);
				
				//get ip
				ip=getClientIP();
				System.out.println(ip);
				
				//query2.setString(0, "" + this.inputUsername);
				List<Object[]> list2 = query2.list();
				
				/*Studenttag������Ա�exam���ж�Ӧ��exam��status����ʼ״̬����Ե�¼
				 * ͬʱ���Ѿ���ʼ���ⳡ���Ա��д��session��5������
				 * DownloadAction��ȡ��������ҵ�examfileName���޸��������Ӳ��� 
				 * **/
				//��ȡ�ѿ�ʼ�Ŀ��ԵĿ��Ժ��� studenttag�Ƚ�
				ORMTool ormtool4 =new ORMTool();
				ormtool4.initSession();
				String hql4 = "select e.examnumber,e.status from Exam as e where e.status=?";
				Query query4 = ormtool4.getQuery(hql4);
				query4.setString(0, "������");
				List<Object[]> list4 = query4.list();
				for(Object[] obj1 : list4) {
					System.out.println("Exam of Started:"+obj1[0].toString());
				
				
					for(Object[] obj : list2) {
						if(obj[0].equals(en_inputPassword2)&&obj1[0].equals(obj[1])&&(obj[2].equals("ip")||obj[2].equals(ip)))
						{
							this.result = "student";
							
							//����
							String de_inputUsername2=AESUtils.decrypt(key, en_inputUsername2);
							String de_inputPassword2=AESUtils.decrypt(key, en_inputPassword2);
							
							//�������ݿ��е�IP�ֶ�
							ORMTool ormtool3 = new ORMTool();
							ormtool3.initSession();
							String hql3 = "update Student s set s.ipAdress=? where s.studentname=?";
							int updateEntities = ormtool3.getSession().createQuery(hql3)
									.setString(0, ip).setString(1, en_inputUsername2)
									.executeUpdate();
							ormtool3.getSession().getTransaction().commit();
							ormtool3.getSession().close();
							
							System.out.println(updateEntities);
							//sTool.setSession(inputUsername,inputPassword);
							
							sTool.setSession(de_inputUsername2,de_inputPassword2,obj[1].toString(),en_inputUsername2,obj[1].toString());
							System.out.println("��½:" + obj[1].toString());
							
							msession.setAttribute("studenttag", obj[1].toString());
							msession.setAttribute("studentnumber", obj[0].toString());
							
						}
						else {
							this.result="log_error";
						}
					}
				}
				break;
				
		}
		System.out.println(this.message_debug +  "\n���ݿ�����"  +  this.result);
		return this.result;

	}
	
}