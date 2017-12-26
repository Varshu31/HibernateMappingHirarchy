package com.scp.OneToOne;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Entity
@Table
class Company_PKB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	Department_PKB department;
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
	public Department_PKB getDepartment() {
		return department;
	}
	public void setDepartment(Department_PKB department) {
		this.department = department;
	}
	public Company_PKB(int id, String c_Name, Department_PKB department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}
	public Company_PKB() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Company_JTB [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
	}

	
}

@Entity
@Table
class Department_PKB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	
	@OneToOne(mappedBy="department")
	Company_PKB company;

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

	public Company_PKB getCompany() {
		return company;
	}

	public void setCompany(Company_PKB company) {
		this.company = company;
	}

	public Department_PKB(int depid, String depName, String depPlace, Company_PKB company) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
		this.company = company;
	}

	public Department_PKB() {
		super();
		// TODO Auto-generated constructor stub
	}
  
	}
public class OneToOnePrimarykeyBi {

	public static void main(String[] args) throws HibernateException, MyException {
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Department_PKB d1 = new Department_PKB(10, "Design", "Pune", null);
		Company_PKB c1 = new Company_PKB(1, "MANTruck",d1 );
		d1.setCompany(c1);
		d1.setDepid(c1.getId());
		
		//session.save(d1);
		session.save(c1);
		System.out.println("Successfully completed");
		tx.commit();

		
		
	}

}
