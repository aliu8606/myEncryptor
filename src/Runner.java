public class Runner {
    public static void main(String[] args) {
        String message = "oh hello there";
        // shift chr 2, horzion shift 5, column shift 3, fill with all
        String encryMessage = "nqj jgngq vjgt";

        Encryptor encryptor = new Encryptor(message, 2, 5, 3);
        System.out.println(encryptor.encrypt());
        System.out.println(encryMessage);

    }
}
