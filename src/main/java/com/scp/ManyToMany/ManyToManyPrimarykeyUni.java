package com.scp.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.scp.OneToOne.HibernateUtility;
import com.scp.OneToOne.MyException;

@Entity
@Table
class CompanyMTM_PKU {
		@Id
		int id;
		String c_Name;
		@ManyToMany(cascade=CascadeType.ALL)
		@PrimaryKeyJoinColumn
		
		List<DepartmentMTM_PKU> department=new ArrayList<DepartmentMTM_PKU>();

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
		public List<DepartmentMTM_PKU> getDepartment() {
			return department;
		}

		public void setDepartment(List<DepartmentMTM_PKU> department) {
			this.department = department;
		}

		public CompanyMTM_PKU(int id, String c_Name){
			super();
			this.id = id;
			this.c_Name = c_Name;
			//this.department = department;
		}

		public CompanyMTM_PKU() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "CompanyMTM_PKU [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
		}
			
		
		
}

@Entity
@Table
class DepartmentMTM_PKU {
		@Id
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
		public DepartmentMTM_PKU(int depid, String depName, String depPlace) {
			super();
			this.depid = depid;
			this.depName = depName;
			this.depPlace = depPlace;
		}
		public DepartmentMTM_PKU() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "DepartmentMTM_PKU [depid=" + depid + ", depName=" + depName + ", depPlace=" + depPlace + "]";
		}
		
					
}


public class ManyToManyPrimarykeyUni {

	public static void main(String[] args) throws HibernateException, MyException {
		Session session = HibernateUtility.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		DepartmentMTM_PKU d1 = new DepartmentMTM_PKU(11, "Design", "3rd Flour");
		DepartmentMTM_PKU d2 = new DepartmentMTM_PKU(12, "Engine", "3rd Flour");
		DepartmentMTM_PKU d3 = new DepartmentMTM_PKU(13, "Production", "4th Flour");

		CompanyMTM_PKU c1 = new CompanyMTM_PKU(1, "Mahindra");
		CompanyMTM_PKU c2 = new CompanyMTM_PKU(2, "TataMotors");
		
		// company1 have multiple department 
		 c1.getDepartment().add(d1);
		 c1.getDepartment().add(d2);
		  
		// company2 have multiple department 
		  c2.getDepartment().add(d1);
		  c2.getDepartment().add(d2);
		  c2.getDepartment().add(d3);
			
		session.save(c1);
		session.save(c2);
		tx.commit();

	}

}
