package utility;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {
	private static DataSource datasource;
	static {
		ComboPooledDataSource cpds = new ComboPooledDataSource("mysql8");
		datasource = cpds;
	}
	public static DataSource getDatasource() {
		return datasource;
	}
}
