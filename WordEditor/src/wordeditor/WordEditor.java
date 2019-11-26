/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordeditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import org.apache.commons.lang.StringUtils;



/**
 *
 * @author kunnu
 */
public class WordEditor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {   // TODO code application logic here
            // SaveFile();
            File file = new File("C:\\Users\\kunnu\\Desktop\\test.txt");

//  BufferedReader br = new BufferedReader(new FileReader(file)); 
//  
//  String st; 
//  while ((st = br.readLine()) != null) 
//    System.out.println(st); 
            String data = readFileAsString("C:\\Users\\kunnu\\Desktop\\test.txt");
            FormatString(data);
            System.out.println(data);
        } catch (Exception ex) {

        }
    }

    public static String readFileAsString(String fileName) throws Exception {
        String data;
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data.replaceAll("\r\n", "");
    }

    public static void FormatString(String data) {
        try {
            char c;
            char[] operation;
            String str;
            String splittedStringArray[] = data.split("-");
            int count = splittedStringArray.length;
            for (int i = 1; i < count; i++) {
                str = splittedStringArray[i];//.replace("\r\n", "");
                if (str.isEmpty()) {

                }
//                 if(str.contains("\r\n"))
//                 {
//                   str = str.replaceAll("\r\n", "");
//                 }
//                 if(str.contains("\n"))
//                 {
//                   str = str.replaceAll("\n", "");
//                 }
                c = splittedStringArray[i].charAt(0);
                //data= splittedStringArray[i];
                int charCount = str.length();
                String abc = "";
                if (charCount <= 80) {
                    str = "  " + str;
                } else {
                     str = str.substring(1);
                     abc = NIndentation(str);
                     //abc=AlighCenter(str);
                 // abc=  MaxChar(str);
                    // ChangeFormatOfString(c, str);
                    SaveFile(abc);
                    // j=str.length()/80;

                    SaveFile(abc);

                }
               

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void ChangeFormatOfString(char formatIndicator, String text) {
        try {
            switch (formatIndicator) {
                case 'i':
                    text="  "+text;

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public static void SaveFile(String text) {
        try {
            FileWriter writer = new FileWriter("MyFile.txt", true);
            writer.write(text);
            //writer.write("\r\n");   // write new line
            writer.write("Good Bye!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String AlignRight(String str) {
        int j = 0;
        int m = str.length() / 80;
        
      // str = StringUtils.leftPad(str, 80);
        String abc = "";
        while (j <= m) {
            if (j < m) {
                abc = abc + str.substring(80 * j, (80 * j + 80) - 1) + System.lineSeparator();
                //  abc.format("%-10s",abc);
            } else {
                abc = abc + StringUtils.leftPad(str.substring(80 * j, str.length()), 79) + System.lineSeparator();

            }
            //call switch case
            j++;
        }
        return abc;
        }
        public void AlignLeft(String str) {
        int j = 0;
        int m = str.length() / 80;
        str = str.substring(1);
        String abc = "";
        while (j <= m) {
            if (j < m) {
                abc = abc + str.substring(80 * j, (80 * j + 80) - 1) + System.lineSeparator();
                //  abc.format("%-10s",abc);
            } else {
                abc = abc + str.substring(80 * j, str.length()) + System.lineSeparator();

            }
            j++;
        }
    }
        public static String AlignCenter(String str) {
             int j = 0;
        int m = str.length() / 80;
        
      // str = StringUtils.leftPad(str, 80);
        String abc = "";
        while (j <= m) {
            if (j < m) {
                abc = abc + str.substring(80 * j, (80 * j + 80) - 1) + System.lineSeparator();
                //  abc.format("%-10s",abc);
            } else {
                abc = abc + StringUtils.center(str.substring(80 * j, str.length()), 79) + System.lineSeparator();

            }
            //call switch case
            j++;
        }
        return abc;
        
    }
        public static String NIndentation(String str) {
        int j = 0;
       
        str= str.trim();
         int m = str.length() / 80;
        String abc = "";
        while (j <= m) {
            if (j < m) {
                abc = abc + str.substring(80 * j, (80 * j + 80) - 1) + System.lineSeparator();
                //  abc.format("%-10s",abc);
            } else {
                abc = abc + str.substring(80 * j, str.length()) + System.lineSeparator();

            }
            j++;
        }
        return abc;
    }

}
