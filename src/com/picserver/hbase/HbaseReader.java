package com.picserver.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;

import com.picserver.bean.HdBean;
import com.picserver.bean.LogBean;
import com.picserver.bean.MapfileBean;
import com.picserver.bean.PictureBean;
import com.picserver.bean.SpaceBean;
import com.picserver.bean.UserBean;

/**
 * 读取hbase内容封装到Bean中
 * 
 * @author hadoop
 *
 */
public class HbaseReader {

	HbaseOperation ho = new HbaseOperation();

	/**
	 * 根据rowkey 图片名 读取数据保存在PictureBean
	 * 
	 * @param rowkey图片名
	 * @return 存在则返回Bean，不存在返回null
	 */
	public PictureBean getPictureBean(String rowkey) {
		if (rowkey == null)
			return null;
		PictureBean pb = new PictureBean();
		Result rs = ho.QueryByRowKey("cloud_picture", rowkey);
		if (rs.isEmpty()) {
			// 没有检索到，说明数据库中没有该图片，返回错误信息
			return null;
		} else {
			pb.setName(rowkey);
			for (KeyValue keyValue : rs.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("size")) {
					pb.setSize(val);
				}
				if (v.equals("type")) {
					pb.setType(val);
				}
				if (v.equals("space")) {
					pb.setSpace(val);
				}
				if (v.equals("usr")) {
					pb.setUsr(val);
				}
				if (v.equals("createTime")) {
					pb.setCreateTime(val);
				}
				if (v.equals("path")) {
					pb.setPath(val);
				}
				if (v.equals("status")) {
					pb.setStatus(val);
				}
				if (v.equals("updateTime")) {
					pb.setUpdateTime(val);
				}
				if (v.equals("visitCount")) {
					pb.setVisitCount(val);
				}
				if (v.equals("visitFlow")) {
					pb.setVisitFlow(val);
				}
			}
		}
		return pb;
	}

	/**
	 * 根据图片某一个列值检索表cloud_picture
	 * 
	 * @param family
	 *            列族
	 * @param column
	 *            列
	 * @param value
	 *            值
	 * @return 如果检索到，则返回PictureBean的list，没有则返回null
	 * @throws IOException
	 */
	public List<PictureBean> getPictureBean(String family, String column,
			String value) throws IOException {
		List<PictureBean> list = new ArrayList<PictureBean>();
		ResultScanner rs = ho.QueryByColumn("cloud_picture", family, column,
				value);
		for (Result r : rs) {
			PictureBean pb = new PictureBean();
			pb.setName(new String(r.getRow()));
			for (KeyValue keyValue : r.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("size")) {
					pb.setSize(val);
				}
				if (v.equals("type")) {
					pb.setType(val);
				}
				if (v.equals("space")) {
					pb.setSpace(val);
				}
				if (v.equals("usr")) {
					pb.setUsr(val);
				}
				if (v.equals("createTime")) {
					pb.setCreateTime(val);
				}
				if (v.equals("path")) {
					pb.setPath(val);
				}
				if (v.equals("status")) {
					pb.setStatus(val);
				}
				if (v.equals("updateTime")) {
					pb.setUpdateTime(val);
				}
				if (v.equals("visitCount")) {
					pb.setVisitCount(val);
				}
				if (v.equals("visitFlow")) {
					pb.setVisitFlow(val);
				}
			}
			list.add(pb);
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 检索某用户某时间段上传的图片
	 * 
	 * @param uid
	 *            用户
	 * @param sTime
	 *            起始时间
	 * @param eTime
	 *            结束时间
	 * @return
	 * @throws IOException
	 */
	public List<PictureBean> getLimitPicture(String uid, String sTime,
			String eTime) throws IOException {
		List<PictureBean> list = new ArrayList<PictureBean>();
		ResultScanner rs = ho.QueryLimitPic(uid, sTime, eTime);
		for (Result r : rs) {
			PictureBean pb = new PictureBean();
			pb.setName(new String(r.getRow()));
			for (KeyValue keyValue : r.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("size")) {
					pb.setSize(val);
				}
				if (v.equals("type")) {
					pb.setType(val);
				}
				if (v.equals("space")) {
					pb.setSpace(val);
				}
				if (v.equals("usr")) {
					pb.setUsr(val);
				}
				if (v.equals("createTime")) {
					pb.setCreateTime(val);
				}
				if (v.equals("path")) {
					pb.setPath(val);
				}
				if (v.equals("status")) {
					pb.setStatus(val);
				}
				if (v.equals("updateTime")) {
					pb.setUpdateTime(val);
				}
				if (v.equals("visitCount")) {
					pb.setVisitCount(val);
				}
				if (v.equals("visitFlow")) {
					pb.setVisitFlow(val);
				}
			}
			list.add(pb);
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 根据space和usr检索表cloud_space
	 * 
	 * @param usr
	 * @param space
	 * @return
	 * @throws IOException
	 */
	public List<PictureBean> getPictureBean(String usr, String space)
			throws IOException {
		List<PictureBean> list = new ArrayList<PictureBean>();
		ResultScanner rs = ho.QueryPic(usr, space);
		for (Result r : rs) {
			PictureBean pb = new PictureBean();
			pb.setName(new String(r.getRow()));
			for (KeyValue keyValue : r.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("size")) {
					pb.setSize(val);
				}
				if (v.equals("type")) {
					pb.setType(val);
				}
				if (v.equals("space")) {
					pb.setSpace(val);
				}
				if (v.equals("usr")) {
					pb.setUsr(val);
				}
				if (v.equals("createTime")) {
					pb.setCreateTime(val);
				}
				if (v.equals("path")) {
					pb.setPath(val);
				}
				if (v.equals("status")) {
					pb.setStatus(val);
				}
				if (v.equals("updateTime")) {
					pb.setUpdateTime(val);
				}
				if (v.equals("visitCount")) {
					pb.setVisitCount(val);
				}
				if (v.equals("visitFlow")) {
					pb.setVisitFlow(val);
				}
			}
			list.add(pb);
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 根据rowkey 空间名 读取数据保存在SpaceBean
	 * 
	 * @param rowkey
	 *            空间名
	 * @return 存在则返回SpaceBean，不存在返回null
	 */
	public SpaceBean getSpaceBean(String rowkey) {
		SpaceBean sb = new SpaceBean();
		Result rs = ho.QueryByRowKey("cloud_space", rowkey);
		if (rs.isEmpty()) {
			// 没有检索到，说明数据库中没有该图片，返回错误信息
			return null;
		} else {
			sb.setName(rowkey);
			for (KeyValue keyValue : rs.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("desc")) {
					sb.setDesc(val);
				}
				if (v.equals("cover")) {
					sb.setCover(val);
				}
				if (v.equals("uid")) {
					sb.setUid(val);
				}
				if (v.equals("storage")) {
					sb.setStorage(val);
				}
				if (v.equals("number")) {
					sb.setNumber(val);
				}
				if (v.equals("flow")) {
					sb.setFlow(val);
				}
			}
		}
		return sb;
	}

	/**
	 * 根据空间列值检索表cloud_space
	 * 
	 * @param family
	 *            列族
	 * @param column
	 *            列
	 * @param value
	 *            值
	 * @return 如果检索到，则返回SpaceBean的list，没有则返回null
	 * @throws IOException
	 */
	public List<SpaceBean> getSpaceBean(String family, String column,
			String value) throws IOException {
		List<SpaceBean> list = new ArrayList<SpaceBean>();
		ResultScanner rs = ho.QueryByColumn("cloud_space", family, column,
				value);
		for (Result r : rs) {
			SpaceBean sb = new SpaceBean();
			sb.setName(new String(r.getRow()));
			for (KeyValue keyValue : r.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("desc")) {
					sb.setDesc(val);
				}
				if (v.equals("cover")) {
					sb.setCover(val);
				}
				if (v.equals("uid")) {
					sb.setUid(val);
				}
				if (v.equals("storage")) {
					sb.setStorage(val);
				}
				if (v.equals("number")) {
					sb.setNumber(val);
				}
				if (v.equals("flow")) {
					sb.setFlow(val);
				}
			}
			list.add(sb);
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 根据rowkey 用户名 读取数据保存在UserBean
	 * 
	 * @param rowkey
	 *            用户名uid
	 * @return 如果存在则返回UserBean，不存在返回null
	 */
	public UserBean getUserBean(String rowkey) {
		UserBean ub = new UserBean();
		Result rs = ho.QueryByRowKey("cloud_user", rowkey);
		if (rs.isEmpty()) {
			// 没有检索到，说明数据库中没有该图片，返回错误信息
			return null;
		} else {
			ub.setUid(rowkey);
			for (KeyValue keyValue : rs.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("accType")) {
					ub.setAccType(val);
				}
				if (v.equals("email")) {
					ub.setEmail(val);
				}
				if (v.equals("lastLogin")) {
					ub.setLastLogin(val);
				}
				if (v.equals("website")) {
					ub.setWebsite(val);
				}
				if (v.equals("nickname")) {
					ub.setNickname(val);
				}
				if (v.equals("pwd")) {
					ub.setPwd(val);
				}
				if (v.equals("picNum")) {
					ub.setPicNum(val);
				}
				if (v.equals("totSize")) {
					ub.setTotSize(val);
				}
				if (v.equals("spaceNum")) {
					ub.setSpaceNum(val);
				}
			}
		}
		return ub;
	}

	/**
	 * 根据用户信息检索cloud_user
	 * 
	 * @param family
	 *            列族
	 * @param column
	 *            列
	 * @param value
	 *            值
	 * @return 存在也返回UserBean的List，不存在返回null
	 * @throws IOException
	 */
	public List<UserBean> getUserBean(String family, String column, String value)
			throws IOException {
		List<UserBean> list = new ArrayList<UserBean>();
		ResultScanner rs = ho
				.QueryByColumn("cloud_user", family, column, value);
		for (Result r : rs) {
			UserBean ub = new UserBean();
			ub.setUid(new String(r.getRow()));
			for (KeyValue keyValue : r.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("accType")) {
					ub.setAccType(val);
				}
				if (v.equals("email")) {
					ub.setEmail(val);
				}
				if (v.equals("lastLogin")) {
					ub.setLastLogin(val);
				}
				if (v.equals("website")) {
					ub.setWebsite(val);
				}
				if (v.equals("nickname")) {
					ub.setNickname(val);
				}
				if (v.equals("pwd")) {
					ub.setPwd(val);
				}
				if (v.equals("picNum")) {
					ub.setPicNum(val);
				}
				if (v.equals("totSize")) {
					ub.setTotSize(val);
				}
				if (v.equals("spaceNum")) {
					ub.setSpaceNum(val);
				}
			}
			list.add(ub);
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 根据用户检索日志
	 * 
	 * @param user
	 *            用户id
	 * @return 存在返回LogBean，不存在返回null
	 * @throws IOException
	 */
	public List<LogBean> getLogBean(String user) throws IOException {
		List<LogBean> list = new ArrayList<LogBean>();
		ResultScanner rs = ho.QueryByColumn("cloud_log", "attr", "user", user);
		for (Result r : rs) {
			LogBean lb = new LogBean();
			lb.setLogid(new String(r.getRow()));
			for (KeyValue keyValue : r.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("user")) {
					lb.setUser(val);
				}
				if (v.equals("time")) {
					lb.setTime(val);
				}
				if (v.equals("operation")) {
					lb.setOperation(val);
				}
			}
			list.add(lb);
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 根据用户和时间范围检索日志
	 * 
	 * @param user
	 *            用户
	 * @param min
	 *            起始时间
	 * @param max
	 *            结束时间
	 * @return 如果有返回List，没有返回null
	 * @throws IOException
	 */
	public List<LogBean> getLogBean(String user, String min, String max)
			throws IOException {
		List<LogBean> list = new ArrayList<LogBean>();
		ResultScanner rs = ho.QueryLog(user, min, max);
		for (Result r : rs) {
			LogBean lb = new LogBean();
			lb.setLogid(new String(r.getRow()));
			for (KeyValue keyValue : r.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("user")) {
					lb.setUser(val);
				}
				if (v.equals("time")) {
					lb.setTime(val);
				}
				if (v.equals("operation")) {
					lb.setOperation(val);
				}
			}
			list.add(lb);
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	public List<HdBean> getHdList(String uid) throws IOException {
		List<HdBean> list = new ArrayList<HdBean>();
		ResultScanner rs = ho.QueryByColumn("cloud_hd", "attr", "uid", uid);
		for (Result r : rs) {
			HdBean hb = new HdBean();
			hb.setName(new String(r.getRow()));
			for (KeyValue keyValue : r.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("uid")) {
					hb.setUid(val);
				}
				if (v.equals("createTime")) {
					hb.setCreateTime(val);
				}
				if (v.equals("size")) {
					hb.setSize(val);
				}
				if (v.equals("url")) {
					hb.setUrl(val);
				}
			}
			list.add(hb);
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	public MapfileBean getMapfileBean(String rowkey) {
		MapfileBean mb = new MapfileBean();
		Result rs = ho.QueryByRowKey("cloud_mapfile", rowkey);
		if (rs.isEmpty()) {
			// 没有检索到，说明数据库中没有该图片，返回错误信息
			return null;
		} else {
			mb.setName(rowkey);
			for (KeyValue keyValue : rs.raw()) {
				String v = new String(keyValue.getQualifier());
				String val = new String(keyValue.getValue());
				if (v.equals("uid")) {
					mb.setUid(val);
				}
				if (v.equals("flagNum")) {
					mb.setFlagNum(val);
				}
				if (v.equals("picNum")) {
					mb.setPicNum(val);
				}
			}
			return mb;
		}
	}

}
