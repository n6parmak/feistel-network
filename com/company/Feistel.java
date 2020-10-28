package com.company;
import java.util.ArrayList;
import java.util.Base64;

public class Feistel {

    public String Left_Circular_Shift(String key, int number){
        return key.substring(number) + key.substring(0, number);
    }
    
    public ArrayList<String> Subkey_Generation(String key){
        ArrayList<String> keys = new ArrayList<String>();
        if(key.length() == 96){
            for(int i= 0; i < 10 ; i ++){
                String new_shifted = Left_Circular_Shift(key, i+1);
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

    public String Base64_Decoder(String encodedString){
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

    public String XOR(String round_i, String key_i){
        String result = "";
        for (int i = 0; i < round_i.length(); i++) {
            result += round_i.charAt(i)^key_i.charAt(i);
        }
        return result;
    }

    private int find_index(String[] row, String element) {
        int index = -1;

        int i = 0;
        while(i < row.length) {
            if(row[i].equals(element)) {
                index = i;
            }
            i++;
        }
        return index;
    }

    public String DES_boxes(String divided){
        String outer = "", middle = "";
        outer += divided.charAt(0);
        outer += divided.charAt(5);
        middle = divided.substring(1,5);

        String [] row = {"00", "01", "10", "11"};
        String [] column = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
        String[][] arr = {{"0010", "1100", "0100", "0001", "0111", "1010", "1011", "0110", "1000", "0101", "0011", "1111", "1101", "0000", "1110", "1001"},
                {"1110", "1011", "0010", "1100", "0100", "0111", "1101", "0001", "0101", "0000", "1111", "1010", "0011", "1001", "1000", "0110"},
                {"0100", "0010", "0001", "1011", "1010", "1101", "0111", "1000", "1111", "1001", "1100", "0101", "0110", "0011", "0000", "1110"},
                {"1011", "1000", "1100", "0111", "0001", "1110", "0010", "1101", "0110", "1111", "0000", "1001", "1010", "0100", "0101", "0011"}};
        int row_index = find_index(row, outer);
        int column_index = find_index(column, middle);

        return arr[row_index][column_index];
    }

    public String  scramble_function(String round_i, String key_i){
        String result = "";
        String new_plaintext = XOR(round_i, key_i);
        new_plaintext += XOR(new_plaintext.substring(0,6), new_plaintext.substring(6,12));
        new_plaintext += XOR(new_plaintext.substring(12,18), new_plaintext.substring(18,24));
        new_plaintext += XOR(new_plaintext.substring(24,30), new_plaintext.substring(30,36));
        new_plaintext += XOR(new_plaintext.substring(30,36), new_plaintext.substring(36));
        for(int i = 0; i < 72; i = i + 6){
            result += DES_boxes(new_plaintext.substring(i, i+ 6));
        }
        return result;
    }

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
}
