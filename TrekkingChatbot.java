
import java.util.HashMap;
import java.util.Scanner;

public class TrekkingChatbot {

    private static final HashMap<String, String> responses = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    static {
        // Greetings
        responses.put("hello", "Hello! Ready for your next trekking adventure?");
        responses.put("hi", "Hi there! How can I assist you in planning your trek?");
        responses.put("hey", "Hey trekker! What can I help you with today?");

        // Basic info
        responses.put("name", "I'm TrekMate, your personal trekking assistant!");
        responses.put("who made you", "I was created by trekking enthusiasts to help fellow adventurers!");

        // Help
        responses.put("help", """
                I can help with:
                - Trek recommendations
                - Gear suggestions
                - Weather information
                - Safety tips
                - Training advice
                What would you like to know?""");

        // Trek recommendations
        responses.put("recommend", "I can recommend treks based on difficulty and region. Could you specify your preferred location?");
        responses.put("suggest", "I'd be happy to suggest some treks! What difficulty level are you looking for?");
        responses.put("best treks", "Here are some popular treks: Rajmachi, Harishchandragad, Kalsubai, and Torna in Maharashtra.");

        // Safety
        responses.put("safety", """
                Important safety tips:
                1. Always trek with a partner/group
                2. Inform someone about your route
                3. Check weather forecasts
                4. Carry emergency contacts""");

        responses.put("safe", "For safe trekking: stay on marked trails, carry a first aid kit, and know your limits.");

        // Gear
        responses.put("gear", """
                Essential trekking gear:
                - Sturdy trekking shoes
                - Backpack (30-50L)
                - Water (3L)
                - First aid kit
                - Navigation tools""");

        responses.put("pack", "When packing: include layers for changing weather, energy snacks, and emergency supplies.");

        // Farewells
        responses.put("bye", "Happy trails! May your adventures be safe and memorable!");
        responses.put("thanks", "You're welcome! Enjoy your trekking experience!");
        responses.put("thank you", "My pleasure! Let me know if you need any more help!");
    }

    public static String getResponse(String userInput) {
        userInput = userInput.toLowerCase().trim();

        // Check for direct matches first
        for (String key : responses.keySet()) {
            if (userInput.contains(key)) {
                return responses.get(key);
            }
        }

        // Check for similar words
        if (userInput.matches(".*(recommend|suggest|where).*")) {
            return responses.get("recommend");
        }

        if (userInput.matches(".*(safe|danger|risk).*")) {
            return responses.get("safety");
        }

        if (userInput.matches(".*(gear|pack|equip).*")) {
            return responses.get("gear");
        }

        if (userInput.matches(".*(hello|hi|hey).*")) {
            return responses.get("hi");
        }

        return "I'm still learning! Try asking about:\n"
                + "- Trek recommendations\n- Packing lists\n- Safety tips\n"
                + "Or type 'help' for more options.";
    }

    public static void main(String[] args) {
        System.out.println("""
                ====================================
                Welcome to TrekMate - Your Trekking Guide!
                Type 'exit' anytime to end the chat.
                ====================================
                """);

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("TrekMate: Happy trekking! Stay safe and enjoy the mountains!");
                break;
            }

            System.out.println("TrekMate: " + getResponse(userInput));
        }

        scanner.close();
    }
}
