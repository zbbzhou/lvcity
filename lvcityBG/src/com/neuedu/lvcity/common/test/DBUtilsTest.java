package com.neuedu.lvcity.common.test;

import static org.junit.Assert.*;

import java.sql.*;

import org.junit.Before;
import org.junit.Test;

import com.neuedu.lvcity.common.DBUtils;

public class DBUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetConnection() {
		Connection  conn=DBUtils.getConnection();
		System.out.println(conn);
	}

}
