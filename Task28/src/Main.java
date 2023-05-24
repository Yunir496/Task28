import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final String FAIL_PATH = "Task28/file/staff.txt";

    public static void main(String[] args) throws IOException {
        List<Employee> employees = loadStaffFromFile();
        employees.sort(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName));
        for (Employee e : employees) {
            System.out.println(e.toString());
        }

    }

    private static List<Employee> loadStaffFromFile() throws IOException {
        List<Employee> employees = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(FAIL_PATH));
        lines.forEach(line -> {
                    String[] fragments = line.split("\t");
                    if (fragments.length == 3) {
                        try {
                            employees.add(new Employee(fragments[0],
                                    Integer.parseInt(fragments[1]),
                                    (new SimpleDateFormat("dd.MM.yyyy")).parse(fragments[2])));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Ошибка");
                    }
                }
        );
        return employees;
    }
}
