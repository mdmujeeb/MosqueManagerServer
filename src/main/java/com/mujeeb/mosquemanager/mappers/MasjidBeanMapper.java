package com.mujeeb.mosquemanager.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mujeeb.mosquemanager.beans.MasjidBean;

public class MasjidBeanMapper implements RowMapper<MasjidBean> {

	@Override
	public MasjidBean mapRow(ResultSet result, int index) throws SQLException {
		return new MasjidBean(result.getString("MASJID_ID")
								, result.getString("DESCRIPTION")  
								, result.getString("PASSWORD")
								, result.getString("GPS_LATITUDE")
								, result.getString("GPS_LONGITUDE"));
	}

}
