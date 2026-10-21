import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private static final String FILE_NAME = "students.txt";

    public StudentDao() {
        File file = new File(FILE_NAME);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            int id = (int) (System.currentTimeMillis() % 10000);
            writer.write(id + "," + student.getName() + "," + student.getEmail() + "," + student.getCourse());
            writer.newLine();
            System.out.println("Student added successfully!");
        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[0]);
                    students.add(new Student(id, parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading students: " + e.getMessage());
        }
        return students;
    }
}
