package team.left.shoppingmall.member.dao;

import javax.sql.DataSource;

import team.left.shoppingmall.global.DataSourceContainer;

public class MemberDao {
    private static DataSource dataSource = DataSourceContainer.getDataSource();
}
