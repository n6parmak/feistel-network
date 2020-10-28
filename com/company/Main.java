package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //getting arguments and file reading
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String Key ="";
        Feistel feistel = new Feistel();
        Key = feistel.Base64_Decoder(Key);
        ArrayList<String> sub_keys = feistel.Subkey_Generation(Key);
        System.out.println("Subkeys: " + sub_keys);

        feistel.scramble_function("111010111011000111110010111101000011001111010100","000101100111011111101001011101100100110110010111");

        System.out.println(permutation_func("10101010"));
        boolean isEnc = args[1].equals("enc"); // true for encryption false for decryption
        String keyPath="";
        String inputPath="";
        String outputPath="";
        String mode="";
        for (int i = 2; i < args.length; i++) {
            switch (args[i]) {
                case "-K":
                    keyPath = args[i + 1];
                    break;
                case "-I":
                    inputPath = args[i + 1];
                    break;
                case "-O":
                    outputPath = args[i + 1];
                    break;
                case "–M":
                    mode = args[i + 1];
                    break;
            }
        }

       /*try (Scanner input = new Scanner(new File(inputPath));
             PrintWriter output = new PrintWriter(new File(outputPath));
             Scanner keyFile = new Scanner(new File(keyPath))) {
            /** while (scanner.hasNext()) {
                //writer.print(scanner.nextLine());
            }**/


       /*} catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    }
    public static String permutation_func(String input){
        String dummy = "";
        for(int i=0;i<=(input.length()/2)+2;i=i+2){
            dummy+=input.charAt(i+1);
            dummy+=input.charAt(i);
        }
        input = dummy;
        return input;
    }

}