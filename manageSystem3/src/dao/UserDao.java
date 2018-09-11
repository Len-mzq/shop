package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCUtil;

public class UserDao {
	public boolean search(String username, String password) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from user where username=? and password=? ";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, username);
			pstat.setString(2, password);
			rs = pstat.executeQuery();
			if (rs.next()) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}
		return flag;
	}

	public boolean searchUserName(String username) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from user where username=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, username);
			rs = pstat.executeQuery();
			if (rs.next()) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}
		return flag;
	}

	public String searchTime(String username) {
		String time = null;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select time from user where username=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, username);
			rs = pstat.executeQuery();
			if (rs.next()) {
				time = rs.getString("time");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}
		return time;
	}

	public boolean add(String username, String password, String time) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		int rs = -1;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into user (username,password,time) value(?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, username);
			pstat.setString(2, password);
			pstat.setString(3, time);
			rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, null);
		}
		return flag;
	}
}
