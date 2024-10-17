import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CaesarCipher cipher = new CaesarCipher();
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Шифровать текст");
            System.out.println("2. Расшифровать текст");
            System.out.println("3. Использовать файл для шифрования(где-то тут ошибка)");
            System.out.println("4. Использовать файл для расшифрования(где-то тут ошибка)");
            System.out.println("5. Выход");
            try {
                int choice = Integer.parseInt(reader.readLine());
                switch (choice) {
                    case 1:
                        encryptText(reader, cipher);
                        break;
                    case 2:
                        decryptText(reader, cipher);
                        break;
                    case 3:
                        encryptTextFromFile(reader, cipher);
                        break;
                    case 4:
                        decryptTextFromFile(reader, cipher);
                        break;
                    case 5:
                        System.out.println("Выход из программы...");
                        return;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка ввода. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void encryptText(BufferedReader reader, CaesarCipher cipher) throws IOException {
        System.out.print("Введите текст для шифрования: ");
        String text = reader.readLine();
        System.out.print("Введите ключ (сдвиг): ");
        int shift = Integer.parseInt(reader.readLine());
        String encryptedText = cipher.encrypt(text, shift);
        System.out.println("Зашифрованный текст: " + encryptedText);
    }

    private static void decryptText(BufferedReader reader, CaesarCipher cipher) throws IOException {
        System.out.print("Введите текст для расшифрования: ");
        String text = reader.readLine();
        System.out.print("Введите ключ (сдвиг): ");
        int shift = Integer.parseInt(reader.readLine());
        String decryptedText = cipher.decrypt(text, shift);
        System.out.println("Расшифрованный текст: " + decryptedText);
    }

    private static void encryptTextFromFile(BufferedReader reader, CaesarCipher cipher) throws IOException {
        System.out.print("Введите путь к файлу для шифрования: ");
        String inputFilePath = reader.readLine();
        System.out.print("Введите путь для записи зашифрованного файла: ");
        String outputFilePath = reader.readLine();
        System.out.print("Введите ключ (сдвиг): ");
        int shift = Integer.parseInt(reader.readLine());

        String content = readFile(inputFilePath);
        String encryptedContent = cipher.encrypt(content, shift);
        writeFile(outputFilePath, encryptedContent);
        System.out.println("Текст успешно зашифрован и сохранен в файл: " + outputFilePath);
    }

    private static void decryptTextFromFile(BufferedReader reader, CaesarCipher cipher) throws IOException {
        System.out.print("Введите путь к файлу для расшифрования: ");
        String inputFilePath = reader.readLine();
        System.out.print("Введите путь для записи расшифрованного файла: ");
        String outputFilePath = reader.readLine();
        System.out.print("Введите ключ (сдвиг): ");
        int shift = Integer.parseInt(reader.readLine());

        String content = readFile(inputFilePath);
        String decryptedContent = cipher.decrypt(content, shift);
        writeFile(outputFilePath, decryptedContent);
        System.out.println("Текст успешно расшифрован и сохранен в файл: " + outputFilePath);
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString().trim();
    }

    private static void writeFile(String filePath, String content) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(content);
        }
    }
}