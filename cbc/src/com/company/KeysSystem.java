package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 30-03-2015.
 */
public class KeysSystem {
    HashMap<Character, Character> mapTo;
    HashMap<Character, Character> mapFrom;
    public KeysSystem(String key) {
        mapTo = FillMapTo(key);
        mapFrom = FillMapBy(key);
    }
    private HashMap<Character, Character> FillMapTo(String input) {
        HashMap<Character, Character> ans = new HashMap<Character, Character>();
        String[] tmp = input.split("\n");
        for (int i=0; i<tmp.length; i++) {
            char[] charArr = tmp[i].toCharArray();
            ans.put(charArr[0],charArr[2]);
        }
        return ans;
    }
    private HashMap<Character, Character> FillMapBy(String input) {
        HashMap<Character, Character> ans = new HashMap<Character, Character>();
        String[] tmp = input.split("\n");
        for (int i=0; i<tmp.length; i++) {
            char[] charArr = tmp[i].toCharArray();
            ans.put(charArr[2],charArr[0]);
        }
        return ans;
    }
    public char isDefinedTo(char x) {
        if (mapTo.containsKey(x))
            return mapTo.get(x);
        return x;
    }
    public char isDefinedBy(char x) {
        if (mapFrom.containsKey(x))
            return mapFrom.get(x);
        return x;
    }
}
