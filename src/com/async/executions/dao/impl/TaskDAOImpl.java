package com.async.executions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.async.executions.dao.ITaskDAO;
import com.async.executions.model.Job;
import com.async.executions.model.Status;
import com.async.executions.model.Task;

import lombok.NonNull;

public class TaskDAOImpl implements ITaskDAO {
	
	private final String TASK_ID = "TASK_ID";
	private final String DESCRIPTION = "DESCRIPTION";
	private final String PARENT_JOB_ID = "PARENT_JOB_ID";
	private final String STATUS = "STATUS";
	
	private final String INSERT_SQL = "INSERT INTO TASK (TASK_ID, PARENT_JOB_ID, DESCRIPTION, STATUS) VALUES(?,?,?,?)";
	private final String SELECT_SQL = "SELECT * from TASK where TASK_ID = ?";
	private final String UPDATE_STATUS_SQL = "update TASK set STATUS = ? where TASK_ID = ?";
	
	
	private DataSource dataSource;

	public TaskDAOImpl(@NonNull DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void createTask(Task task) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT_SQL);
			ps.setString(1, task.getTaskId());
			ps.setString(2, task.getParentJobId());
			ps.setString(3, task.getDescription());
			ps.setString(4, task.getStatus().name());

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public Task getTask(String taskId) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT_SQL);
			ps.setString(1, taskId);
			ResultSet rs = ps.executeQuery();
			Task task = null;
			if (rs.next()) {
				task = new Task(rs.getString(TASK_ID), rs.getString(PARENT_JOB_ID), rs.getString(DESCRIPTION), rs.getString(STATUS));

			}
			rs.close();
			ps.close();
			return task;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void updateStatus(String taskId, Status status) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(UPDATE_STATUS_SQL);
			ps.setString(1, status.name());
			ps.setString(2, taskId);
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	@Override
	public Status getStatus(String taskId) {
		Task task = getTask(taskId);
		return task.getStatus();
	}

}
