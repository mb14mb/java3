package simple_lab3;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
	
	students s = new students();
	
	
	SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session session = sf.openSession(); 
	Transaction t = session.beginTransaction();

	public void insert(int s_id, String s_name, int marks) {
		Session session = sf.openSession(); 
		Transaction t = session.beginTransaction();
		students st = new students();
		s.setS_id(s_id);
		s.setS_name(s_name);
		s.setMarks(marks);
		
		session.save(s);
		t.commit();
	}

	public void delete(int s_id) {
		Session session = sf.openSession(); 
		Transaction t = session.beginTransaction();
    	students st = new students();
    	s.setS_id(s_id);
        
        session.delete(s);
        
        t.commit();
	}
	
	public void update(int s_id, String s_name, int marks) {
		Session session = sf.openSession(); 
		Transaction t = session.beginTransaction();
		students st = new students();
		s.setS_id(s_id);
		s.setS_name(s_name);
		s.setMarks(marks);

		session.saveOrUpdate(s);
        t.commit();
	} 
	    	   
      
       
    
	
	public void display() {
		Session session = sf.openSession(); 
		Transaction t = session.beginTransaction();
		Query q = session.createQuery("from students");
		List l = q.getResultList();
		Iterator it = l.iterator();
		System.out.println("List of students:");
		while (it.hasNext()) {
			students s = (students) it.next();
			System.out.println(s.getS_id()+" "+s.getS_name()+" "+s.getMarks());
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		App aa = new App();
		
		while(true) {
			
			System.out.println("1.insert\n2.update\n3.display\n4.delete\nSelect your choice : ");
			int ch= sc.nextInt();
			
			if(ch==1) {
				System.out.println("Enter student id : ");
				int s_id = sc.nextInt();
				System.out.println("Enter student name : ");
				String s_name = sc.next();
				System.out.println("Enter student marks : ");
				int marks = sc.nextInt();
				aa.insert(s_id, s_name, marks);			
				
			}
			if(ch==2) {
				System.out.println("Enter student id : ");
				int s_id = sc.nextInt();
				System.out.println("Enter student name : ");
				String s_name = sc.next();
				System.out.println("Enter student marks : ");
				int marks = sc.nextInt();
				aa.update(s_id, s_name, marks);	
			}
			if(ch==3) {
				aa.display();
			}
			if(ch==4) {
				System.out.println("Enter student id to delete: ");
				int s_id = sc.nextInt();
				aa.delete(s_id);
			}
		}
	}

}
