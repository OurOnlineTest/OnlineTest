package bean;

/*
 * ��ʵ����Ӧѧ��
 */

public class Student {
	
	private int studentid;
	private String studentname;//ѧ������
	private String studentnumber;//ѧ��ѧ��
	private String studenttag;//���Ա�ʶ
	private String status;
	private String ipAdress;//��IP
	private String uploadFileName;//ѧ���ϴ��𰸵��ļ���
	private String uploadFileDate;//�ϴ��𰸵�ʱ��
	
	
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getStudentnumber() {
		return studentnumber;
	}
	public void setStudentnumber(String studentnumber) {
		this.studentnumber = studentnumber;
	}
	public String getStudenttag() {
		return studenttag;
	}
	public void setStudenttag(String studenttag) {
		this.studenttag = studenttag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the ipAdress
	 */
	public String getIpAdress() {
		return ipAdress;
	}
	/**
	 * @param ipAdress the ipAdress to set
	 */
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
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
	/**
	 * @return the uploadFileDate
	 */
	public String getUploadFileDate() {
		return uploadFileDate;
	}
	/**
	 * @param uploadFileDate the uploadFileDate to set
	 */
	public void setUploadFileDate(String uploadFileDate) {
		this.uploadFileDate = uploadFileDate;
	}
	

}
