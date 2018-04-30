package com.kaishengit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.kaishengit.dao.DiskDao;
import com.kaishengit.entity.Disk;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;

public class DiskService {
	DiskDao diskDao = new DiskDao();
	String filePath = Config.get("file.upload.path");
	//增
	/**
	 * 保存上传的文件夹
	 */
	public void saveDiskDir(String name, int pId, int accountId) {
		Disk disk = new Disk();
		disk.setName(name);
		disk.setAccountId(accountId);
		disk.setpId(pId);
		disk.setType(Disk.DISK_TYPE_FOLDER);
		diskDao.save(disk);
	}
	
	/**
	 * 文件上传
	 */
	public void uploadFile(InputStream input, String saveName) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			OutputStream outputStream = new FileOutputStream(new File(filePath, saveName));
			IOUtils.copy(input,outputStream);
			outputStream.flush();
			outputStream.close();
			input.close();
		} catch (IOException e) {
			throw new ServiceException("文件上传失败");
		}
	}

	
	/**
	 * 保存上传的文件
	 */
	public void saveNewFile(Disk disk) {
		// TODO Auto-generated method stub
		diskDao.save(disk);
	}
	
	//删
	/**根据id递归删除文件或文件夹
	 * @param id
	 */
	public void deleteDiskById(int id) {
		Disk disk = diskDao.findDiskById(id);
		recursiveDelete(disk);
		
	}

	/**递归删除文件或文件夹，辅助上一个方法
	 * @param disk
	 */
	private void recursiveDelete(Disk disk) {
		List<Disk> diskList = diskDao.findDiskListByPId(disk.getId());
		for(Disk d : diskList) {
			recursiveDelete(d);
		}
		
		// 删除数据库
		diskDao.deleteDiskById(disk.getId());
		// 删除文件
		if("file".equals(disk.getType())) {
			File file = new File(filePath,disk.getSaveName());
			if(file.exists()) {
				file.delete();
			}
		}
	}

	//改
	/**重命名
	 * @param name
	 * @param id
	 */
	public void updateDiskFile(String name, int id) {
		Disk disk = diskDao.findDiskById(id);
		disk.setName(name);
		diskDao.updateDiskFileName(disk);
		
	}
		
	//查
	/**根据pId查询对应层级的文件和文件夹
	 */
	public List<Disk> findDiskListByPId(int pId) {
		// TODO Auto-generated method stub
		return diskDao.findDiskListByPId(pId);
	}

	/**把pid当做id查找对应的文件或文件夹
	 */
	public Disk findDiskById(int pId) {
		// TODO Auto-generated method stub
		return diskDao.findDiskById(pId);
	}
	
	/**根据md5值查找disk集合
	 */
	public Disk findDiskByMd5(String md5) {
		// TODO Auto-generated method stub
		return diskDao.findDiskByMd5(md5);
	}

}
