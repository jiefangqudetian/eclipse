package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BeanRowMapper<T> implements RowMapper<T> {

	private Class<T> clazz;
	
	 public BeanRowMapper(Class<T> clazz) {
		this.clazz = clazz;
	}
	@Override
	public T mapper(ResultSet rs) throws SQLException {
		
		T object = null;
		try {
			object = clazz.newInstance();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String label = rsmd.getColumnLabel(i);
				String methodName = "set" + label.substring(0, 1).toUpperCase() + label.substring(1);
				Method[] methods = clazz.getMethods();
				for (Method method : methods) {
					if(method.getName().equals(methodName)) {
						method.invoke(object, rs.getObject(i));
						break;
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return object;
		
	}

}
