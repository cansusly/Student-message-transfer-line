import java.util.Scanner;
import java.util.Random;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
        Random random = new Random();
        DoubleLinkedList studentList = new DoubleLinkedList();
        String[] allNames = new String[115];

        for (int i =0; i<allNames.length; i++){
            allNames[i] = reader.readLine();//reading all names into an array
        }
        reader.close();
        System.out.println("Enter the number of hub students :");
        int hubStudentNum = scan.nextInt();
        studentList = studentList.makeRandomizedList(allNames,hubStudentNum);//this method gets 30 random names and random hubS.
        System.out.println(studentList.toString());

        System.out.print("Enter a message: ");
        String message = scan.next();

        simulateMessagePassing(studentList, message, random);

        if (allVisited(studentList)) {
            System.out.println("All students have been visited.");
        } else {
            System.out.println("Not all students have been visited. Please continue.");

        }
    }

    public static boolean allVisited(DoubleLinkedList studentList) {
        Node currentNode = studentList.head;
        while (currentNode != null) {
            if (!currentNode.isVisited){
                return false;
            }
            currentNode=currentNode.next;
        }
        return true;
    }

    public static void simulateMessagePassing(DoubleLinkedList studentList,  String message, Random random) {
        System.out.println("Starting message passing simulation...");
        System.out.println("**");

        Random rand = new Random();
        int randomStart = rand.nextInt(30);
        Node currentStudent = studentList.head;

        for (int i=0; i<randomStart; i++){//Random starter student
            currentStudent = currentStudent.next;
        }

        System.out.println(currentStudent.name + " The first randomly chosen student that will get the message:  "+ message);


        boolean directionRight = random.nextBoolean(); // true for right, false for left
        System.out.println("Randomly generating a direction: " + (directionRight ? "right" : "left"));

        do {
            int k = random.nextInt(5) + 1;
            Node previousStudent = currentStudent;

            if(currentStudent.isHubStudent){
                currentStudent.isVisited =true;
                System.out.println("You have reached a hub student. Please enter a new message :");
                message = scan.nextLine();
                directionRight= !directionRight;
                System.out.println("Direction has been reversed. The new direction is: " + (directionRight ? "right" : "left"));
            }


            for (int i = 0; i < k; i++) {
                if (directionRight) {
                    if (currentStudent.next != null) {
                        currentStudent = currentStudent.next;
                        currentStudent.isVisited=true;
                    } else {
                        if (!currentStudent.isHubStudent){
                            System.out.println("You have reached to head of the list, the direction has been reversed.");
                            directionRight = false;
                            break;
                        }

                    }
                } else {
                    if (currentStudent.prev != null) {
                        currentStudent = currentStudent.prev;
                        currentStudent.isVisited=true;
                    } else {
                        if (!currentStudent.isHubStudent){
                            System.out.println("You have reached to head of the list, the direction has been reversed.");
                            directionRight = true;
                            break;
                        }
                        break;
                    }
                }
            }
            System.out.println("The random k from 1-5 is : " + k);

            System.out.println(previousStudent.name + " passes the message to " + currentStudent.name);
            System.out.println("*****");

            int commonLetters = commonLetterCounter(currentStudent.name, message);
            currentStudent.commonLettersCounter += commonLetters;
            System.out.println("Common letters found between " + currentStudent.name + " and the message: " + commonLetters);
            System.out.println("********");


        } while (!allVisited(studentList));
        System.out.println(studentList.toString());
        System.out.println("Message passing completed.");
    }

    public static int commonLetterCounter(String studentName, String message) {
        int[] nameLetterCount = new int[26];
        int commonLetterCount = 0;

        String lowerStudentName = studentName.toLowerCase();
        //String lowerMessage = message.toLowerCase();

        for (int i = 0; i < lowerStudentName.length(); i++) {
            char c = lowerStudentName.charAt(i);
            if (c >= 'a' && c <= 'z') {
                nameLetterCount[c - 'a']++;
            }
        }
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c >= 'a' && c <= 'z' && nameLetterCount[c - 'a'] > 0) {
                commonLetterCount++;
                nameLetterCount[c - 'a']--;
            }
        }
        return commonLetterCount;
    }
}