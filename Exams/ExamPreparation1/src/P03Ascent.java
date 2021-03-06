import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P03Ascent {
    public static void main(String[] args) throws IOException { // TODO 90/100
        //the twist of the task: if there is a double encoded message, but there hasn't been a similar coded message before, then this message
        //should be decoded only once.
        //If the same encoded part of a message appears, then it should be replaced immediately, before being decoded once
        //Triple coded messages could be decoded if before it there was a once encoded message and a twice encoded message.
        //Task is very confusing.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Pattern pattern = Pattern.compile("(_|,)(?<word>[A-z]+)?(?<digit>[0-9])");

        Map<String, String> codedDecoded = new LinkedHashMap<>();

        String input = reader.readLine();
        while (!"Ascend".equals(input)) {

                for (Map.Entry<String, String> stringStringEntry : codedDecoded.entrySet()) {
                    input = input.replaceAll(stringStringEntry.getKey(), stringStringEntry.getValue());
                }

                Matcher matcher = pattern.matcher(input);
                while (matcher.find()) {
                    StringBuilder sb = new StringBuilder();

                    String word = matcher.group("word");
                    int digit = Integer.parseInt(matcher.group("digit"));

                    for (int i = 0; i < word.length(); i++) {
                        char updatedCharacter = '\0';
                        if (matcher.group(0).startsWith(",")) {
                            updatedCharacter = (char) (word.charAt(i) + digit);
                        } else {
                            updatedCharacter = (char) (word.charAt(i) - digit);
                        }
                        sb.append(updatedCharacter);
                    }

                    codedDecoded.putIfAbsent(matcher.group(0), sb.toString());

                    input = input.replaceAll(matcher.group(0), sb.toString());
                }
            System.out.println(input);

            input = reader.readLine();
        }

        //main ends here
    }
}
