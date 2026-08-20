import java.util.Scanner;

public class HillCipher {
    
    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return -1;
    }

    static String encrypt(String text, int[][] key) {
        
        text = text.toUpperCase().replaceAll("\\s", "");
        if (text.length() % 2 != 0) 
            text += "X";
            
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int a = text.charAt(i) - 'A';
           
            int b = text.charAt(i + 1) - 'A'; 
            
            int c1 = (key[0][0] * a + key[0][1] * b) % 26;
            int c2 = (key[1][0] * a + key[1][1] * b) % 26;
            
            cipher.append((char) (c1 + 'A'));
            cipher.append((char) (c2 + 'A'));
        }
        return cipher.toString();
    }

    static String decrypt(String cipher, int[][] key) {
        int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]) % 26;
        if (det < 0) det += 26;
        
       
        int detInv = modInverse(det, 26); 
        if (detInv == -1) {
            return "Key matrix is not invertible!";
        }

        int[][] invKey = new int[2][2];
        invKey[0][0] = (key[1][1] * detInv) % 26;
        
        invKey[0][1] = ((-key[0][1] % 26 + 26) * detInv) % 26;
        invKey[1][0] = ((-key[1][0] % 26 + 26) * detInv) % 26;
        invKey[1][1] = (key[0][0] * detInv) % 26;

        
        StringBuilder plain = new StringBuilder(); 
        for (int i = 0; i < cipher.length(); i += 2) {
           
            int a = cipher.charAt(i) - 'A'; 
            int b = cipher.charAt(i + 1) - 'A';
            
            int p1 = (invKey[0][0] * a + invKey[0][1] * b) % 26;
           
            int p2 = (invKey[1][0] * a + invKey[1][1] * b) % 26; 
            
            
            plain.append((char) (p1 + 'A'));
            plain.append((char) (p2 + 'A')); 
        }
        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Fixed: Added "int" to instantiation
        int[][] key = new int[2][2]; 
        
        System.out.println("Enter 2*2 Key Matrix (e.g., 3 3 2 5):");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key[i][j] = sc.nextInt();
            }
        }
        sc.nextLine(); 

        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();
        
        String cipher = encrypt(text, key);
        System.out.println("Encrypted Text: " + cipher);
        
        // Fixed: Called decrypt method and fixed printing syntax
        String decrypted = decrypt(cipher, key); 
        System.out.println("Decrypted Text: " + decrypted); 
        
        sc.close();
    }
}