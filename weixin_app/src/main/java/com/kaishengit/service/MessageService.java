package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.MessageDao;
import com.kaishengit.entity.Message;

public class MessageService {

	MessageDao messageDao = new MessageDao();
	
	/**
	 * 获取所有消息的集合
	 */
	public List<Message> findMessages(String maxId) {
		return messageDao.findByMaxId(maxId);
	}
}
