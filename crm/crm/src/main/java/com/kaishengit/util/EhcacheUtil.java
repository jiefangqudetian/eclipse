package com.kaishengit.util;

import java.io.Serializable;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class EhcacheUtil {

	private static CacheManager cacheManager = new CacheManager();
	private Ehcache cache;
	
	/**构造方法 必须传缓存名
	 * @param cacheName
	 */
	public EhcacheUtil(String cacheName) {
		this.cache = cacheManager.getEhcache(cacheName);
	}

	
	/**存值方法
	 * @param key
	 * @param value
	 */
	public void setCache(Object key, Object value) {
		Element element = new Element(key, value);
		cache.put(element);
	}
	
	public void setCache(Serializable key, Serializable value) {
		Element element = new Element(key, value);
		cache.put(element);
	}
	
	
	/**根据key键取值
	 * @param key
	 * @return
	 */
	public Object getValue(Object key) {
		Element element = cache.get(key);
		return element==null?null:element.getObjectValue();
	}
	
	public Object getValue(Serializable key) {
		Element element = cache.get(key);
		return element==null?null:element.getObjectValue();
	}
	
	//删除值
	public void remove(Object key) {
		cache.remove(key);
	}
	public void remove(Serializable key) {
		cache.remove(key);
	}
	
	//删除所有
	public void removeAll() {
		cache.removeAll();
	}
	
	
}
