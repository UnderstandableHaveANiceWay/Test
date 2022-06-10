import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task {
    public static boolean createUser(String FirstName, String LastName, String UserName, String FilePath) throws IOException {
        File file = new File(FilePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsolutePath(), true);
        Scanner scanner = new Scanner(new FileReader(file.getAbsolutePath()));
        while(scanner.hasNextLine()) {
            if (scanner.nextLine().equals(UserName + ":")) return false;
        }
        fw.write(UserName + ":\n" + "\tFirstName:" + FirstName + "\n" + "\tLastName:" + LastName + "\n" + "\tTasks:\n");
        fw.flush();
        scanner.close();
        fw.close();
        return true;
    }
    public static boolean showAllUsers(String FilePath) throws IOException {
        if (!(new File(FilePath)).exists()) return false;
        Scanner scanner = new Scanner(new FileReader(FilePath));
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
        return true;
    }
    public static boolean addTask(String UserName, String TaskTitle, String TaskDescription, String FilePath) throws IOException {
        if (!(new File(FilePath)).exists()) return false;
        Scanner scanner = new Scanner(new FileReader(FilePath));
        StringBuilder file = new StringBuilder(), line = new StringBuilder();
        while (scanner.hasNextLine()) {
            if (scanner.nextLine().equals(UserName + ":")) {
                file.append(UserName + ":" + "\n");
                file.append(scanner.nextLine() + "\n");
                file.append(scanner.nextLine() + "\n");
                file.append(scanner.nextLine() + "\n");
                do{
                    line.delete(0, line.length());
                    if (scanner.hasNextLine()) line.append(scanner.nextLine());
                    if (line.toString().contains("\t")) file.append(line + "\n");
                }while (line.toString().contains("\t") && line.length() > 0);
                file.append("\t\tTask Title:\n");
                file.append("\t\t\t" + TaskTitle + "\n");
                file.append("\t\tTask Description:\n");
                file.append("\t\t\t" + TaskDescription + "\n");
            }
            if (scanner.hasNextLine()) file.append(scanner.nextLine() + "\n");
        }
        System.out.println(file);
        FileWriter fw = new FileWriter(FilePath, false);
        fw.write(file.toString());
        scanner.close();
        fw.close();
        return true;
    }
    public static boolean showTasks(String UserName, String FilePath) throws IOException {
        if (!(new File(FilePath)).exists()) return false;
        Scanner scanner = new Scanner(new FileReader(FilePath));
        StringBuilder line = new StringBuilder();
        while (scanner.hasNextLine()) {
            if (scanner.nextLine().equals(UserName + ":")) {
                while (!scanner.nextLine().equals("Tasks:"));
                while (scanner.hasNext()) {
                    line.delete(0, line.length());
                    line.append(scanner.nextLine());
                    if (!line.toString().contains("\t")) break;
                    System.out.println(line);
                }
            }
        }
        scanner.close();
        return true;
    }

    public static void main(String[] args) {
        System.out.println(args[0]);
        String firstName = "", lastName = "", userName = "", taskTitle = "", taskDescription = "";
        switch (args[0]) {
            case "-createUser":
                try {
                    for (String a: args) {
                        if (a.contains("-fn")) firstName = a.substring(5, a.length() - 1);
                        else if (a.contains("-ln")) lastName = a.substring(5, a.length() - 1);
                        else if (a.contains("-un")) userName = a.substring(5, a.length() - 1);
                    }
                    System.out.println(createUser(firstName,lastName,userName, "C:/Task2/Task2.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "-showAllUsers":
                try {
                    System.out.println(showAllUsers("C:/Task2/Task2.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "-addTask":
                try {
                    for (String a: args) {
                        if (a.contains("-un")) userName = a.substring(5, a.length() - 1);
                        else if (a.contains("-tt")) taskTitle = a.substring(5, a.length() - 1);
                        else if (a.contains("-td")) taskDescription = a.substring(5, a.length() - 1);
                    }
                    System.out.println(addTask(userName,taskTitle,taskDescription, "C:/Task2/Task2.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "-showTasks":
                try {
                    System.out.println(showTasks(args[1].substring(5,args[1].length() - 1), "C:/Task2/Task2.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
