package com.company;

public class Main {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Invalid Input");
            return;
        }
        if (args[0].equals("Encryption"))
            EncryptMessage(args[1], args[2], args[3]);
        if (args[0].equals("Decryption"))
            DecryptMessage(args[1], args[2], args[3]);

    }

    private static void EncryptMessage(String cipherTextPath, String keyPath, String ivPath) {
        byte[] cipherText = FileHelper.GetBinaryData(cipherTextPath);
        String key = FileHelper.GetTextData(keyPath);
        byte[] iv = FileHelper.GetBinaryData(ivPath);
        Encryptor crypter = new Encryptor(key,iv);
        byte[] ans = crypter.Encrypt(cipherText);
        FileHelper.WriteToFileAsBytes(ans);
    }

    private static void DecryptMessage(String cipherTextPath, String keyPath, String ivPath) {
        byte[] cipherText = FileHelper.GetBinaryData(cipherTextPath);
        String key = FileHelper.GetTextData(keyPath);
        byte[] iv = FileHelper.GetBinaryData(ivPath);
        Encryptor crypter = new Encryptor(key,iv);
        byte[] ans = crypter.Decrypt(cipherText);
        FileHelper.WriteToFileAsText(ans);
    }
}
