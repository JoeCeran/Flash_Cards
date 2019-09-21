package flashcards;

import java.util.Scanner;

public class Main {
 public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        int cards = scanner.nextInt();
        scanner.nextLine();
        String[] cardArray = new String[cards];
        String[] definitionArray = new String[cards];
        for (int i = 0; i < cardArray.length; i++) {

            System.out.println("The card #" + (i + 1));
            String carded = scanner.nextLine();
            cardArray[i] = carded;

            System.out.println("The definition of the card #" + (i + 1));
            String definition = scanner.nextLine();
            definitionArray[i] = definition;
        }

        for (int i = 0; i < cardArray.length; i++) {
            System.out.println("Print the definition of \"" + cardArray[i] + "\":");
            String input = scanner.nextLine();
            if (definitionArray[i].equals(input)){
                System.out.println("Correct answer");
            } else {
                System.out.println("Wrong answer. (the correct one is \"" + definitionArray[i] + "\".)");
            }
        }
    }
}
