import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {

    final private Scanner reader = new Scanner(System.in);

    private int getIntInput() {
        int userChoice = 0;

        try {
            if (reader.hasNext()){
            //System.out.println();
            userChoice = reader.nextInt();}
         } catch (Exception e) {
            reader.nextLine();
            userChoice = -1;
            System.out.println("Error! Enter a valid value.");
        }

        return userChoice;
    }

    //--------------------
    private void printClubOptions() {
        System.out.println("1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");
    }

    //--------------------
    public int getChoice() {
        System.out.println("WELCOME TO OZONE FITNESS CENTER");
        System.out.println("================================");
        System.out.println("1) Add Member");
        System.out.println("2) Remove Member");
        System.out.println("3) Display Member Information\n");
        System.out.print("Please select an option (or Enter -1 to quit): ");

        int choice = getIntInput();

        return choice;
    }

    //-----------------------------
    public String addMembers(LinkedList<Member> m) {
        //Метод addMembers() получает коллекцию LinkedList объектов Member и добавляет в нее данные
        // нового посетителя. После добавления данных в LinkedList он возвращает строку с информацией
        // о добавленном посетителе.

        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal;



        printClubOptions();
        System.out.print("Choice club: ");
        club = getIntInput();
        while (club < 1 || club > 4) {
            System.out.print("Error. Try again: ");
            club = getIntInput();
        }

        System.out.print("Enter Name: ");
        name = reader.next();

        if (m.size() > 0) {
            memberID = m.getLast().getMemberID() + 1;
        } else {
            memberID = 1;
        }

        if (club != 4) {
            cal = (n)-> {
                switch (n) {
                case 1: return 900;
                case 2: return 950;
                case 3: return 1000;
                default: return -1;
                }
            };
            fees = cal.calculateFees(club);
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            System.out.println("SingleClub Member added:\n" + mbr.toString());//+ memberID + name + fees);
        } else {
            cal = (n)-> {
                switch (n) {
                case 4: return 1200;
                default: return -1;
                }
            };
            fees = cal.calculateFees(club);
           mbr = new MultiClubMember('M', memberID, name, fees, 100);
           System.out.println("MultiClub Member added:\n" + mbr);// memberID + name + fees);

        }
        m.add(mbr);
        mem = mbr.toString();
        return mem;
    }
    //------------------------

        public void removeMember(LinkedList<Member> m) {

            System.out.println("Enter Member ID you want remove: ");
            int memberID = getIntInput();
            for (int i = 0; i < m.size(); i++) {
                if (m.get(i).getMemberID() == memberID) {
                    m.remove(i);
                    System.out.println("Member has been removed.");
                    break;
                } else {
                    System.out.println("There is no Member with this ID.");
                }
            }
        }
  //----------------------------

      public void printMemberInfo(LinkedList<Member> m)
  {
      System.out.print("Enter Member ID to info: ");
      int memberID = getIntInput();
      for (int i=0; i<m.size(); i++){
          if (m.get(i).getMemberID() == memberID){
                 m.remove(i);
              System.out.println(m.toString());
              break;
          }
          else System.out.println("There is no Member with this ID.");
      }
  }

}
