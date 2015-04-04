package com.company;

import java.util.Arrays;

/**
 * Created by User on 30-03-2015.
 */
public class Encryptor {

    byte[] m_iv =null;
    KeysSystem m_kSys;

    public Encryptor(String key, byte[] iv) {
        m_iv = iv;
        m_kSys = new KeysSystem(key);
    }

    public byte[] Encrypt(byte[] s) {
        byte[] scrambledText = new byte[s.length];
        byte[] buffer = new byte[10];
        int buffLoc=0;
        int outputLoc=0;
        for (int i=0;i<s.length;i++) {
            buffer[buffLoc] = s[i];
            buffLoc++;
            if (buffLoc == 10) {
                //dump buffer
                byte[] newText = scrambleXOR(buffer);
                byte[] keyedText = UseKeyExchangeToEncrypt(newText);
                m_iv = keyedText;
                for (int j = outputLoc, k = 0; k < 10; j++, k++) {
                    scrambledText[j] = keyedText[k];
                }
                outputLoc+=10;
                buffLoc = 0;
            }
        }
        return scrambledText;
    }
    public byte[] Decrypt(byte[] s) {
        byte[] sortedText = new byte[s.length];
        byte[] buffer = new byte[10];
        int buffLoc=0;
        int outputLoc=0;
        for (int i=0;i<s.length;i++) {
            buffer[buffLoc] = s[i];
            buffLoc++;
            if (buffLoc == 10) {
                //dump buffer
                byte[] savedIV = buffer;
                byte[] keyedText = UseKeyExchangeToDecrypt(buffer);
                byte[] newText = scrambleXOR(keyedText);
                m_iv = Arrays.copyOf(savedIV,10);
                for (int j = outputLoc, k = 0; k < 10; j++, k++) {
                    sortedText[j] = newText[k];
                }
                outputLoc+=10;
                buffLoc = 0;
            }
        }
        return sortedText;
    }
    private byte[] UseKeyExchangeToEncrypt(byte[] arr) {
        byte[] ans = new byte[arr.length];
        for (int i=0; i<arr.length; i++) {
            char c = (char) arr[i];
            ans[i] = (byte) m_kSys.isDefinedTo(c);
        }
        return ans;
    }
    private byte[] UseKeyExchangeToDecrypt(byte[] arr) {
        byte[] ans = new byte[arr.length];
        for (int i=0; i<arr.length; i++) {
            char c = (char) arr[i];
            ans[i] = (byte) m_kSys.isDefinedBy(c);
        }
        return ans;
    }



    private byte[] scrambleXOR(byte[] s) {
        // for 10 bytes only! (key size)
        byte[] output = new byte[10];
        for (int i=0; i<10; i++) {
            int xValue = s[i];
            int yValue = m_iv[i];
            int xorValue = xValue^yValue;
            byte c = (byte)xorValue;
            output[i] = c;
        }
        return output;
    }
}
