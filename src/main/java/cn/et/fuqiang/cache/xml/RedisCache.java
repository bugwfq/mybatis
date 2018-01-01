package cn.et.fuqiang.cache.xml;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {
	/**
	 * 二级缓存是针对namespace的所以该id其实就是namespace
	 */
	private String id;
	/**
	 * 实现的是第三方缓存   redis
	 * 使用Jedis
	 */
	private Jedis jedis;
	/**
	 * 定义构造器，当容器自动调用时会实例化并传入对应的值
	 * @param id
	 */
	public RedisCache(final String id){
		this.id=id;
		init();
	}
	/**
	 * 初始化的连接方法
	 */
	public void init(){
		jedis = new Jedis("localhost",6379);
	}
	/**
	 * 返回namespace
	 */
	public String getId() {
		return id;
	}
	/**
	 * 将值存入redis中
	 * 方法中使用了自定义的序列化方法，使用set(byte[] key,byte[] value)存入
	 */
	public void putObject(Object key, Object value) {
		
		if(key==null || value==null){
			return;
		}
		try {
			//将值存入
			jedis.set(SerializationUtil.objectToByte(key), SerializationUtil.objectToByte(value));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 从redis中查找值
	 * 方法中使用了自定义的反序列化方法，使用get(byte[] key)将值去除
	 */
	public Object getObject(Object key) {
		if(key!=null){
			try {
				byte[] value = jedis.get(SerializationUtil.objectToByte(key));
				if(value==null){
					return null;
				}
				return SerializationUtil.byteToObject(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 用于从缓存中删除指定的键值
	 */
	public Object removeObject(Object key) {
		if(key!=null){
			try {
				Object value = getObject(key);
				jedis.del(SerializationUtil.objectToByte(key));
				return value;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 清空所有缓存的方法
	 */
	public void clear() {
		jedis.flushAll();
	}
	
	public int getSize() {
		
		return 0;
	}
	/**
	 * 直接返回 ReadWriteLock的子类ReentrantReadWriteLock()实体
	 */
	public ReadWriteLock getReadWriteLock() {
		return new ReentrantReadWriteLock();
	}

}
