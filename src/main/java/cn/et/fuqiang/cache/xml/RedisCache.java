package cn.et.fuqiang.cache.xml;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {
	/**
	 * �������������namespace�����Ը�id��ʵ����namespace
	 */
	private String id;
	/**
	 * ʵ�ֵ��ǵ���������   redis
	 * ʹ��Jedis
	 */
	private Jedis jedis;
	/**
	 * ���幹�������������Զ�����ʱ��ʵ�����������Ӧ��ֵ
	 * @param id
	 */
	public RedisCache(final String id){
		this.id=id;
		init();
	}
	/**
	 * ��ʼ�������ӷ���
	 */
	public void init(){
		jedis = new Jedis("localhost",6379);
	}
	/**
	 * ����namespace
	 */
	public String getId() {
		return id;
	}
	/**
	 * ��ֵ����redis��
	 * ������ʹ�����Զ�������л�������ʹ��set(byte[] key,byte[] value)����
	 */
	public void putObject(Object key, Object value) {
		
		if(key==null || value==null){
			return;
		}
		try {
			//��ֵ����
			jedis.set(SerializationUtil.objectToByte(key), SerializationUtil.objectToByte(value));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ��redis�в���ֵ
	 * ������ʹ�����Զ���ķ����л�������ʹ��get(byte[] key)��ֵȥ��
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
	 * ���ڴӻ�����ɾ��ָ���ļ�ֵ
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
	 * ������л���ķ���
	 */
	public void clear() {
		jedis.flushAll();
	}
	
	public int getSize() {
		
		return 0;
	}
	/**
	 * ֱ�ӷ��� ReadWriteLock������ReentrantReadWriteLock()ʵ��
	 */
	public ReadWriteLock getReadWriteLock() {
		return new ReentrantReadWriteLock();
	}

}
