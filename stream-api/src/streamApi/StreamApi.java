package streamApi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;

public class StreamApi {

    static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(
                new Employee("Mert", "Murat", 5000.0, Arrays.asList("Project 1", "Project 2"))
        );
        employees.add(
                new Employee("Ali", "Töre", 6000.0, Arrays.asList("Project 1", "Project 3"))
        );
        employees.add(
                new Employee("Veli", "Yılmaz", 5500.0, Arrays.asList("Project 3", "Project 2"))
        );
        employees.add(
                new Employee("Ahmet", "Kaya", 7300.0, Arrays.asList("Project 3", "Project 4"))
        );

    }

    public static void main(String[] args) {
        // Stream.of(employees);

        System.out.println("--------------------------------------------------------------------");
        System.out.println("[FOREACH]");

        //  Bu kod parçacığı, bir "employees" isimli nesnenin stream'ini kullanarak her bir çalışanı yazdırmaktadır.

//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }

//        Iterator<Employee> iterator = employees.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

        employees.stream()
                .forEach(employee -> System.out.println(employee));

        System.out.println("--------------------------------------------------------------------");
        System.out.println("[MAP and COLLECT]");

        // toSet() te kullanılanilir.

        /*
        Bu kod parçacığı, "employees" isimli bir List nesnesinde bulunan çalışanların maaşlarını %10 arttırarak yeni bir List nesnesi oluşturmaktadır.
         */

//        List<Employee> increasedSal = new ArrayList<>();
//        for (Employee employee : employees) {
//            Employee updatedEmployee = new Employee(
//                    employee.getFirstName(),
//                    employee.getLastName(),
//                    employee.getSalary() * 1.10,
//                    employee.getProjects()
//            );
//            increasedSal.add(updatedEmployee);
//        }
//        System.out.println(increasedSal);

        List<Employee> increasedSal = employees.stream()
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects()
                ))
                .collect(Collectors.toList());
        increasedSal.forEach(employee -> System.out.println(employee));

        System.out.println("--------------------------------------------------------------------");
        System.out.println("[FILTER]");

        /*
        Bu kod parçacığı, "employees" isimli bir List nesnesinde bulunan çalışanlar arasından maaşı 9000.0'dan fazla olan
        ilk çalışanın maaşını %10 arttırarak yeni bir Employee nesnesi oluşturmaktadır.
         */

//        Employee firstEmployee = null;
//        for (Employee employee : employees) {
//            if (employee.getSalary() > 9000.0) {
//                Employee updatedEmployee = new Employee(
//                        employee.getFirstName(),
//                        employee.getLastName(),
//                        employee.getSalary() * 1.10,
//                        employee.getProjects()
//                );
//                firstEmployee = updatedEmployee;
//                break;
//            }
//        }
//        System.out.println(firstEmployee);

        Employee firstEmployee = employees
                .stream()
                .filter(employee -> employee.getSalary() >= 5000.0)
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects()
                ))
                .findFirst()
                .orElse(null);
        System.out.println(firstEmployee);

        System.out.println("--------------------------------------------------------------------");
        System.out.println("[FLATMAP]");

        /*
        Bu kod parçacığı, "employees" isimli bir List nesnesinde bulunan çalışanların projelerini
         bir dizi halinde alır, dizileri düzleştirir ve projeleri virgül ile birleştirir.
         */

//        StringBuilder projects = new StringBuilder();
//        for (Employee employee : employees) {
//            List<String> employeeProjects = employee.getProjects();
//            for (String project : employeeProjects) {
//                projects.append(project);
//                projects.append(",");
//            }
//        }
//// remove last ","
//        if (projects.length() > 0) {
//            projects.deleteCharAt(projects.length() - 1);
//        }
//        System.out.println(projects);

        String projects = employees
                .stream()
                .map(employee -> employee.getProjects())
                .flatMap(strings -> strings.stream())
                .collect(Collectors.joining(","));

        System.out.println(projects);

        System.out.println("--------------------------------------------------------------------");
        System.out.println("[SHORT CIRCUIT]");

        /*
            Bu kod parçacığı, "employees" isimli bir List nesnesinde bulunan çalışanların ilk 2 elemanını atlar ve sonraki elemanı alır.
         */

//        List<Employee> shortCircuit = new ArrayList<>();
//        int i=0;
//        for (Employee employee : employees) {
//            if(i==0){
//                i++;
//                continue;
//            }
//            if(i==1){
//                shortCircuit.add(employee);
//                break;
//            }
//            i++;
//        }
//        System.out.println(shortCircuit);

        List<Employee> shortCircuit =
                employees
                        .stream()
                        .skip(1)
                        .limit(1)
                        .collect(Collectors.toList());
        System.out.println(shortCircuit);

        System.out.println("--------------------------------------------------------------------");
        System.out.println("[FINITE DATA]");

        /*
        Bu kod parçacığı Java dilinde yazılmış ve kullanıcıya 5 tane rastgele sayı üretiyor.
         */
//        for (int i = 0; i < 5; i++) {
//            double randomValue = Math.random();
//            System.out.println(randomValue);
//        }

        Stream.generate(Math::random)
                .limit(5)
                .forEach(value -> System.out.println(value));


        System.out.println("--------------------------------------------------------------------");
        System.out.println("[SORTİNG]");

        /*
        Bu kod parçacığı Java dilinde yazılmış ve kullanıcıya "employees" adlı bir liste içindeki çalışanların adlarına göre sıralı olarak döndürür.
         */
//        System.out.println("--------------------------------------------------------------------");
//        System.out.println("[SORTİNG YONTEM-1]");
//        Collections.sort(employees, new Comparator<Employee>() {
//            @Override
//            public int compare(Employee o1, Employee o2) {
//                return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
//            }
//        });
//
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }

//        System.out.println("--------------------------------------------------------------------");
//        System.out.println("[SORTİNG YONTEM-2]");
//        Collections.sort(employees, (o1, o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName()));
//        System.out.println(employees);

//        System.out.println("--------------------------------------------------------------------");
        List<Employee> sortingEmployees = employees
                .stream()
                .sorted((o1, o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName()))
                .collect(Collectors.toList());
        sortingEmployees.forEach(employee -> System.out.println(employee));

        System.out.println("--------------------------------------------------------------------");
        System.out.println("[MIN or MAX]");

        /*
        Bu kod parçacığı, "employees" listesindeki tüm çalışanlar arasından maaşları en yüksek olan çalışanı bulmak için kullanılıyor.
         */

//        Employee highestPaid = employees.get(0);
//        for (int i = 1; i < employees.size(); i++) {
//            if (employees.get(i).getSalary() > highestPaid.getSalary()) {
//                highestPaid = employees.get(i);
//            }
//        }
//        System.out.println(highestPaid);

        Employee employee = employees
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);

        System.out.println(employee);

        System.out.println("--------------------------------------------------------------------");
        System.out.println("[REDUCE]");

        /*
        Bu kod parçacığı, "employees" listesindeki tüm çalışanların maaşlarının toplamını bulmak için kullanılıyor.
         */

//        double totalSalary = -5000.0;
//        for (Employee employee : employees) {
//            totalSalary += employee.getSalary();
//        }
//        System.out.println(totalSalary);

        Double totalSalary = employees
                .stream()
                .map(employee1 -> employee1.getSalary())
                .reduce(-5000.0, Double::sum);
        System.out.println(totalSalary);
    }
}
