import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentManagementSystem extends JFrame {
    private StudentDao dao;
    private JTextField nameField, emailField, courseField;
    private JTextArea displayArea;

    public StudentManagementSystem() {
        dao = new StudentDao();
        setTitle("Student Management System");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Layout
        setLayout(new BorderLayout(10, 10));

        // Form Panel (Top)
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        formPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email Address:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        formPanel.add(courseField);

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("Refresh List");
        
        formPanel.add(addButton);
        formPanel.add(viewButton);

        add(formPanel, BorderLayout.NORTH);

        // Display Area (Center)
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registered Students List"));
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Button Action: Add Student
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String course = courseField.getText().trim();

                if (name.isEmpty() || email.isEmpty() || course.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Student student = new Student(name, email, course);
                dao.addStudent(student);
                JOptionPane.showMessageDialog(null, "Student added successfully!");

                // Clear fields and refresh display
                nameField.setText("");
                emailField.setText("");
                courseField.setText("");
                loadStudents();
            }
        });

        // Button Action: View/Refresh Students
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStudents();
            }
        });

        // Load data on startup
        loadStudents();
    }

    private void loadStudents() {
        displayArea.setText("");
        List<Student> students = dao.getAllStudents();
        if (students.isEmpty()) {
            displayArea.setText("No students registered yet.");
        } else {
            for (Student s : students) {
                displayArea.append(s.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystem().setVisible(true);
            }
        });
    }
}
