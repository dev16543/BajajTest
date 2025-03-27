import java.io.*;
import java.nio.file.*;
import java.security.*;
import org.json.*;

class jsonmd5hash {

    public static void main(String[] args) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("input.json")));
            JSONObject jsonObject = new JSONObject(jsonContent);
            JSONObject studentData = jsonObject.getJSONObject("student");

            String firstName = studentData.getString("first_name").toLowerCase().replaceAll("\s", "");
            String rollNumber = studentData.getString("roll_number").toLowerCase().replaceAll("\s", "");

            String combinedString = firstName + rollNumber;
            String hashResult = generateMD5Hash(combinedString);

            Files.write(Paths.get("output.txt"), hashResult.getBytes());

            System.out.println("MD5 Hash generated successfully: " + hashResult);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String generateMD5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(input.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
