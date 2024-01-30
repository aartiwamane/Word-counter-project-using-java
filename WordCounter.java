import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter 
{
    public static void main(String[] arg) 
    {
        Scanner scanner = new Scanner(System.in);

       
        System.out.println("Enter text or provide a file path:");
        String input = scanner.nextLine();

        String content = readInput(input);

        String[] words = content.split("[\\s\\p{Punct}]+");

        int wordCount = 0;

        for (String word : words)
        {
            if (!isStopWord(word)) 
            {
                wordCount++;
            }
        }

        System.out.println("Total word count: " + wordCount);

        displayWordStatistics(words);
    }

    private static String readInput(String input)
    {
        StringBuilder content = new StringBuilder();

        if (input.endsWith(".txt"))
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(input))) 
            {
                String line;
                while ((line = reader.readLine()) != null) 
                {
                    content.append(line).append(" ");
                }
            } catch (IOException e) 
            {
                System.err.println("Error reading file: " + e.getMessage());
                System.exit(1);
            }
        } 
        else 
        {
            content.append(input);
        }

        return content.toString();
    }

    private static boolean isStopWord(String word) 
    {
        String[] stopWords = {"the", "and", "is", "in", "it", "to", "of", "for", "with", "on"};
        return Arrays.asList(stopWords).contains(word.toLowerCase());
    }

    private static void displayWordStatistics(String[] words) 
    {
        Map<String, Integer> wordFrequency = new HashMap<>();

        for (String word : words) 
        {
            if (!isStopWord(word)) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        System.out.println("\nWord Statistics:");
        System.out.println("Number of Unique Words: " + wordFrequency.size());

        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) 
        {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " occurrences");
        }
    }
}
