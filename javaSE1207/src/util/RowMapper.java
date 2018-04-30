package util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {

	public abstract Object mapper(ResultSet rs) throws SQLException;
}
