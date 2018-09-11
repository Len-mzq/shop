package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Project;
import util.JDBCUtil;

public class ProjectDao {
	List<Project> list = new ArrayList<Project>();

	// 查询所有项目
	public List<Project> search() {
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			ResultSet result = statement.executeQuery("SELECT * from project");
			// 6、对结果集进行处理
			while (result.next()) {
				Project dep = new Project();
				dep.setId(result.getInt("id"));
				dep.setName(result.getString("name"));
				list.add(dep);
			}
			// 7、释放资源
			connection.close();
			statement.close();
			result.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 分页获取项目信息
	public List<Project> searchByPage(int startIndex, int pageSize) {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM department LIMIT ?,?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, startIndex);
			pstat.setInt(2, pageSize);
			rs = pstat.executeQuery();
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}

		return list;
	}
	
	// 获取项目总数
	public int getCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT COUNT(0) FROM project";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				count = rs.getInt("COUNT(0)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}
		return count;
	}
	
	// 查询选择的部门已有的项目
	public List<Project> searchPro(int d_id,int startIndex, int pageSize) {
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			String sql = " select p.id,p.name from ((department as d join r_dep_pro as r on((d.id = r.d_id))) "
					+ "join project as p on((p.id = r.p_id))) where d.id=" + d_id + " limit "+startIndex+","+pageSize+"";

			ResultSet result = statement.executeQuery(sql);
			// 6、对结果集进行处理
			while (result.next()) {
				Project dep = new Project();
				dep.setId(result.getInt("p.id"));
				dep.setName(result.getString("p.name"));
				list.add(dep);
			}
			// 7、释放资源
			connection.close();
			statement.close();
			result.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 查询选择的部门没有的项目
	public List<Project> searchProNo(String depName) {
		List<Project> noList = new ArrayList<Project>();
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			String sql = " select p.id,p.name from project as p "
					+ "where p.id not in (select p.id from ((department as d join r_dep_pro as r on((d.id = r.d_id))) "
					+ "join project as p on((p.id = r.p_id))) " + "where d.name='" + depName + "') ";

			ResultSet result = statement.executeQuery(sql);
			// 6、对结果集进行处理
			while (result.next()) {
				Project dep = new Project();
				dep.setId(result.getInt("p.id"));
				dep.setName(result.getString("p.name"));
				noList.add(dep);
			}
			// 7、释放资源
			connection.close();
			statement.close();
			result.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noList;
	}

	// 条件搜索
	public List<Project> searchByCondition(String condition) {
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			String where = " where 1=1 ";
			if (!condition.equals("")) {
				where += "and name='" + condition + "'";
			}
			String sql = "SELECT * from project" + where;
			ResultSet result = statement.executeQuery(sql);
			// 6、对结果集进行处理
			while (result.next()) {
				Project dep = new Project();
				dep.setId(result.getInt("id"));
				dep.setName(result.getString("name"));
				list.add(dep);
			}
			// 7、释放资源
			connection.close();
			statement.close();
			result.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 根据条件查询数据库project表中的内容
	public List<Project> searchByCondition(Project condition,int startIndex, int pageSize) {
		List<Project> listPro = new ArrayList<Project>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			Statement statement = connection.createStatement();
			String where = "where 1=1 ";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += "and name='"+condition.getName()+"'";
			}
			String sql = "select * from project " + where +" limit "+startIndex+","+pageSize+" ";
			ResultSet result = statement.executeQuery(sql);
			// 6、对结果集进行处理
			while (result.next()) {
				Project pro = new Project();
				pro.setId(result.getInt("id"));
				pro.setName(result.getString("name"));
				listPro.add(pro);
			}
			// 7、释放资源
			connection.close();
			statement.close();
			result.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPro;
	}

	// 根据id查询项目
	public List<Project> search(String id) {
		List<Project> list = new ArrayList<Project>();
	
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from project where id in(" + id + ") ";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}

		return list;
	}
	
	// 根据项目名查项目id
	public int searchId(String name) {
		int id = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = " select id from project where name in('"+name+"') ";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}

		return id;
	}
	
	// 往数据库添加数据
	public void add(Project pro) {
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			String sql = "insert into project (name) values ('" + pro.getName() + "')";
			statement.executeUpdate(sql);

			// 6、对结果集进行处理

			// 7、释放资源
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 修改项目
	public void update(Project pro) {
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			String sql = "update project set name='" + pro.getName() + "' where id=" + pro.getId() + " ";
			statement.executeUpdate(sql);
			// 6、对结果集进行处理

			// 7、释放资源
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 修改项目名(根据项目信息的list)
	public void update(List<Project> list) {
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = JDBCUtil.getConnection();
			for (int i = 0; i < list.size(); i++) {
				Project dep = list.get(i);
				String sql = "update project set name='" + dep.getName() + "'  where id in(" + dep.getId() + ") ";
				pstat = conn.prepareStatement(sql);
				pstat.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtil.close(conn, pstat, null);

		}
	}

	// 往部门中添加项目(已知项目名和部门id)
	public boolean addProToDep(int d_id, int p_id) {
		boolean flag=false;
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			connection.setAutoCommit(false);
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			String sql = "insert into r_dep_pro (d_id,p_id) values (" +d_id+ "," + p_id + ")";
			int rs = statement.executeUpdate(sql);
			// 6、对结果集进行处理
			if(rs>0) {
				flag=true;
			}
			connection.commit();
			// 7、释放资源
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	// 往部门中添加项目(已知项目名和部门id)
	public boolean addProToDep2(int d_id, String p_id) {
		boolean flag=false;
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			connection.setAutoCommit(false);
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			int rs = 0;
			String[] array = p_id.split(",");
			for (int i = 0; i < array.length; i++) {
				String sql= "insert into r_dep_pro (d_id,p_id) values (" +d_id+ "," + array[i] + ")";
				rs = statement.executeUpdate(sql);
			}
			
			// 6、对结果集进行处理
			if(rs>0) {
				flag=true;
			}
			connection.commit();
			// 7、释放资源
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// 删除项目
	public void delete(Project pro) {
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 事务
			connection.setAutoCommit(false);
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			statement.executeUpdate("delete from project where id=" + pro.getId() + " ");
			statement.executeUpdate("delete from r_dep_pro where p_id=" + pro.getId() + " ");
			connection.commit();
			// 6、对结果集进行处理
			// 7、释放资源
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 批量删除项目(根据项目id)
	public void deleteBatch(String id) {
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql1 = "delete from project where id in(" + id + ") ";
			String sql2 = "delete from r_dep_pro where p_id in(" + id + ") ";
			pstat = conn.prepareStatement(sql1);
			pstat = conn.prepareStatement(sql2);
			pstat.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtil.close(conn, pstat, null);

		}
	}
	
	// 删除部门中的项目
	public boolean deletePro(String id, String name) {
		boolean flag = false;
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 事务
			connection.setAutoCommit(false);
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			int rs = statement.executeUpdate("delete from r_dep_pro where p_id in(" + id + ") and d_id=(select id from department where name='"+ name + "')");
			connection.commit();
			// 6、对结果集进行处理
			if(rs>0) {
				flag=true;
			}
			// 7、释放资源
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// 获取按条件查询到的项目总数
	public int getSearchCount(Project condition) {
		int proCount = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			String where = "where 1=1 ";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += "and name='"+condition.getName()+"'";
			}
			conn = JDBCUtil.getConnection();
			String sql = "SELECT COUNT(0) FROM project "+ where;
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				proCount = rs.getInt("COUNT(0)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}
		return proCount;
	}	
	
		// 获取对应部门的项目总数
		public int getSearchCount(int d_id) {
			int proCount = 0;
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			try {
				String where = "where 1=1 ";
				if (d_id!=-1) {
					where += "and d_id="+d_id+"";
				}
				conn = JDBCUtil.getConnection();
				String sql = "SELECT COUNT(0) FROM r_dep_pro "+ where;
				pstat = conn.prepareStatement(sql);
				rs = pstat.executeQuery();
				while (rs.next()) {
					proCount = rs.getInt("COUNT(0)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn, pstat, rs);
			}
			return proCount;
		}	
	
}
