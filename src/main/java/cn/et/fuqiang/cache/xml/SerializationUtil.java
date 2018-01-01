package cn.et.fuqiang.cache.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {
	/**
	 * ���л�
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] objectToByte(Object object) throws IOException{
		/*
		 * ʹ��һ��byte�����������������byte����д�뵽�ڴ���   ʹ��out.toByteArray()�����鷵��
		 */
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream obj = new ObjectOutputStream(out);
		obj.writeObject(object);
		return out.toByteArray();
	}
	
	/**
	 * �����л�
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object byteToObject(byte[] data) throws IOException, ClassNotFoundException{
		/*
		 * ʹ��һ��byte�����ȡ��  ����������뵽ByteArrayInputStream����������л���ȥ
		 */
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		ObjectInputStream obj = new ObjectInputStream(inputStream);
		return obj.readObject();
	}

}
