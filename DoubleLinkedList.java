import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class DoubleLinkedList {
    Node head;
    Node tail;

    public void addStudent(String name, Boolean isHubStudent) {
        Node newNode = new Node(name,isHubStudent);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }


    @Override
    public String toString() {
        String result = "";
        Node current = head;
        int  i=0;
        while (current != null) {
            result += current.getName();
            result += " (";
            result += current.isHubStudent ? "Hub" : "Regular";
            result += ")";
            result += ", (Common Letters: " + current.commonLettersCounter + ")";
            if (current.next != null) {
                result += "  <->  ";
            }
            i++;
            current = current.next;
        }
        return result;
    }
    public DoubleLinkedList makeRandomizedList(String[] array, int N) {
        Random random = new Random();
        int randomIndex = 0;
        ArrayList<String> newArray = new ArrayList<>();
        DoubleLinkedList nameList = new DoubleLinkedList();

        while (newArray.size() < 30) {
            randomIndex = random.nextInt(array.length);
            String randomName = array[randomIndex];

            if (!newArray.contains(randomName)) {
                newArray.add(randomName);
                nameList.addStudent(randomName, false);
            }
        }

        for (int j = 0; j < N; j++) {
            randomIndex = random.nextInt(30);
            String randomName = newArray.get(randomIndex);

            Node current = nameList.head;
            while (current != null) {
                if (current.name.equals(randomName)) {
                    current.isHubStudent = true;
                    break;
                }
                current = current.next;
            }
        }
        return nameList;
    }

}