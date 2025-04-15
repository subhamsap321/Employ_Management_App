package Employ_management_system;

public class Query {
	    public static final String insertQuery = "INSERT INTO employees (name, age, department) VALUES (?, ?, ?)";
	    public static final String selectQuery = "SELECT * FROM employees";
	    public static final String updateQuery = "UPDATE employees SET name = ?, age = ?, department = ? WHERE id = ?";
	    public static final String deleteQuery = "DELETE FROM employees WHERE id = ?";

}
