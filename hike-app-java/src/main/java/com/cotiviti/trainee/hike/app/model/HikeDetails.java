package com.cotiviti.trainee.hike.app.model;

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
import java.time.LocalDate;

@Entity
@Table(name = "hikeDetails")
public class HikeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "location")
	private String location;

	@Column(name = "routes")
	private String routes;

	
	@ManyToOne
	@JoinColumn(name = "employee")
	private Employee employee;
	
	@Basic
	private LocalDate hikeDayDate;
	
	@Basic 
	private LocalDate hikeRequestDate;

	@Transient
	private String hikeCoordinator;

	@Column(name = "if_deleted")
	private String ifDeleted;

//	public HikeDetails() {
//
//	}
//
//	public HikeDetails(Integer id, String name, String location, String routes, String hikeCoordinator) {
//		this.id = id;
//		this.name = name;
//		this.location = location;
//		this.routes = routes;
//		this.hikeCoordinator = hikeCoordinator;
//	}

	
	
	public Integer getId() {
		return id;
	}

	public LocalDate getHikeDayDate() {
		return hikeDayDate;
	}


	public void setHikeDayDate(LocalDate hikeDayDate) {
		this.hikeDayDate = hikeDayDate;
	}


	public LocalDate getHikeRequestDate() {
		return hikeRequestDate;
	}


	public void setHikeRequestDate(LocalDate hikeRequestDate) {
		this.hikeRequestDate = hikeRequestDate;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getHikeCoordinator() {
		return hikeCoordinator;
	}

	public void setHikeCoordinator(String hikeCoordinator) {
		this.hikeCoordinator = hikeCoordinator;
	}

	public String getIfDeleted() {
		return ifDeleted;
	}

	public void setIfDeleted(String ifDeleted) {
		this.ifDeleted = ifDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRoutes() {
		return routes;
	}

	public void setRoutes(String routes) {
		this.routes = routes;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
