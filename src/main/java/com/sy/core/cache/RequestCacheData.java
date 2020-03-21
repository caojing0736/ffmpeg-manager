package com.sy.core.cache;



import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;

public class RequestCacheData {

	private RequestCacheData() {
		
	}
	
	public static RequestCacheData getInstance() {
		return CacheDataHolder.instance;
	}
	
	private static class CacheDataHolder {
		private static RequestCacheData instance = new RequestCacheData(); 
	}
	
	//====================
	// <线程UUID, Map<key, value>>
	private Map<UUID, Map<Short, Object>> cacheData = Maps.newConcurrentMap();
	// 保存当前线程的UUID
	private ThreadLocal<UUID> threadUUID = new ThreadLocal<UUID>();
	//====================
	
	/**
	 * 获取requestDataEnum项对应的值
	 * @param requestDataEnum
	 * @return
	 * @author 
	 */
	public <T> T get(RequestCacheDataItem<T> requestCacheDataEnum) {
		Object value = getData().get(requestCacheDataEnum.getKey());
		if(value == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		T result = (T) value;
		return result;
	}
	
	/**
	 * 设置requestDataEnum项的值为value
	 * @param requestDataEnum
	 * @param value
	 * @author 
	 */
	public <T> void put(RequestCacheDataItem<T> requestCacheDataEnum, T value) {
//		System.out.println("requestCacheDataEnum.getKey(),"+requestCacheDataEnum.getKey()+" getThreadUUID():"+getThreadUUID() +" cacheData.size:"+cacheData.size());
		Map<Short, Object> aa= getData();
		aa.put(requestCacheDataEnum.getKey(), value);
	}
	
	/**
	 * 删除requestDataEnum项的值
	 * @param requestCacheDataEnum
	 * @author 
	 */
	public void remove(RequestCacheDataItem<?> requestCacheDataEnum) {
		getData().remove(requestCacheDataEnum.getKey());
	}
	
	
	/**
	 * 获取当前线程的cache 
	 * @return
	 * @author 
	 */
	private Map<Short, Object> getData() {
		return cacheData.get(getThreadUUID());
	}
	
	private UUID getThreadUUID() {
		return threadUUID.get();
	}
	
	/**
	 * 添加cache
	 * @author 
	 */
	public void setCache() {
		threadUUID.set(UUID.randomUUID());
		Map<Short, Object> data = Maps.newConcurrentMap();
		cacheData.put(getThreadUUID(), data);
	}
	
	/**
	 * 删除cache
	 * @author 
	 */
	public void removeCache() {
		cacheData.remove(getThreadUUID());
		threadUUID.remove();
	}
}
