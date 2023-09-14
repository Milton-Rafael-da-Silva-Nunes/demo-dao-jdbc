package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.FuncionarioDao;
import model.entities.Departamento;
import model.entities.Funcionario;

public class FuncionarioDaoJDBC implements FuncionarioDao {

	private Connection conn;

	public FuncionarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Funcionario obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO seller(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
			st.setDouble(4, obj.getSalarioBase());
			st.setInt(5, obj.getDepartamento().getId());
			
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1); // Pega a primeira coluna da tabela que e a "ID"
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha afetada!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			
		}
	}

	@Override
	public void update(Funcionario obj) {

	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public Funcionario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT s.*, d.name as departamentoNome FROM seller s "
					+ "INNER JOIN department d on d.id = s.departmentid WHERE s.id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Departamento dep = instaciacaoDepartamento(rs);
				Funcionario obj = instaciacaoFuncionario(rs, dep);
				return obj;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException("Error ao buscar funcionario por departamento: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Funcionario instaciacaoFuncionario(ResultSet rs, Departamento dep) throws SQLException {
		Funcionario obj = new Funcionario();
		obj.setId(rs.getInt("id"));
		obj.setNome(rs.getString("name"));
		obj.setEmail(rs.getString("email"));
		obj.setAniversario(rs.getDate("birthDate"));
		obj.setSalarioBase(rs.getDouble("baseSalary"));
		obj.setDepartamento(dep);
		return obj;
	}

	private Departamento instaciacaoDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("id"));
		dep.setNome(rs.getString("name"));
		return dep;
	}

	@Override
	public List<Funcionario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName " 
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id " 
					+ "ORDER BY Name");

			rs = st.executeQuery();

			List<Funcionario> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();

			while (rs.next()) { 
				
				Departamento dep = map.get(rs.getInt("DepartmentId")); 
				
				if (dep == null) {
					dep = instaciacaoDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Funcionario obj = instaciacaoFuncionario(rs, dep);
				lista.add(obj);
			}
			return lista;

		} catch (SQLException e) {
			throw new DbException("Error ao buscar funcionario por departamento: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Funcionario> findByDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName " 
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id " 
					+ "WHERE DepartmentId = ? " 
					+ "ORDER BY Name");

			st.setInt(1, departamento.getId());
			rs = st.executeQuery();

			List<Funcionario> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();

			while (rs.next()) { 
				
				Departamento dep = map.get(rs.getInt("DepartmentId")); 
				
				if (dep == null) {
					dep = instaciacaoDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Funcionario obj = instaciacaoFuncionario(rs, dep);
				lista.add(obj);
			}
			return lista;

		} catch (SQLException e) {
			throw new DbException("Error ao buscar funcionario por departamento: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
