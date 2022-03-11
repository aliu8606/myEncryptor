import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What is your message? ");
        String message = input.nextLine();
        System.out.print("What is your caesar shift? ");
        int ca = input.nextInt();
        System.out.print("What is your row shift? ");
        int r = input.nextInt();
        System.out.print("What is your column shift? ");
        int co = input.nextInt();
        System.out.print("Encrypt (E) or Decrypt (D)? ");
        String crypt = input.next();

        Encryptor encryptor = new Encryptor(message, ca, r, co);
        if (crypt.equals("E")) {
            System.out.println(encryptor.encrypt());
        }
        if (crypt.equals("D")) {
            System.out.println(encryptor.decrypt());
        }

        /* test code
        String message = "oh hello there";
        // shift chr 2, horzion shift 5, column shift 3, fill with all
        String caesarm = "qj jgnnq vjgtg";
        String rowshiftm = " jgnnqjvjgtgq ";
        String colshiftm = "vjgtgq  jgnnqj";
        String encryMessage = colshiftm;

        Encryptor encryptor = new Encryptor(message, 2, 5, 3);
        //System.out.println(encryptor.encrypt());
        //System.out.println(encryMessage);

        Encryptor encryptor2 = new Encryptor(encryMessage, 2, 5, 3);
        //System.out.println(encryptor2.decrypt());
        //System.out.println(message);

        String mess = "startling";
        Encryptor encryptor3 = new Encryptor(mess, 4, 2, 8);
        String mess2 = encryptor3.encrypt();

        Encryptor encryptor4 = new Encryptor(mess2, 4, 2, 8);
        System.out.println(encryptor4.decrypt());
        System.out.println(mess);
        */
    }
}
