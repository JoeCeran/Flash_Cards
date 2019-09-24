package flashcards;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
       public static void main(String[] args) {
        boolean exists = true;
        Scanner scanner = new Scanner(System.in);
        LinkedHashMap<String, String> cardToDefinition = new LinkedHashMap<>();
        LinkedHashMap<String, String> definitionToCard = new LinkedHashMap<>();
        System.out.println("Input the number of cards:");
        int carded = scanner.nextInt();
        String key = "";
        scanner.nextLine();

        for (int i = 0; i < carded; i++) {

            System.out.println("The card #" + (i + 1));
            String cardedd = scanner.nextLine();
            if (cardToDefinition.containsKey(cardedd)) {
                do {
                    System.out.println("The card \"" + cardedd + "\" already exists. Try again:");
                    cardedd = scanner.nextLine();
                    if (cardToDefinition.containsKey(cardedd)) {
                        exists = true;
                    } else {
                        exists = false;
                    }
                } while (exists == true);
            }

            System.out.println("The definition of the card #" + (i + 1));
            String definition = scanner.nextLine();
            if (cardToDefinition.containsValue(definition)) {
                do {
                    System.out.println("The definition \"" + definition + "\" already exists. Try again:");
                    definition= scanner.nextLine();
                    if (cardToDefinition.containsValue(definition)) {
                        exists = true;
                    } else {
                        exists = false;
                    }
                } while (exists == true);
            }
            cardToDefinition.put(cardedd, definition);
            definitionToCard.put(definition, cardedd);
        }
            exists = true;
            for (String j : cardToDefinition.keySet()) {
                System.out.println("Print the definition of \"" + j + "\":");
                String input = scanner.nextLine();
                if (cardToDefinition.get(j).equals(input)) {
                    System.out.println("Correct answer");
                }
                else if (!input.equals(j) && cardToDefinition.containsValue(input) ) {
                        System.out.println("Wrong answer. the correct one is \"" + cardToDefinition.get(j) + "\", you've just written the definition of \"" + definitionToCard.get(input) + "\".");
                }
                else {
                    System.out.println("Wrong answer. the correct one is \"" + cardToDefinition.get(j) + "\".");
                }
            }
        }
    }
