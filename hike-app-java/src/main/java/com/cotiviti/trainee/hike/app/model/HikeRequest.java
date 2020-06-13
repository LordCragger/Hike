package com.cotiviti.trainee.hike.app.model;

import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "hikeRequest")
public class HikeRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "employee")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "hikeDetails")
	private HikeDetails hikeDetails;

	@Column(name = "if_deleted")
	private String ifDeleted;

	@Transient
	private String hikeName;
	
	@Basic
	private LocalDate hikeRequestDate;

	@Transient
	private String employeeIno;
	

	public LocalDate getHikeRequestDate() {
		return hikeRequestDate;
	}

	public void setHikeRequestDate(LocalDate hikeRequestDate) {
		this.hikeRequestDate = hikeRequestDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public HikeDetails getHikeDetails() {
		return hikeDetails;
	}

	public void setHikeDetails(HikeDetails hikeDetails) {
		this.hikeDetails = hikeDetails;
	}

	public String getIfDeleted() {
		return ifDeleted;
	}

	public void setIfDeleted(String ifDeleted) {
		this.ifDeleted = ifDeleted;
	}

	public String getHikeName() {
		return hikeName;
	}

	public void setHikeName(String hikeName) {
		this.hikeName = hikeName;
	}

	public String getEmployeeIno() {
		return employeeIno;
	}

	public void setEmployeeIno(String employeeIno) {
		this.employeeIno = employeeIno;
	}
	
	

	
}

	


