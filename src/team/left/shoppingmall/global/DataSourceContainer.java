package team.left.shoppingmall.global;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceContainer {
    private static final DataSource dataSource;
    
    static {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static DataSource getDataSource() {
        return dataSource;
    }
}
