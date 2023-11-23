import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class FileHandler {


    public LinkedList<Member> readFile() {
        LinkedList<Member> m = new LinkedList<>();

        Scanner scanner = new Scanner("members.csv");
        while (scanner.hasNextLine()) {
            String l = scanner.nextLine();
            String[] parts = l.split(", ");

            if (parts.length == 4) {
                char pMemberType = parts[0].charAt(0);
                int pMemberID = Integer.parseInt(parts[1]);
                String pName = parts[2];
                double pFees = Double.parseDouble(parts[3]);
                Member member = new Member(pMemberType, pMemberID, pName, pFees);
                m.add(member);
            } else {
                System.err.println("Неверный формат данных в строке: " + l);
            }}
                scanner.close();

            return m;

    }

//Этот открытый метод не получает параметра и возвращает коллекцию LinkedList с объектами Member.
// Метод readFile() читает данные из CSV-файла с подробной информацией обо всех посетителях.
// Затем каждый посетитель добавляется в коллекцию LinkedList. Метод возвращает LinkedList.
// Данные в CSV-файле members.csv имеют следующий формат:
//Тип посетителя, идентификатор посетителя, имя посетителя, оплата, идентификатор клуба для
// посетителей одного клуба и формат Тип посетителя, идентификатор посетителя, имя посетителя,
// оплата, баллы для посетителей многих клубов.


    public void appendFile(String mem) {
        //Метод appendFile() присоединяет новую строку к файлу members.csv каждый раз, когда добавляется
        // новый посетитель. Он получает параметр String с именем mem и не возвращает никакого
        // значения.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv", true))) {
            writer.write(mem);
            writer.newLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    public void overwriteFile(LinkedList<Member> m) {
     //Метод overwriteFile() получает параметр LinkedList<Member> с именем m и ничего не возвращает.
        // Этот метод вызывается каждый раз, когда вы хотите удалить посетителя из клуба. При удалении
        // посетителя необходимо обновить CSV-файл. К сожалению, в Java несуществует метода для
        // простого удаления строки из файла. В файл можно либо записывать данные, либо присоединять
        // их, но возможность удаления не предусмотрена. Следовательно, нужно создать временный файл.
     //Каждый раз, когда вы хотите исключить посетителя из клуба, он сначала удаляется из LinkedList.
        // Затем объект LinkedList передается в аргументе метода overwriteFile().
     //В методе overwriteFile() создается временный файл members.temp, в который записываются
        // все данные из LinkedList. Обратите внимание: данные не записываются прямо в файл
        // members.csv. Это делается для того, чтобы предотвратить возможные ошибки из-за повреждения
        // файла. Если все прошло нормально, исходный файл members.csv удаляется, а members.temp
        // переименовывается в members.csv.


        String s;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.temp", false))) {
            for (int i = 0; i < m.size(); i++) {
                s = m.get(i).toString();
                writer.write(s + "\n");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            File file = new File("members.csv");
            File tempf = new File("members.temp");

            file.delete();
            tempf.renameTo(file);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

