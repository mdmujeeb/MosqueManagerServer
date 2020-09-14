package com.mujeeb.mosquemanager.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mujeeb.mosquemanager.beans.NamazBean;

public class NamazTimeMapper implements RowMapper<NamazBean> {

	@Override
	public NamazBean mapRow(ResultSet result, int index) throws SQLException {
		return new NamazBean(result.getString("NAME"), result.getString("TIME"));
	}

}
