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
class Company_JCU{
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Dept_ID")
	Department_JCU department;
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
	public Department_JCU getDepartment() {
		return department;
	}
	public void setDepartment(Department_JCU department) {
		this.department = department;
	}
	public Company_JCU(int id, String c_Name, Department_JCU department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}
	public Company_JCU() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Company_JCU [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
	}
	
	

}
@Entity
@Table
class Department_JCU {
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	public Department_JCU(int depid,String depName, String depPlace) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
	}
	public Department_JCU() {
		super();
		// TODO Auto-generated constructor stub
	}
		
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
	public void setDepPlace( String depPlace) {
		this.depPlace = depPlace;
	}
	@Override
	public String toString() {
		return "Department_JCU [depid=" + depid + ", depName=" + depName + ", depPlace=" + depPlace + "]";
	}
	
	
}

public class OneToOneJoinColumnUni {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtility.getSessionFactory().openSession();
        
        Transaction tx =  session.beginTransaction();
        Department_JCU d1=new Department_JCU(11,"Design", "First_Flour");
        Department_JCU d2=new Department_JCU(12,"Production", "2nd_Flour");
       
        Company_JCU c1=new Company_JCU(1, "MANTruck",d1);
        Company_JCU c2=new Company_JCU(2, "TataMotors",d2);
        session.save(c1);
        session.save(c2);
       
        System.out.println("Successfully completed");
       tx.commit();
		
	}

}
