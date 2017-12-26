package com.scp.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.scp.OneToOne.HibernateUtility;
import com.scp.OneToOne.MyException;

@Entity
@Table(name="CompMTMPKB")
class CompanyMTM_PKB {
		@Id
		int id;
		String c_Name;
		@ManyToMany(cascade=CascadeType.ALL)
		@PrimaryKeyJoinColumn
		
		List<DepartmentMTM_PKB> department=new ArrayList<DepartmentMTM_PKB>();

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
		public List<DepartmentMTM_PKB> getDepartment() {
			return department;
		}

		public void setDepartment(List<DepartmentMTM_PKB> department) {
			this.department = department;
		}

		public CompanyMTM_PKB(int id, String c_Name){
			super();
			this.id = id;
			this.c_Name = c_Name;
			//this.department = department;
		}

		public CompanyMTM_PKB() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "CompanyMTM_PKB [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
		}
			
		
		
}

@Entity
@Table(name="DeptMTMPKB")
class DepartmentMTM_PKB {
		@Id
		int depid;
		String depName;
		String depPlace;
		@ManyToMany(mappedBy="department")
		List<CompanyMTM_PKB> company=new ArrayList<CompanyMTM_PKB>();
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
				
		public List<CompanyMTM_PKB> getCompany() {
			return company;
		}
		public void setCompany(List<CompanyMTM_PKB> company) {
			this.company = company;
		}
		public DepartmentMTM_PKB(int depid, String depName, String depPlace) {
			super();
			this.depid = depid;
			this.depName = depName;
			this.depPlace = depPlace;
		}
		public DepartmentMTM_PKB() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "DepartmentMTM_PKB [depid=" + depid + ", depName=" + depName + ", depPlace=" + depPlace + "]";
		}
		
					

}
public class ManyToManyPrimarykeyBi {

	public static void main(String[] args) throws HibernateException, MyException {
		Session session = HibernateUtility.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		DepartmentMTM_PKB d1 = new DepartmentMTM_PKB(11, "Design", "3rd Flour");
		DepartmentMTM_PKB d2 = new DepartmentMTM_PKB(12, "Engine", "3rd Flour");
		

		CompanyMTM_PKB c1 = new CompanyMTM_PKB(1, "Mahindra");
		CompanyMTM_PKB c2 = new CompanyMTM_PKB(2, "TataMotors");
		
		// company1 have multiple department 
		 c1.getDepartment().add(d1);
		 c1.getDepartment().add(d2);
		 
		// department1 have multiple company 
		  d1.getCompany().add(c1);
		  d1.getCompany().add(c2);
		// company2 have multiple department 
		  c2.getDepartment().add(d1);
		  c2.getDepartment().add(d2);
		  
		// department2 have multiple company
		  d2.getCompany().add(c1);
		  d2.getCompany().add(c2);
		
		  session.save(c1);
		  session.save(c2);
		tx.commit();


	}

}
