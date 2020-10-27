package com.company;
import java.util.ArrayList;

public class Feistel {

    public String left_circular_shift(String key, int number){
        return key.substring(number) + key.substring(0, number);
    }
    
    public ArrayList<String> subkey_generation(String key){
        ArrayList<String> keys = new ArrayList<String>();
        if(key.length() == 96){
            for(int i= 0; i < 10 ; i ++){
                String new_shifted = left_circular_shift(key, i+1);
                String key_i_th = "";
                if(i % 2 == 0){
                    for (int j = 0; j < 96 ; j = j + 2) {
                        key_i_th += new_shifted.charAt(j);
                    }
                }
                else{
                    for (int j = 1; j < 96 ; j = j + 2) {
                        key_i_th += new_shifted.charAt(j);
                    }
                }
                keys.add(key_i_th);
            }
        }
        return keys;
    }
}
