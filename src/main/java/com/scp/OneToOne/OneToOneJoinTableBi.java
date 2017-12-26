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

@Entity
@Table
class Company_JTB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="Company_Depart",joinColumns=@JoinColumn(name="id"),inverseJoinColumns=@JoinColumn(name="depid"))
	Department_JTB department;
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
	public Department_JTB getDepartment() {
		return department;
	}
	public void setDepartment(Department_JTB department) {
		this.department = department;
	}
	public Company_JTB(int id, String c_Name, Department_JTB department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}
	public Company_JTB() {
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
class Department_JTB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	
	@OneToOne(mappedBy="department")
	
	Company_JTB company;

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

	public Company_JTB getCompany() {
		return company;
	}

	public void setCompany(Company_JTB company) {
		this.company = company;
	}

	public Department_JTB(int depid, String depName, String depPlace, Company_JTB company) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
		this.company = company;
	}

	public Department_JTB() {
		super();
		// TODO Auto-generated constructor stub
	}
  
	}
public class OneToOneJoinTableBi {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Department_JTB d1 = new Department_JTB(21, "Design", "Pune", null);
		Company_JTB c1 = new Company_JTB(11, "MANTruck",d1 );
		d1.setCompany(c1);
		
		Department_JTB d2 = new Department_JTB(22, "Engine", "Mumbai", null);
		Company_JTB c2 = new Company_JTB(12, "Tata_Motors",d2);
		d2.setCompany(c2);
		
		session.save(c1);
		session.save(c2);
		
		System.out.println("Successfully completed");
		tx.commit();

		

	}

}
