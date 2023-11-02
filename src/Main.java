import java.io.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {

        String inputFileName = "C:\\Users\\Ильдар\\OneDrive\\Документы\\базаданных\\bugawuga.java";
        String outputFileName = "C:\\Users\\Ильдар\\OneDrive\\Документы\\базаданных\\bugawuga2.java";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            String line;

            while ((line = reader.readLine()) != null) {
                // Убираем комментарии
                line = line.replaceAll("//.*|/\\*(.|\\n)*?\\*/", "");

                // Удаляем лишние пробелы и символы перехода на новую строку
                line = line.replaceAll("\\s+", "");
                // Заменяем идентификаторы на односимвольные имена
                line = obfuscateVariables(line);

                writer.write(line);
            }

            reader.close();
            writer.close();
            System.out.println("Обфускация завершена.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для замены идентификаторов на односимвольные имена
    private static String obfuscateVariables(String code) {
        String[] identifiers = code.split("[^A-Za-z0-9_]+");
        int variableCounter = 0;

        for (String identifier : identifiers) {
            if (identifier.matches("[A-Za-z_][A-Za-z0-9_]*")) {
                String variableName = "a" + variableCounter++;
                code = code.replaceFirst(Pattern.quote(identifier), variableName);

            }
        }

        return code;
    }
}