package util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

	public abstract T mapper(ResultSet rs) throws SQLException;
}
