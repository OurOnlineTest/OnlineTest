package teacher;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import AES.AESUtils;
import SystemTest.TempClass;
import bean.Student;
import tool.ExcelReadTool;
import tool.ORMTool;

//�ϴ�Excel���ɹ���д��student��
public class UploadExcelAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private String studenttag;
	
	public String getStudenttag() {
		return studenttag;
	}
	public void setStudenttag(String studenttag) {
		this.studenttag = studenttag;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * @return the uploadContentType
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}
	/**
	 * @param uploadContentType the uploadContentType to set
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}
	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	
	public String execute() throws Exception{
		ActionContext act = ActionContext.getContext();
		//String realpath=ServletActionContext.getServletContext().getRealPath("/upload/"+this.uploadFileName);
		String realpath=ServletActionContext.getServletContext().getRealPath("/upload/excel");
		if(upload!=null)
		{
			System.out.println(realpath);
			
			File file = new File(realpath);
			if(!file.exists())
				file.mkdirs();
			
			//���������ļ�
			File saveFile = new File(file, uploadFileName);
			//ʹ��commons-io��FileUtils�ϴ��ļ�
			try {
				FileUtils.copyFile(upload, saveFile);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("�ļ��ϴ�����");
				act.put("message", "�ϴ�ʧ��");
				return ERROR;
			}
		}
		
		//��ȡ��ǰexamnumber����ȡExcel��д�����ݿ�
		String examnumber=TempClass.temp;
		System.out.println("examnumber in Excel Action:"+examnumber);
		//excel����
		File file = new File(realpath+"/"+uploadFileName);
		ExcelReadTool er = new ExcelReadTool();
		List<Student> list = er.readExcel(file,examnumber);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getStudentname()+"....."+list.get(i).getStudentnumber());
			//д�����ݿ�
				System.out.println("��ʼд�����ݿ�");
			//�Ƚ����ļ���
			String key="666";
			String en_studentname=AESUtils.encrypt(key, list.get(i).getStudentname());
			String en_studentnumber=AESUtils.encrypt(key, list.get(i).getStudentnumber());
			list.get(i).setStudentname(en_studentname);
			list.get(i).setStudentnumber(en_studentnumber);
			//list.get(i).setStudenttag(examnumber);
//			//��һ�¸�ѧ���Ƿ��Ѵ������ݿ�
//			ORMTool ormtoolstudent = new ORMTool();
//	        ormtoolstudent.initSession();
//			String hqlstudent = "select s.studentnumber,s.studenttag from Student as s where s.studentname=?";
//			Query querystudent = ormtoolstudent.getQuery(hqlstudent);
//			querystudent.setString(0, "" + en_studentname);
//			List<Object[]> list0 = querystudent.list();
//			//���������ݿ�
//			if(list0.size()==0)
//			{
//				ORMTool ormtool = new ORMTool("student",list.get(i));
//				ormtool.insert();
//		    }
//			else//�������ݿ�ʱ
//			{  for(Object[] obj0 : list0)
//			   {
//				//20150220180505060534#03
//				    String studenttag=obj0[1].toString();
//				    if(studenttag.substring(0,20).equals(this.studenttag.substring(0, 20)))
//				    {
//				    	ORMTool ormtool = new ORMTool();
//						ormtool.initSession();
//						String hql = "delete Student s where s.studentnumber = :oldId";
//						int deleteEnnity = ormtool.getSession().createQuery(hql)
//								.setString("oldId", en_studentnumber)
//								.executeUpdate();
//						ormtool.getTranscation().commit();
//						ormtool.closeSession();
//									
//
//						for(Object[] obj : list0) {
//							String studenttag2=obj[1]+","+examnumber;
//							
//							System.out.println("ee "+studenttag2);				
//							list.get(i).setStudenttag(studenttag2);
//							ORMTool ormtool2= new ORMTool("student",list.get(i));
//							ormtool2.insert();
//							System.out.println("ok!!!!  "+studenttag);			
//						}
//				    	
//				    }
//				    else
//				    {
//						ORMTool ormtool = new ORMTool("student",list.get(i));
//						ormtool.insert();
//				    }
//			   }
//				
//				
//				
//			}
			
			ORMTool ormtool = new ORMTool("student",list.get(i));
			ormtool.insert();
			
			}
		System.out.println("��ȡExcel");
		
		
		
//		//��ȡ��ǰ�ϴ�ʱ�䲢д�����ݿ�
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
//		String date =df.format(System.currentTimeMillis());
//		System.out.println(date); 
//		
//		HttpSession msession = ServletActionContext.getRequest().getSession();
//		String TeaName=(String) msession.getAttribute("enusername");
//		System.out.println(TeaName);
//		
//		//������ʦ���е�uploadFileName,uploadFileDate
//		ORMTool ormtool1 = new ORMTool();
//		ormtool1.initSession();
//		String hql1 = "update Teacher t set t.uploadFileName=?,t.uploadFileDate=? where t.teachername=?";
//		int updateEntities = ormtool1.getSession().createQuery(hql1)
//				.setString(0, this.uploadFileName).setString(1, date).setString(2, TeaName)
//				.executeUpdate();
//		ormtool1.getSession().getTransaction().commit();
//		//ormtool1.getSession().close();
//		System.out.println(updateEntities);
//		
//		//��ȡexamnumber
//		//HttpSession session = ServletActionContext.getRequest().getSession();
//		//String examnumber = (String) session.getAttribute("thisexamnumber");
//		String examnumber = TempClass.temp;
//		System.out.println(examnumber+"������");
//		
//		//����Exam���е�ExamFileName
//		ORMTool ormtool2 = new ORMTool();
//		ormtool2.initSession();
//		String hql2 = "update Exam e set e.examFileName=? where e.examnumber=?";
//		int updateEntities2 = ormtool2.getSession().createQuery(hql2)
//				.setString(0, this.uploadFileName).setString(1, examnumber)
//				.executeUpdate();
//		ormtool2.getSession().getTransaction().commit();
//		ormtool2.getSession().close();
//		System.out.println(updateEntities2+"hh");
//		
		act.put("message", "�ϴ��ɹ���");
		return INPUT;
	}
}
