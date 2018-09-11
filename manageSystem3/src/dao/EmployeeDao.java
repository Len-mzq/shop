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
import entity.Employee;
import util.JDBCUtil;

public class EmployeeDao {
	List<Employee> list = new ArrayList<Employee>();

	// 获取员工信息
	public List<Employee> search() {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from employee";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}

		return list;
	}

	// 获取所有部门信息
	public List<Department> searchDepartment() {
		List<Department> depList = new ArrayList<Department>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from department";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setCount(rs.getInt("emp_count"));
				depList.add(dep);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}

		return depList;
	}
	
	// 根据id查询数据库的数据
	public List<Employee> search(String id) {
		List<Employee> list = new ArrayList<Employee>();
	
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select e.*,d.name,emp_count from employee as e left join department as d on d_id=d.id  where e.id in(" + id + ") ";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("e.id"));
				emp.setName(rs.getString("e.name"));
				emp.setSex(rs.getString("e.sex"));
				emp.setAge(rs.getInt("e.age"));

				Department dep = new Department();
				dep.setId(rs.getInt("e.d_id"));
				dep.setName(rs.getString("d.name"));
				
				emp.setDep(dep);
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}

		return list;
	}
	
		// 根据条件查询数据库Employee表中的内容
		public List<Employee> searchByCondition(Employee condition,int startIndex, int pageSize) {
			List<Employee> listEmp = new ArrayList<Employee>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
				Statement statement = connection.createStatement();
				String where = "where 1=1 ";
				if (condition.getName()!=null&&!condition.getName().equals("")) {
					where += "and e.name='"+condition.getName()+"'";
				}
				if (condition.getSex()!=null&&!condition.getSex().equals("")) {
					where += "and sex='" + condition.getSex() + "' ";
				}
				if (condition.getAge() != -1) {
					where += "and age=" + condition.getAge();
				}
//				if (condition.getDep().getId()!=-1) {
//					where += " and d_id=" + condition.getDep().getId();
//				}
				if (condition.getDep().getName()!=null&&!condition.getDep().getName().equals("")) {
					where += " and d.name='"+condition.getDep().getName()+"'";
				}
				String sql = "select e.*,d.name,emp_count from employee as e left join department as d on d_id=d.id " + where +" limit "+startIndex+","+pageSize+" ";
				ResultSet result = statement.executeQuery(sql);
				// 6、对结果集进行处理
				while (result.next()) {
					Employee emp = new Employee();
					emp.setId(result.getInt("e.id"));
					emp.setName(result.getString("e.name"));
					emp.setSex(result.getString("e.sex"));
					emp.setAge(result.getInt("e.age"));
					emp.setPic(result.getString("e.picture"));
					
					Department dep = new Department();
					dep.setId(result.getInt("e.d_id"));
					dep.setName(result.getString("d.name"));
					
					emp.setDep(dep);
					listEmp.add(emp);
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
			return listEmp;
		}

		// 根据部门名查部门id
		public int searchID(String department) {
			Integer id = -1;
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			try {
				conn = JDBCUtil.getConnection();
				String where = "where 1=1 ";
				if (department!=null && !department.equals("")) {
					where += "and name='"+department+"'";
					String sql = "select id from department " + where;
					pstat = conn.prepareStatement(sql);
					//pstat.setString(1, department);
					rs = pstat.executeQuery();
					while (rs.next()) {
						id = rs.getInt("id");
					}
				}else {
					id = -1;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn, pstat, rs);
			}

			return id;
		}
		
	// 往数据库添加数据
	public void add(Employee emp, int id) {
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			statement = conn.createStatement();
			String sql = "insert into employee (name,sex,age,d_id,picture) values ('" + emp.getName()
			+ "','" + emp.getSex() + "','" + emp.getAge() + "','" + id + "','" + emp.getPic() + "')";
			
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtil.close(conn, null, null);

		}
	}

	// 删除数据库的数据
	public void deleteBatch(String id) {
		Connection conn = null;
		PreparedStatement pstat = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from employee where id in(" + id + ") ";
			pstat = conn.prepareStatement(sql);
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

	// 修改数据库的数据
	public void update(Employee emp) {
		Connection conn = null;
		PreparedStatement pstat = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "update employee set name='" + emp.getName() + "' ,sex='" + emp.getSex() + "' ,age="
					+ emp.getAge() + " ,d_id='" + emp.getD_id() + "' where id=" + emp.getId() + " ";
			pstat = conn.prepareStatement(sql);
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

	// 修改数据库的数据(把不同的id，修改成统一的数据)
	public void update(String ids, Employee emp) {
		Connection conn = null;
		PreparedStatement pstat = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "update employee set name='" + emp.getName() + "' ,sex='" + emp.getSex() + "' ,age="
					+ emp.getAge() + " where id in(" + ids + ") ";
			pstat = conn.prepareStatement(sql);
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

	// 修改数据库的数据(根据员工信息的list)
	public void update(List<Employee> list) {
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = JDBCUtil.getConnection();
			for (int i = 0; i < list.size(); i++) {
				Employee emp = list.get(i);
				String sql = "update employee set name='" + emp.getName() + "' ,sex='" + emp.getSex() + "' ,age="
						+ emp.getAge() + " ,d_id=" + emp.getD_id() + " where id in(" + emp.getId() + ") ";
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

	// 分页获取员工信息
	public List<Employee> searchByPage(int startIndex, int pageSize) {

		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM employee LIMIT ?,?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, startIndex);
			pstat.setInt(2, pageSize);
			rs = pstat.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstat, rs);
		}

		return list;
	}

	// 获取员工总数
	public int getCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT COUNT(0) FROM employee";
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

		// 获取按条件查询到的员工总数
		public int getSearchCount(Employee condition) {
			int count = 0;
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			try {
				String where = "where 1=1 ";
				if (condition.getName()!=null&&!condition.getName().equals("")) {
					where += "and name='"+condition.getName()+"'";
				}
				if (condition.getSex()!=null&&!condition.getSex().equals("")) {
					where += "and sex='" + condition.getSex() + "'";
				}
				if (condition.getAge() != -1) {
					where += "and age=" + condition.getAge();
				}
				if (condition.getD_id()!=-1) {
					where += " and d_id=" + condition.getD_id();
				}
				conn = JDBCUtil.getConnection();
				String sql = "SELECT COUNT(0) FROM employee "+ where;
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
	
}
