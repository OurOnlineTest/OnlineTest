package teacher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import tool.ORMTool;
import tool.ZipTool;

public class DownloadAction extends ActionSupport{
	
	private int number;
 

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	private String filename;
 
   

    public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InputStream getTargetFile() throws IOException {
    	
    	//�ȴ�������� 
//    	HttpServletRequest request = ServletActionContext.getRequest(); 
//    	String sourceFilePath = request.getSession().getServletContext().getRealPath("/")+"/upload/answerPaper"; 
//        String zipFilePath = request.getSession().getServletContext().getRealPath("/")+"/filetemps";  
//        String fileName = "answerPaper";  
//        System.out.println(sourceFilePath+"class");
//        System.out.println(zipFilePath);
//        boolean flag = ZipTool.fileToZip(sourceFilePath, zipFilePath, fileName);  
//        if(flag){  
//            System.out.println("�ļ�����ɹ�!");  
//        }else{  
//            System.out.println("�ļ����ʧ��!");  
//        }  
    	
        //����·��
        if(number==1){
//            this.filename="answerPaper.zip";
//            this.filename=new String(this.filename.getBytes("gbk"), "iso-8859-1");
//            //��ȡ��Դ·��
//            return ServletActionContext.getServletContext().getResourceAsStream("/filetemps/answerPaper.zip");
        }
        else if(number==2){
//              this.filename="answerPaper.zip";
//              this.filename=new String(this.filename.getBytes("gbk"), "iso-8859-1");
//              //��ȡ��Դ·��
//              return ServletActionContext.getServletContext().getResourceAsStream("/filetemps/answerPaper.zip");
        }
        return null;
    }

    @Override
    public String execute() throws Exception {
    	System.out.println("teacher.....");
        return SUCCESS;
    }

	
//	//�û������ļ���
//	private String fileName;
//	//������Դ·����Stucts�����ã�
//	private String inputPath;
//	
//	public String getFileName() {
//		return fileName;
//	}
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}
//	public String getInputPath() {
//		return inputPath;
//	}
//	public void setInputPath(String inputPath) {
//		this.inputPath = inputPath;
//	}
//	
//	public String downloadFile() throws Exception{
//		ServletContext context = ServletActionContext.getServletContext();
//		String downloadDir = context.getRealPath("/upload");
//		String downloadFile= context.getRealPath(inputPath);
//		//��ֹ�û����󲻰�ȫ��Դ
//		if(!downloadFile.startsWith(downloadDir)) {
//			return null;
//		}
//		return "download_success";
//	}
//	
//	/*
//	 * ��ȡ��������Դ
//	 */
//	public InputStream getInputStream() throws Exception{
//		String path = inputPath+File.separatorChar+new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
//		return ServletActionContext.getServletContext().getResourceAsStream(path);
//	}
//	
//	/*
//	 * ��ȡ����ʱ�ļ�Ĭ�ϵ��ļ���
//	 */
//	public String  getDownloadFileName() {
//		String downloadFileName = fileName;
//		try {
//			downloadFileName=URLEncoder.encode(downloadFileName,"ISO-8859-1");
//		} catch (UnsupportedEncodingException e) {
//			// TODO: handle exception
//			e.getMessage();
//			e.printStackTrace();
//		}
//		return downloadFileName;
//	}
}
