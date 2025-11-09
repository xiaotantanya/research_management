package xyz.management.project.library.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import xyz.management.project.library.enumUtil.ItemType;

@MappedTypes(ItemType.class)
public class ItemTypeHandler extends BaseTypeHandler<ItemType>{

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ItemType parameter, JdbcType jdbcType) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ItemType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return columnValue == null ? null : ItemType.fromValue(columnValue); // 调用你的静态方法
    }

    @Override
    public ItemType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return columnValue == null ? null : ItemType.fromValue(columnValue);
    }

    @Override
    public ItemType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return columnValue == null ? null : ItemType.fromValue(columnValue);
    }
    
}
