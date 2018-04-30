package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kaishengit.entity.Disk;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.EhcacheUtil;


public class DiskDao {
	EhcacheUtil Cache = new EhcacheUtil("disk");
	
	//增
	public void save(Disk disk) {
		System.out.println("增");
		Cache.removeAll();
		String sql = "insert into t_disk (name,pid,type,account_id,file_size, download_count, save_name, md5) values (?,?,?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, disk.getName(), disk.getpId(), disk.getType(), disk.getAccountId(),disk.getFileSize(), disk.getDownloadCount(), disk.getSaveName(),disk.getMd5());
	}
	//删
	public void deleteDiskById(int id) {
		System.out.println("删");
		Cache.removeAll();
		String sql = "delete from t_disk where id = ?";
		DbHelp.executeUpdate(sql, id);
	}
	//改
	public void updateDiskFileName(Disk disk) {
		System.out.println("改");
		Cache.removeAll();
		String sql = "update t_disk set name=? where id=?";
		DbHelp.executeUpdate(sql, disk.getName(),disk.getId());
		
	}
	//查
	public List<Disk> findDiskListByPId(int pId) {
		List<Disk> diskList = (ArrayList<Disk>)Cache.getValue("pId"+pId);
		if (diskList==null) {
			String sql = "select * from t_disk where pid = ? order by type asc";
			diskList = DbHelp.query(sql, new BeanListHandler<>(Disk.class,new BasicRowProcessor(new GenerousBeanProcessor())), pId);
			Cache.setCache("pId"+pId, diskList);
		} 
		
		return diskList;
	}

	public Disk findDiskById(int id) {
		Disk disk = (Disk)Cache.getValue("id"+id);
		if (disk==null) {
			String sql = "select * from t_disk where id = ?";
			disk = DbHelp.query(sql, new BeanHandler<>(Disk.class,new BasicRowProcessor(new GenerousBeanProcessor())), id);
			Cache.setCache("id"+id, disk);
		}
		return disk;
	}

	
	public Disk findDiskByMd5(String md5) {
		Disk disk = (Disk)Cache.getValue("md5"+md5);
		if (disk==null) {
			String sql = "select * from t_disk where md5 = ?";
			disk = DbHelp.query(sql, new BeanHandler<>(Disk.class, new BasicRowProcessor(new GenerousBeanProcessor())), md5);
			Cache.setCache("md5"+md5, disk);
		}
		return disk;
	}



}
