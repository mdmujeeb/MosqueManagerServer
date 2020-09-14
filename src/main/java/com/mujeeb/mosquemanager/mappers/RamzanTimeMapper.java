package com.mujeeb.mosquemanager.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mujeeb.mosquemanager.beans.RamzanTimesBean;

public class RamzanTimeMapper implements RowMapper<RamzanTimesBean> {

	@Override
	public RamzanTimesBean mapRow(ResultSet result, int index) throws SQLException {
		return new RamzanTimesBean(result.getString("SEHERI"), result.getString("IFTAR"));
	}

}
