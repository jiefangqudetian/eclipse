package com.kaishengit.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.kaishengit.entity.User;

public class Gsontest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User(20,"jack", 25);
		Gson gson = new Gson();
		
		String json1 = gson.toJson(user);
		System.out.println(json1);
		
		List<String> lists = new ArrayList<>();
		lists.add("tom");
		lists.add("jack");
		lists.add("lucy");
		String json2 = gson.toJson(lists);
		System.out.println(json2);
		
		Map<String, String> maps = new HashMap<>();
		maps.put("tom", "12");
		maps.put("jack", "13");
		String json3 = gson.toJson(maps);
		System.out.println(json3);
		
	}

}
