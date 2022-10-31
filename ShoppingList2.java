import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayList;

public class ShoppingList2 {

    public void printShoppingList() {
        // Create HashMaps for available and recipe:
        HashMap<String, Integer> available = new HashMap<>();
        HashMap<String, Integer> recipe = new HashMap<>();

        // Create Scanner:
        Scanner input = new Scanner(System.in);
        
        // Add food and amount to available HashMap:
        while(input.hasNextLine()) {
            String lineString = input.nextLine();

            
            if (lineString.startsWith("RECIPE")) {
                aa:
                while(input.hasNextLine()) {
                    String lineString2 = input.nextLine();
                    if (lineString2.startsWith("RECIPE")) {
                        continue;
                    }
                    String[] line2 = lineString2.split(" ");


                    // For when the same food is mentioned in different recipes:
                    for (var recipeItem : recipe.entrySet()) {
                        if (recipeItem.getKey().equals(line2[2])) {
                            int combinedFood = recipeItem.getValue() + Integer.parseInt(line2[0]);
                            recipe.put(line2[2], combinedFood);
                            continue aa;
                        }
                    }
                    
                    if(line2[1].equals("kg")) {
                        int kg2g = Integer.parseInt(line2[0])*1000;
                        recipe.put(line2[2], kg2g);
                    }
                    else {
                        recipe.put(line2[2], Integer.parseInt(line2[0]));
                    }

                }
                // HUSK!!!
                break;
            }
            
            if (lineString.equals("AVAILABLE")) {
                continue;
            }
            String[] line = lineString.split(" ");
            if(line[1].equals("kg")) {
                int kg2g = Integer.parseInt(line[0])*1000;
                available.put(line[2], kg2g);
            }
            else {
                available.put(line[2], Integer.parseInt(line[0]));
            }
        }



        TreeMap<String, Integer> shopping_list = new TreeMap<>();
        
        // Add all ingredients from recipe to shopping list:
        for (var recipeItem : recipe.entrySet()) {
            shopping_list.put(recipeItem.getKey(), recipeItem.getValue());
        }

        // if statement for if value in available is larger than in shopping list
        ArrayList<String> list = new ArrayList<String>();
        for (var availableItem : available.entrySet()) {
            for (var shoppingItem : shopping_list.entrySet()) {
                if (shoppingItem.getKey().equals(availableItem.getKey()) && (shoppingItem.getValue() <= availableItem.getValue())) {
                    list.add(shoppingItem.getKey());
                    // shopping_list.remove(shoppingItem.getKey());
                }
            }
        }

        // if statement for if value in available is less than in shopping list
        for (var availableItem : available.entrySet()) {
            for (var shoppingItem : shopping_list.entrySet()) {
                if (shoppingItem.getKey().equals(availableItem.getKey()) && (shoppingItem.getValue() > availableItem.getValue())) {
                    shopping_list.put(shoppingItem.getKey(), shoppingItem.getValue() - availableItem.getValue());
                }
            }
        }


        for (String element : list) {
            if (shopping_list.containsKey(element)) {
                shopping_list.remove(element);
            }
        }

        
        System.out.println("Shopping List:");
        for (var item : shopping_list.entrySet()) {
            System.out.println(item.getValue() + " g " + item.getKey());
        }

        input.close();
    }
    public static void main(String[] args) {
        ShoppingList2 shop1 = new ShoppingList2();
        shop1.printShoppingList();
    }
}