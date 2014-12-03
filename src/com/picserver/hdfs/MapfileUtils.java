package com.picserver.hdfs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;

public class MapfileUtils {
	public static String hdfsUrl = "hdfs://localhost:9000";
	
	/**
	 * 将小图片打包成mapfile进行存储
	 * @author Jet-Muffin
	 * @param items 文件数组
	 * @param filePath	map文件地址
	 * @throws IOException
	 */
	public static void packageToHdfs(File[] items,String filePath) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(hdfsUrl), conf);
		Path path = new Path(fs.getHomeDirectory(), filePath);

		BytesWritable value = new BytesWritable();
		Text key = new Text();

		MapFile.Writer writer = new MapFile.Writer(conf, fs, path.toString(),
				key.getClass(), value.getClass());
		// 通过writer向文档中写入记录
		for (File item : items) {
			System.out.println(item.getName());
			try {
				String filename = item.getName();
				byte buffer[] = getBytes(item);
				writer.append(new Text(filename), new BytesWritable(buffer));
			} catch (Exception e) {
				System.out.println("Exception MESSAGES = " + e.getMessage());
				e.printStackTrace();
			}
		}
		IOUtils.closeStream(writer);// 关闭write流
	}
	
	/**
	 * 从Mapfile中读取出图片
	 * @param filePath Mapfile文件路径
	 * @param fileName 图片名
	 * @return bytep[] 图片byte数组
	 * @throws IOException
	 */
	public static byte[] readFromHdfs(String filePath, String fileName) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(hdfsUrl), conf);
		Path path = new Path(fs.getHomeDirectory(), filePath);
		Text key = new Text(fileName);
		BytesWritable value = new BytesWritable();
		byte[] data = null;
		try{
			MapFile.Reader reader=new MapFile.Reader(fs,path.toString(),conf);  
			if(reader.seek(key)) {
				reader.get(key, value);
				data = value.get();			
				return data;			
			} else {
				return null;
			}
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 将file对象转化为byte数组
	 * @param file
	 * @return
	 */
	private static byte[] getBytes(File file){  
        byte[] buffer = null;  
        try {  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[fis.available()];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return buffer;  
    }
}
	