package com.scp.OneToOne;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Entity
@Table
class Company_JCB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Comp_Dept_JCB")
	Department_JCB department;
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
	public Department_JCB getDepartment() {
		return department;
	}
	public void setDepartment(Department_JCB department) {
		this.department = department;
	}
	public Company_JCB(int id, String c_Name, Department_JCB department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}
	public Company_JCB() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Company_JCB [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
	}

	
}

@Entity
@Table
class Department_JCB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	
	@OneToOne(cascade=CascadeType.ALL)
	Company_JCB company;

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

	public Company_JCB getCompany() {
		return company;
	}

	public void setCompany(Company_JCB company) {
		this.company = company;
	}

	public Department_JCB(int depid, String depName, String depPlace, Company_JCB company) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
		this.company = company;
	}

	public Department_JCB() {
		super();
		// TODO Auto-generated constructor stub
	}
  
	}

public class OneToOneJoinColumnBid {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub

		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Department_JCB d1 = new Department_JCB(11, "Design", "Pune", null);
		Company_JCB c1 = new Company_JCB(1, "MANTruck",d1 );
		d1.setCompany(c1);
		
		session.save(d1);
		session.save(c1);
		System.out.println("Successfully completed");
		tx.commit();

		
		
	}

}
