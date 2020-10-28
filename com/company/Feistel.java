package com.company;
import java.util.ArrayList;
import java.util.Base64;

public class Feistel {

    public ArrayList<String> input_generator(String input){

        while(input.length()%96!=0){
            input+="0";
        }
        ArrayList<String> inputs = new ArrayList<>();
        for(int i=0;i<input.length();i+=96){
            String dummy = input.substring(i,i+96);
            inputs.add(dummy);
        }
        return inputs;
    }

    /**public void feistel_EBC(String input,String key,boolean isEnc){
        ArrayList<String> keys = subkey_generation(key);
        ArrayList<String> inputs = input_generator(input);

        if(isEnc){

        }


    }**/

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

    public String base64_decoder(String encodedString){
        String key_bits = "";
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(encodedString);
        for ( int i = 0 ; i < bytes.length; i++){
            String output = String.format("%8s", Integer.toBinaryString((int) bytes[i])).replace(' ', '0');
            key_bits += output;
        }
        System.out.println("keyin bit hali:  " + key_bits);
        System.out.println("keyin decode edilmiş hali:  " + new String(bytes));
        return key_bits;
    }
}
