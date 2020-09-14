package com.mujeeb.mosquemanager.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NoticeMapper implements RowMapper<String> {

	@Override
	public String mapRow(ResultSet result, int index) throws SQLException {
		return result.getString("NOTICE");
	}

}
