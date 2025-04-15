package Employ_management_system;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmploDao {
    Scanner sc = new Scanner(System.in);
    Connection conn = Db.getConnection();

    public void addEmployee() {
        try {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Enter Department: ");
            String dept = sc.nextLine();

            Employ emp = new Employ();
            emp.setName(name);
            emp.setAge(age);
            emp.setDepartment(dept);

            PreparedStatement ps = conn.prepareStatement(Query.insertQuery);
            ps.setString(1, emp.getName());
            ps.setInt(2, emp.getAge());
            ps.setString(3, emp.getDepartment());
            ps.executeUpdate();

            System.out.println("✅ Employee added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewEmployees() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query.selectQuery);

            List<Employ> empList = new ArrayList<>();

            while (rs.next()) {
                Employ emp = new Employ(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department")
                );
                empList.add(emp);
            }

            System.out.println("\n--- Employee List ---");
            for (Employ emp : empList) {
                System.out.println("ID: " + emp.getId() +
                                   ", Name: " + emp.getName() +
                                   ", Age: " + emp.getAge() +
                                   ", Dept: " + emp.getDepartment());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee() {
        try {
            System.out.print("Enter ID of employee to update: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("Enter new Name: ");
            String name = sc.nextLine();
            System.out.print("Enter new Age: ");
            int age = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Enter new Department: ");
            String dept = sc.nextLine();

            Employ emp = new Employ(id, name, age, dept);

            PreparedStatement ps = conn.prepareStatement(Query.updateQuery);
            ps.setString(1, emp.getName());
            ps.setInt(2, emp.getAge());
            ps.setString(3, emp.getDepartment());
            ps.setInt(4, emp.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee updated successfully.");
            } else {
                System.out.println("❌ Employee not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee() {
        try {
            System.out.print("Enter ID of employee to delete: ");
            int id = sc.nextInt();

            PreparedStatement ps = conn.prepareStatement(Query.deleteQuery);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Employee deleted successfully.");
            } else {
                System.out.println("❌ Employee not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
