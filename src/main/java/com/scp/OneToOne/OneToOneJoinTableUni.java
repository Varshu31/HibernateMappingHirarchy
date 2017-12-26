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
class Company_JTU{
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	@OneToOne(cascade=CascadeType.ALL)
	
	@JoinTable(name="Comp_Dept_JoinTable")
	Department_JTU department;
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
	public Department_JTU getDepartment() {
		return department;
	}
	public void setDepartment(Department_JTU department) {
		this.department = department;
	}
	public Company_JTU(int id, String c_Name, Department_JTU department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}
	public Company_JTU() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Company_JTU [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
	}
	
	
	
}
@Entity
@Table
class Department_JTU {
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
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
	public Department_JTU(int depid, String depName, String depPlace) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
	}
	public Department_JTU() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Department_JTU [depid=" + depid + ", depName=" + depName + ", depPlace=" + depPlace + "]";
	}
		
	
}

public class OneToOneJoinTableUni {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtility.getSessionFactory().openSession();
        Transaction tx =  session.beginTransaction();
        Department_JTU d1=new Department_JTU(11,"Design", "First_Flour");
        Department_JTU d2=new Department_JTU(12,"Production", "2nd_Flour");
       
        Company_JTU c1=new Company_JTU(1, "MANTruck",d1);
        Company_JTU c2=new Company_JTU(2, "TataMotors",d2);
        session.save(d1);
        session.save(d2);
        session.save(c1);
        session.save(c2);
        System.out.println("Successfully completed");
        tx.commit();
		
	}

}

