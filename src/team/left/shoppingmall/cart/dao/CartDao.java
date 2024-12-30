package team.left.shoppingmall.cart.dao;

import javax.sql.DataSource;

import team.left.shoppingmall.global.DataSourceContainer;

public class CartDao {
    private static DataSource dataSource = DataSourceContainer.getDataSource();
}
