package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import entity.Book;
import library.Library;

public class Test {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
//		Library library = new Library();
//		library.startup();
		Class clazz = Book.class;
		System.out.println(clazz);
		Method method = clazz.getMethod("setName", String.class);
		Book book = new Book();
		method.invoke(book, "¥Ô∑“∆Ê√‹¬Î");
		System.out.println(book.getName());
	}

}
