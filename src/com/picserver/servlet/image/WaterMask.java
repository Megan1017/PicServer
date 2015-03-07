package com.picserver.servlet.image;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.picserver.config.SystemConfig;
import com.picserver.hdfs.HdfsUtil;
import com.picserver.hdfs.MapfileUtils;
import com.picserver.picture.PictureReader;
import com.picserver.picture.PictureUtil;
import com.picserver.picture.PictureUtils;
import com.picserver.utils.FileUtils;

/**
 * Servlet implementation class WaterMask
 */
@WebServlet("/WaterMask")
public class WaterMask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private byte[] outbuffer;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaterMask() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String imageName = request.getParameter("image");
		String uid = request.getParameter("uid");
		String MaskType = request.getParameter("type");
		int offsetX = Integer.parseInt(request.getParameter("offsetX"));
		int offsetY = Integer.parseInt(request.getParameter("offsetY"));
		PictureReader PReader = new PictureReader();
		String format = FileUtils.getFileExtension(imageName);
		
		try {

			if (MaskType.equals("image")) {
				int dissolve = Integer.parseInt(request.getParameter("dissolve"));
				int width = Integer.parseInt(request.getParameter("width"));
				int height = Integer.parseInt(request.getParameter("height"));
				String logo = request.getParameter("logo");
				String logoPath = SystemConfig.getImagePath() + logo + "&uid=" + uid;
				byte[] buffer = PReader.readPicture(imageName, uid);

				PictureUtil image = new PictureUtil(buffer,format);
				 outbuffer = image.imgWaterMask(logoPath,width,height,offsetX,offsetY,dissolve);
			}

			if (MaskType.equals("text")) {
				String text = request.getParameter("text");
				String color = request.getParameter("color");
				text = new String(text.getBytes("iso-8859-1"), "utf-8");
				int fontsize = Integer.parseInt(request
						.getParameter("fontsize"));
				byte[] buffer = PReader.readPicture(imageName, uid);
				PictureUtil image = new PictureUtil(buffer,format);
				outbuffer = image.textWaterMask(text, fontsize, color, offsetX,
						offsetY);
			}

			if (outbuffer != null) {
				OutputStream output = response.getOutputStream();// 得到输出流
				InputStream imageIn = new ByteArrayInputStream(outbuffer);
				BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
				BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
				byte data[] = new byte[4096];// 缓冲字节数
				int size = 0;
				size = bis.read(data);
				while (size != -1) {
					bos.write(data, 0, size);
					size = bis.read(data);
				}
				bis.close();
				bos.flush();// 清空输出缓冲流
				bos.close();
				output.close();
			} else {
				PrintWriter out = response.getWriter();
				out.println("Please input the correct parameter.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
