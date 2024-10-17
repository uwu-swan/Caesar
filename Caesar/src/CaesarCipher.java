import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaesarCipher {

    private static final String ALPHABET = "абвгдёежзийклмнопрстуфхцчшщъыьэюя";
    private static final int ALPHABET_SIZE = ALPHABET.length();

    public String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            int index = ALPHABET.indexOf(Character.toLowerCase(ch));
            if (index != -1) {
                char encryptedChar = ALPHABET.charAt((index + shift) % ALPHABET_SIZE);
                if (Character.isUpperCase(ch)) {
                    result.append(Character.toUpperCase(encryptedChar));
                } else {
                    result.append(encryptedChar);
                }
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public String decrypt(String text, int shift) {
        return encrypt(text, ALPHABET_SIZE - (shift % ALPHABET_SIZE));
    }

    public void encryptFile(String inputFilePath, String outputFilePath, int shift) throws IOException {
        Path inputPath = Paths.get(inputFilePath);
        Path outputPath = Paths.get(outputFilePath);

        String content = new String(Files.readAllBytes(inputPath));
        String encryptedContent = encrypt(content, shift);
        Files.write(outputPath, encryptedContent.getBytes());
    }

    public void decryptFile(String inputFilePath, String outputFilePath, int shift) throws IOException {
        Path inputPath = Paths.get(inputFilePath);
        Path outputPath = Paths.get(outputFilePath);

        String content = new String(Files.readAllBytes(inputPath));
        String decryptedContent = decrypt(content, shift);
        Files.write(outputPath, decryptedContent.getBytes());
    }
}
