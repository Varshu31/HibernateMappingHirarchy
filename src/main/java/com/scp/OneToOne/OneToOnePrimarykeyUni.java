package com.scp.OneToOne;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


@Entity
@Table
class Company_PKJCU{
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	Department_PKJCU department;
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
	public Department_PKJCU getDepartment() {
		return department;
	}
	public void setDepartment(Department_PKJCU department) {
		this.department = department;
	}
	public Company_PKJCU(int id, String c_Name, Department_PKJCU department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}
	
	public Company_PKJCU() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
@Entity
@Table
class Department_PKJCU {
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	public Department_PKJCU(int depid,String depName, String depPlace) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
	}
	public Department_PKJCU() {
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
		return "Department_PKJCU [depid=" + depid + ", depName=" + depName + ", depPlace=" + depPlace + "]";
	}
	
	
}

public class OneToOnePrimarykeyUni {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtility.getSessionFactory().openSession();
        
        Transaction tx =  session.beginTransaction();
        Department_PKJCU d1=new Department_PKJCU(11,"Design", "First_Flour");
        Department_PKJCU d2=new Department_PKJCU(12,"Production", "2nd_Flour");
       
        
        Company_PKJCU c1=new Company_PKJCU(1, "MANTruck",d1);
        Company_PKJCU c2=new Company_PKJCU(2, "TataMotors",d2);
        
        d1.setDepid(c1.getId());
        c1.setDepartment(d1);
        
        d2.setDepid(c2.getId());
        c2.setDepartment(d2);
        
        session.save(c1);
        session.save(c2);
        System.out.println("Successfully completed");
       tx.commit();
		
	}

}
