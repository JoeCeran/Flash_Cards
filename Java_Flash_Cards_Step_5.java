package flashcards;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Math.round;


public class Main {

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) {
        boolean exists = true;
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        LinkedHashMap<String, String> cardToDefinition = new LinkedHashMap<>();
        LinkedHashMap<String, String> definitionToCard = new LinkedHashMap<>();

        while (running == true) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String selection = scanner.nextLine();


            switch (selection) {
                case "add":

                    System.out.println("The card");
                    String cardedd = scanner.nextLine();
                    if (cardToDefinition.containsKey(cardedd)) {
                        System.out.println("The card \"" + cardedd + "\" already exists. Try again:\n");
                        break;
                    }

                    System.out.println("The definition of the card");
                    String definition = scanner.nextLine();
                    if (cardToDefinition.containsValue(definition)) {
                        System.out.println("The definition \"" + definition + "\" already exists. Try again:\n");
                        break;
                    }

                    cardToDefinition.putIfAbsent(cardedd, definition);
                    definitionToCard.putIfAbsent(definition, cardedd);
                    System.out.println("The pair (\"" + cardedd + "\":\"" + definition + "\") has been added.\n");
                    break;

                case "remove":
                    System.out.println("The card:");
                    String theCard = scanner.nextLine();
                    for (int i = 0; i < cardToDefinition.size(); i++) {
                        if (cardToDefinition.keySet().toArray()[i].equals(theCard)) {
                            cardToDefinition.remove(theCard);
                            System.out.println("The card has been removed");
                            break;
                        } else if (!cardToDefinition.keySet().toArray()[i].equals(theCard) && (i+1) == cardToDefinition.size()){
                            System.out.println("Can't remove \"" + theCard + "\": there is no such card.");
                        }
                    }
                    break;
                case "import":
                    System.out.println("File name:");
                    String fileName = scanner.nextLine();
                    File file = new File(fileName + ".txt");
                    try {
                        String line;
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        int count = 0;
                        while ((line = reader.readLine()) != null)
                        {
                            count++;
                            String[] parts = line.split(":", 2);
                            if (parts.length >= 2)
                            {
                                String key = parts[0];
                                String value = parts[1];
                                cardToDefinition.put(key, value);
                            } else {
                                System.out.println("ignoring line: " + line);
                            }
                        }
                        System.out.println(count + " cards have been loaded.");
                    } catch (IOException e) {
                        System.out.println("File not found.");
                    }
                    break;
                case "export":
                    System.out.println("File name:");
                    fileName = scanner.nextLine();
                    file = new File(fileName + ".txt");
                    try (PrintWriter printWriter = new PrintWriter(file)) {
                        for (String j : cardToDefinition.keySet()) {
                            printWriter.println(j + ":" + cardToDefinition.get(j));
                        }
                        System.out.println(cardToDefinition.size() + " cards have been saved.");
                    } catch (IOException e) {
                        System.out.printf("An exception occurs %s", e.getMessage());
                    }
                    break;
                case "ask":
                    System.out.println("How many times to ask?");
                    int carded = scanner.nextInt();
                    scanner.nextLine();
                    for (int j = 0, i = 0; j < carded; j++, i++) {
                        if ( i >= cardToDefinition.size() )
                        {
                            i = 0; // reset back to the beginning
                        }
                        System.out.println("Print the definition of \"" + cardToDefinition.keySet().toArray()[i] + "\":");
                        String input = scanner.nextLine();
                        if (cardToDefinition.values().toArray()[i].equals(input)) {
                            System.out.println("Correct answer");
                        } else if (!input.equals(i) && cardToDefinition.containsValue(input)) {
                            System.out.println("Wrong answer. the correct one is \"" + cardToDefinition.values().toArray()[i] + "\", you've just written the definition of \"" + definitionToCard.get(input) + "\".");
                        } else {
                            System.out.println("Wrong answer. the correct one is \"" + cardToDefinition.values().toArray()[i] + "\".");
                        }
                    }
                    break;

                case "exit":
                    System.out.println("Bye bye!");
                    running = false;
                    break;
            }
        }
    }
}
