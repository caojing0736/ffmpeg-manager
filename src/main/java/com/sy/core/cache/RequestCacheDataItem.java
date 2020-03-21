package com.sy.core.cache;


import java.util.concurrent.atomic.AtomicInteger;



public class RequestCacheDataItem<T> {
	
	/**
	 * key生成器
	 */
	private static AtomicInteger keyGenerator = new AtomicInteger(Short.MIN_VALUE);
	
	private RequestCacheDataItem() {
		this.key = (short) keyGenerator.getAndIncrement();
	}
	
	private static <T> RequestCacheDataItem<T> newInstance() {
		return new RequestCacheDataItem<T>();
	}

	/**
	 * key, 必须唯一 
	 */
	private short key;
	
	public short getKey() {
		return key;
	}
	
	/**
	 * 获取值
	 * @return
	 * @author 
	 */
	public T get() {
		T value = RequestCacheData.getInstance().get(this);
		return value;
	}
	
	/**
	 * 删除值 
	 * @author 
	 */
	public void remove() {
		RequestCacheData.getInstance().remove(this);
	}
	
	/**
	 * 设置值
	 * @param value
	 * @author 
	 */
	public void set(T value) {
		RequestCacheData.getInstance().put(this, value);
	}
	
	//================================各项数据 start===========================
	/**
	 * 测试数据
	 */
	public static final RequestCacheDataItem<Integer> TEST = newInstance();
	/**
	 * 本次请求的path信息
	 */
	public static final RequestCacheDataItem<String> PATH_INFO = newInstance();
	
	/**
	 * 本次请求数据源编号
	 */
	public static final RequestCacheDataItem<String> DB_HOLDER = newInstance();
	
	/**
	 * 本次请求企业代码
	 */
	public static final RequestCacheDataItem<String> ENTERPRISECODE = newInstance();
	
	/**
	 * 本次请求省代码
	 */
	public static final RequestCacheDataItem<String> PROVINCECODE = newInstance();

	/**
	 * 本次请求的用户token
	 */
	public static final RequestCacheDataItem<String> USERTOKEN = newInstance();

	

	
	//================================各项数据 end===========================
	
}
