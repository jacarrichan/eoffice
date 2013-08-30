package com.cyjt.test.system;

import com.cyjt.core.util.BeanUtil;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanUtilTest {
	private String name;
	private Date createtime;
	private int age;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = this.createtime == null ? "null" : sdf
				.format(this.createtime);
		return "name:" + this.name + " createtime:" + date + " age:" + this.age;
	}

	public static void main(String[] args) throws IllegalAccessException,
			InvocationTargetException {
		BeanUtilTest test1 = new BeanUtilTest();

		test1.setAge(10);
		test1.setCreatetime(new Date());
		test1.setName("john");

		BeanUtilTest test2 = new BeanUtilTest();
		test2.setName("LingMing");
		test2.setAge(11);

		BeanUtil.copyNotNullProperties(test1, test2);

		System.out.println("test1:" + test1.toString());
		System.out.println("test2:" + test2.toString());
	}
}
