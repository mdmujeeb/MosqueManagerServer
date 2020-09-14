package com.mujeeb.mosquemanager.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.mujeeb.mosquemanager.beans.OccasionBean;

public class OccasionMapper implements RowMapper<OccasionBean> {

	@Override
	public OccasionBean mapRow(ResultSet result, int index) throws SQLException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		return new OccasionBean(result.getString("ID"), result.getDate("OCCASION_DATE"), formatter.format(result.getDate("OCCASION_DATE")), result.getString("DESCRIPTION"));
	}
}
