package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import util.JDBCUtil;

public class ScoreDao {

	// 查询数据库
	public List<Score> search(int start) {
		List<Score> list = new ArrayList<Score>();
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			String sql = "SELECT s.id as s_id,p.id as p_id ,e.id as e_id , d.`name` as d_name,e.`name`AS e_name,sex,age, p.`name`AS p_name,`value`,grade  \r\n"
					+ "FROM department AS d left JOIN employee AS e  ON e.d_id=d.id \r\n"
					+ "left JOIN r_dep_pro AS r  ON d.id = r.d_id left JOIN project AS p ON r.p_id=p.id \r\n"
					+ "left JOIN score AS s  ON e.id= s.e_id and p.id=s.p_id limit "+start+",6";
			ResultSet result = statement.executeQuery(sql);
			// ResultSet result = statement.executeQuery("SELECT * from v_allTab");
			// 6、对结果集进行处理
			while (result.next()) {
				Score sco = new Score();
				sco.setId(result.getInt("s_id"));
				sco.setValue((Integer) result.getObject("value"));
				sco.setGrade(result.getString("grade"));

				Department dep = new Department();
				dep.setName(result.getString("d_name"));
				sco.setDep(dep);

				Employee emp = new Employee();
				emp.setName(result.getString("e_name"));
				emp.setId(result.getInt("e_id"));
				sco.setEmp(emp);

				Project pro = new Project();
				pro.setName(result.getString("p_name"));
				pro.setId(result.getInt("p_id"));
				sco.setPro(pro);
				list.add(sco);
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

	// 条件搜索
	public List<Score> searchByCondition(Score condition,int start) {
		List<Score> list = new ArrayList<Score>();
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			String where = " where 1=1 ";
			if (!condition.getEmp().getName().equals("")) {
				where += "and e.name='" + condition.getEmp().getName() + "'";
			}
			if (!condition.getPro().getName().equals("")) {
				where += "and p.name='" + condition.getPro().getName() + "'";
			}
			if (!condition.getDep().getName().equals("")) {
				where += "and d.name='" + condition.getDep().getName() + "'";
			}
			if (!condition.getGrade().equals("")) {
				where += "and grade='" + condition.getGrade() + "'";
			}
			String sql = "SELECT * from (department AS d left JOIN employee AS e  ON e.d_id=d.id \r\n"
					+ "left JOIN r_dep_pro AS r  ON d.id = r.d_id left JOIN project AS p ON r.p_id=p.id \r\n"
					+ "left JOIN score AS s  ON e.id= s.e_id and p.id=s.p_id)" + where +"limit "+start+",6";
			// String sql = "SELECT * from v_allTab";
			ResultSet result = statement.executeQuery(sql);
			// 6、对结果集进行处理
			while (result.next()) {
				Score sco = new Score();
				sco.setId(result.getInt("s.id"));
				sco.setValue((Integer) result.getObject("value"));
				sco.setGrade(result.getString("grade"));

				Department dep = new Department();
				dep.setName(result.getString("d.name"));
				sco.setDep(dep);

				Employee emp = new Employee();
				emp.setName(result.getString("e.name"));
				emp.setId(result.getInt("e.id"));
				sco.setEmp(emp);

				Project pro = new Project();
				pro.setName(result.getString("p.name"));
				pro.setId(result.getInt("p.id"));
				sco.setPro(pro);
				list.add(sco);
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

	// 更新绩效
	public void update(Score sco) {

		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			String sql = "update score set value=? where id=?";
			PreparedStatement pstat = connection.prepareStatement(sql);
			pstat.setInt(1, sco.getValue());
			pstat.setInt(2, sco.getId());
			pstat.executeUpdate();
			// 5、执行SQL语句，并得到结果

			// 6、对结果集进行处理
			// 7、释放资源
			connection.close();
			pstat.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 添加新绩效
	public void add(Score sco) {
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			String sql = "insert into score(e_id,p_id,value) values(?,?,?)";
			PreparedStatement pstat = connection.prepareStatement(sql);
			pstat.setInt(1, sco.getEmp().getId());
			pstat.setInt(2, sco.getPro().getId());
			pstat.setInt(3, sco.getValue());
			pstat.executeUpdate();
			// 5、执行SQL语句，并得到结果

			// 6、对结果集进行处理
			// 7、释放资源
			connection.close();
			pstat.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 查询部门信息
	public List<Department> searchDep() {
		List<Department> list = new ArrayList<Department>();
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			ResultSet result = statement.executeQuery(
					"SELECT d.id,d.name,COUNT(e.id) from department as d LEFT JOIN employee as e on d_id=d.id GROUP BY d.id");
			// 6、对结果集进行处理
			while (result.next()) {
				Department dep = new Department();
				dep.setId(result.getInt("d.id"));
				dep.setName(result.getString("d.name"));
				dep.setCount(result.getInt("COUNT(e.id)"));
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

	// 查询项目信息
	public List<Project> searchPro(String dep) {
		List<Project> list = new ArrayList<Project>();
		try {
			// 2、利用反射，加载驱动：class.forName("");
			Class.forName("com.mysql.jdbc.Driver");
			// 3、建立连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			// 4、建立Statement（SQL语句执行器）
			Statement statement = connection.createStatement();
			// 5、执行SQL语句，并得到结果
			String where = " where 1=1 ";
			if (!dep.equals("")) {
				where += "and name='" + dep + "'";
			}
			String sql = "SELECT distinct p.id,p.name from project as p LEFT JOIN r_dep_pro on p.id=p_id WHERE d_id in (select id from department"+where+")";
			ResultSet result = statement.executeQuery(sql);
			// 6、对结果集进行处理
			while (result.next()) {
				Project pro = new Project();
				pro.setId(result.getInt("id"));
				pro.setName(result.getString("name"));
				list.add(pro);
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

	// 获取对应部门的项目总数
	public int getCount(Score condition) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String where = " where 1=1 ";
			if (!condition.getEmp().getName().equals("")) {
				where += "and e.name='" + condition.getEmp().getName() + "'";
			}
			if (!condition.getPro().getName().equals("")) {
				where += "and p.name='" + condition.getPro().getName() + "'";
			}
			if (!condition.getDep().getName().equals("")) {
				where += "and d.name='" + condition.getDep().getName() + "'";
			}
			if (!condition.getGrade().equals("")) {
				where += "and grade='" + condition.getGrade() + "'";
			}
			String sql = "SELECT count(0)  \r\n"
					+ "FROM department AS d left JOIN employee AS e  ON e.d_id=d.id \r\n"
					+ "left JOIN r_dep_pro AS r  ON d.id = r.d_id left JOIN project AS p ON r.p_id=p.id \r\n"
					+ "left JOIN score AS s  ON e.id= s.e_id and p.id=s.p_id"+where;
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
	
	
	
	public void save(List<Score> list) {
		
		for (Score sco : list) {
			if (sco.getId() == 0) {
				add(sco);
			} else {
				update(sco);
			}
		}

	}

}
