package com.picserver.servlet.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.picserver.config.SystemConfig;
import com.picserver.hdfs.WriteFile;
import com.picserver.picture.PictureWriter;
import com.picserver.thread.SyncThread;

/**
 * Servlet implementation class UploadQueue
 */
@WebServlet("/UploadQueue")
public class UploadQueue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final long MAX_FILE=1000000;   
	private static final String LOCAL_UPLOAD_ROOT  =  "/upload";
	private static final String HDFS_UPLOAD_ROOT = "/upload";

    public UploadQueue() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("不支持GET");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			response.getWriter().println("没有文件域");
		}else{			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List items = upload.parseRequest(request);			
				Iterator iter = items.iterator();
				PictureWriter PWriter = new PictureWriter();
				String uid = "admin";
				String space = "test";

				//TODO 获取图片空间
					
	            boolean flag = false;
				while(iter.hasNext()) {
						FileItem item = (FileItem)iter.next();			
						flag = PWriter.writePicture(item, uid , space);
				}
				
			    SystemConfig sc = new SystemConfig();
			    final String LocalPath = sc.getSystemPath() + LOCAL_UPLOAD_ROOT + "/"
			        + uid + '/' + space + '/';
			    
			    // 同步线程
			    SyncThread st = new SyncThread(LocalPath,uid , space);
			    st.start();
			    
				if(flag){
					response.setContentType("text/html;charset=gb2312");
					PrintWriter out = response.getWriter();
					out.println("上传成功!");
					response.setStatus(200);
					System.out.println("Upload success!");
				} else {
					response.setContentType("text/html;charset=gb2312");
					PrintWriter out = response.getWriter();
					out.println("上传失败,文件已存在!");					
					response.setStatus(302);
					System.out.println("Upload failed");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
