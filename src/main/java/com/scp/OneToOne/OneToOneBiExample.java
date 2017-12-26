package com.scp.OneToOne;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OneToOneBiExample {

	public static void main(String[] args) throws HibernateException, MyException {
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Department1 d1 = new Department1(12011, "Design", "Pune", null);
		Company1 c1 = new Company1(1111, "MANTruck",d1 );
		d1.setDepid(c1.getId());
		

		session.save(d1);
		//session.save(c1);
		System.out.println("Successfully completed");
		tx.commit();

	}

}

@Entity
@Table
class Company1 {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	@OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name="dep_Id")
	//@JoinTable(name="CDT",joinColumns=@JoinColumn(name="id"),inverseJoinColumns=@JoinColumn(name="depid"))
	 @PrimaryKeyJoinColumn
	Department1 department;

	public Company1(int id, String c_Name, Department1 department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}

	public Company1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getC_Name() {
		return c_Name;
	}

	public void setC_Name(String c_Name) {
		this.c_Name = c_Name;
	}

	public Department1 getDepartment() {
		return department;
	}

	public void setDepartment(Department1 department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Company [c_Id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
	}

}

@Entity
@Table
class Department1 {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	@OneToOne(cascade=CascadeType.ALL)
	// @JoinColumn
	//@JoinTable(name = "CDT1", joinColumns = @JoinColumn(name = "depid"), inverseJoinColumns = @JoinColumn(name = "id"))
	@PrimaryKeyJoinColumn
	Company1 company;

	public int getDepid() {
		return depid;
	}

	public void setDepid(int depid) {
		this.depid = depid;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepPlace() {
		return depPlace;
	}

	public void setDepPlace(String depPlace) {
		this.depPlace = depPlace;
	}

	public void setCompany(Company1 company) {
		this.company = company;
	}

	public Company1 getCompany() {
		return company;
	}

	public Department1(int depid, String depName, String depPlace, Company1 company) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
		this.company = company;
	}

	public Department1() {
		super();
		// TODO Auto-generated constructor stub
	}

}
