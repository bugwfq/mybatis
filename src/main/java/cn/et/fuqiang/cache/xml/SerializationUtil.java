package cn.et.fuqiang.cache.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {
	/**
	 * 序列化
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] objectToByte(Object object) throws IOException{
		/*
		 * 使用一个byte数组输出流将数据以byte数组写入到内存中   使用out.toByteArray()将数组返回
		 */
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream obj = new ObjectOutputStream(out);
		obj.writeObject(object);
		return out.toByteArray();
	}
	
	/**
	 * 反序列化
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object byteToObject(byte[] data) throws IOException, ClassNotFoundException{
		/*
		 * 使用一个byte数组读取流  ，将数组读入到ByteArrayInputStream流中最后反序列化出去
		 */
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		ObjectInputStream obj = new ObjectInputStream(inputStream);
		return obj.readObject();
	}

}
