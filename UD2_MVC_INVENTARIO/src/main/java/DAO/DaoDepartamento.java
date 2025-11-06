package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import POJO.Departamento;
import dao.DaoGenerico;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;

public class DaoDepartamento extends DaoGenerico<Departamento,Integer> {

	/*
	@Override
	public void grabar(Departamento d) throws BusinessException{
		
		// Inserta en la tabla departamento
		
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		Integer id = null;
		
		// Obtenemos la conexión que ha creado la interfaz utilizando 
		// la clase de utiles ConexionJdbc
		
		
		try {
			Connection con = ConexionJdbc.getConnection();
			sql = "insert into departamento (nombre) values (?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, d.getNombre());
			pstmt.executeUpdate();
			// Devuelve el valor de la clave primaria
			rs = pstmt.getGeneratedKeys();
			if(rs.first()) {
				id=rs.getInt(1);
				d.setIdDepartamento(id);
				System.out.println("El id: "+ id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al insertar");
		} finally {
			ConexionJdbc.cerrar(pstmt);
		}	
	}
	
	
	
	@Override
	public void actualizar(Departamento d) throws BusinessException{
		
		// Actualizar la tabla departamento
		
		PreparedStatement pstmt = null;
		String sql = null;
		
		// ResultSet rs = null;
		// Integer id = null;
		
		// Obtenemos la conexión que ha creado la interfaz utilizando 
		// la clase de utiles ConexionJdbc
		
		
		try {
			Connection con = ConexionJdbc.getConnection();
			sql = "update departamento SET nombre = ? where IdDepartamento = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, d.getNombre());
			pstmt.setInt(2, d.getIdDepartamento());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al actualizar");
		} finally {
			ConexionJdbc.cerrar(pstmt);
		}	
	}	
	
	
	
	public Departamento buscarPorID(Integer id) throws BusinessException{
		
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		Departamento result = null;ç
		Integer id = null;
		
		try {
			Connection con = ConexionJdbc.getConnection();
			sql = "select * from departamento where iddepartamento = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.first()) {
				result = new Departamento();
				result.setIdDepartamento(id);
				result.setNombre(rs.getString("nombre"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al actualizar");
		} finally {
			ConexionJdbc.cerrar(pstmt);
		}
		return result;
	}	
	
	*/
	
	public List<Departamento> buscarTodos() throws BusinessException{
		
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		List<Departamento> result = new ArrayList<Departamento>();
		
		try {
			Connection con = ConexionJdbc.getConnection();
			sql = "select * from departamento";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Departamento d = new Departamento();
				d.setIdDepartamento(rs.getInt("iddepartamento"));
				d.setNombre(rs.getString("nombre"));
				result.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al encontrar");
		} finally {
			ConexionJdbc.cerrar(pstmt);
		}
		return result;
	}	
}
