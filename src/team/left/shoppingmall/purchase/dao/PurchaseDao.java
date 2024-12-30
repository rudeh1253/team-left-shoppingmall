package team.left.shoppingmall.purchase.dao;

import javax.sql.DataSource;

import team.left.shoppingmall.global.DataSourceContainer;

public class PurchaseDao {
    private static DataSource dataSource = DataSourceContainer.getDataSource();
}
