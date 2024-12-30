package team.left.shoppingmall.product.dao;

import javax.sql.DataSource;

import team.left.shoppingmall.global.DataSourceContainer;

public class ProductDao {
    private static DataSource dataSource = DataSourceContainer.getDataSource();
}
