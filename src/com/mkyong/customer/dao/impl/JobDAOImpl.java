package com.mkyong.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mkyong.customer.dao.IJobDAO;
import com.mkyong.customer.model.Job;
import com.mkyong.customer.model.Status;
import com.mkyong.customer.model.Task;

import lombok.NonNull;

public class JobDAOImpl implements IJobDAO {

	private final String JOB_ID = "JOB_ID";
	private final String DESCRIPTION = "DESCRIPTION";
	private final String STATUS = "STATUS";
	private final String CREATED_BY = "CREATED_BY";
	private final String DATE_CREATED = "DATE_CREATED";
	
	private final String TASK_ID = "TASK_ID";
	private final String TASK_DESCRIPTION = "DESCRIPTION";
	private final String PARENT_JOB_ID = "PARENT_JOB_ID";
	private final String TASK_STATUS = "STATUS";

	private final String INSERT_SQL = "INSERT INTO JOB (JOB_ID, DESCRIPTION, STATUS, CREATED_BY, DATE_CREATED) VALUES(?,?,?,?,?)";
	private final String SELECT_JOB_SQL = "SELECT * from JOB where JOB_ID = ?";
	private final String UPDATE_JOB_STATUS_SQL = "update JOB set STATUS = ? where JOB_ID = ?";
	private final String GET_CHILD_TASKS = "SELECT tasks.* from JOB jobs INNER JOIN TASK tasks ON (jobs.JOB_ID = tasks.TASK_ID AND jobs.JOB_ID = ?)";

	private DataSource dataSource;

	public JobDAOImpl(@NonNull DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void createJob(Job job) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT_SQL);
			ps.setString(1, job.getJobId());
			ps.setString(2, job.getDescription());
			ps.setString(3, job.getStatus().name());
			ps.setString(4, job.getCreatedBy());
			ps.setString(5, job.getDateCreated());

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
	public Job getJob(String jobId) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT_JOB_SQL);
			ps.setString(1, jobId);
			ResultSet rs = ps.executeQuery();
			Job job = null;
			if (rs.next()) {
				job = new Job(rs.getString(JOB_ID), rs.getString(DESCRIPTION), rs.getString(STATUS),
						rs.getString(CREATED_BY), rs.getString(DATE_CREATED));

			}
			rs.close();
			ps.close();
			return job;

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
	public List<Task> getChildTasks(String jobId) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(GET_CHILD_TASKS);
			ps.setString(1, jobId);
			ResultSet rs = ps.executeQuery();
			ArrayList<Task> taskList = new ArrayList<Task>();
			Task task = null;
			while (rs.next()) {
				task = new Task(rs.getString(TASK_ID), rs.getString(PARENT_JOB_ID), rs.getString(DESCRIPTION), rs.getString(TASK_STATUS));
				taskList.add(task);
			}
			rs.close();
			ps.close();
			return taskList;

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
	public void updateStatus(String jobId, Status status) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(UPDATE_JOB_STATUS_SQL);
			
			ps.setString(1, status.name());
			ps.setString(2, jobId);
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
	public Status getStatus(String jobId) {
		Job job = getJob(jobId);
		return job.getStatus();
	}

}
