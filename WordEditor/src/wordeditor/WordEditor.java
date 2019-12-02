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
import java.util.ArrayList;
import java.util.List;
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
            ArrayList<String> input = new ArrayList<>();

            /*BufferedReader br = new BufferedReader(new FileReader(file));   
  String st; 
  while ((st = br.readLine()) != null) 
    input.add(st);
      // input.add(br.readLine());
    System.out.println(st); 
    Parser.run(input);
    ArrayList<String> output = Parser.output();
            SaveFile(output);*/
            String data = readFileAsString("C:\\Users\\kunnu\\Desktop\\test.txt");
           String output =  FormatString(data);
            SaveFile(output);
            System.out.println(data);
        } catch (Exception ex) {
            
        }
    }
    
    public static String readFileAsString(String fileName) throws Exception {
        String data;
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;//.replaceAll("\r\n", "");
    }
    
    public static String FormatString(String data) {
        try {
            char c;
            List<String> operation = new ArrayList<String>();
            String str;
            String output = "";
            String splittedStringArray[] = data.split("\n-");
            int count = splittedStringArray.length;
            for (int i = 0; i < count; i++) {
                str = splittedStringArray[i].replace("\r\n", "").replace("\r", "").replace("-", "");
                if (!str.isEmpty()) {
                    
                    int strLength = str.length();
                    if (strLength == 1) {
                        operation.add(str);
                    } else if (str.length() > 2) {
                        String operator = Character.toString(str.charAt(0));
                        operation.add(operator);
                        output = output + ChangeFormatOfString(operation, str.substring(1, str.length()));
                        operation.clear();
                        
                    }
                    
                }
            }
//                 if(str.contains("\r\n"))
//                 {
//                   str = str.replaceAll("\r\n", "");
//                 }
//                 if(str.contains("\n"))
//                 {
//                   str = str.replaceAll("\n", "");
//                 }
            /* c = splittedStringArray[i].charAt(0);
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

                }*/

            // }
        return output;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "";
    }
    
    public static String ChangeFormatOfString(List<String> list, String text) {
        try {
            
            for (int i = 0; i < list.size(); i++) {
                switch (list.get(i).charAt(0)) {
                    case 'i':
                        text = IIndentation(text);
                        break;
                    case 'c':
                        text = AlignCenter(text);
                        break;
                    case 'r':
                        text = AlignRight(text);
                        break;
                    case 'b':
                        text = BIndentation(text);
                        break;
                    case 'e':
                        text = BlankLine(text);
                        break;
                    case 's':
                        text = AlignLeft(text);
                        break;
                    case 'd':
                        text = DoubleSpaceing(text);
                        break;
                    case 'n':
                        text = NIndentation(text);
                        break;
                    case '1':
                        text = AlignLeft(text);
                        break;
                    case 'l':
                        text = AlignLeft(text);
                        break;
                    
                    default:
                        System.out.println("Not Present");
                    
                }
            }
            return text;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "";
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
    
    public static void SaveFile(ArrayList<String> text) {
        try {
            FileWriter writer = new FileWriter("MyFile.txt", true);
            for (int i = 0; i < text.size(); i++) {
                writer.write(text.get(i));
                writer.write("\r\n");   // write new line
            }
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
    
    public static String AlignLeft(String str) {
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
        return abc;
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
        
        str = str.trim();
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
    
    public static String BIndentation(String str) {
        int j = 0;
        int m = str.length() / 80;
        str = str.substring(1);
        String abc = "";
        while (j <= m) {
            if (j < m) {
                abc = abc + "          " + str.substring(70*j, (70*j+ 70) - 1)
                        + System.lineSeparator();
                //  abc.format("%-10s",abc);
            } else {
                abc = abc + "          " + str.substring(70 * j, str.length())
                        + System.lineSeparator();
                
            }
            j++;
        }
        return abc;
    }
    
    public static String IIndentation(String str) {
        int j = 0;
        
        str = str.trim();
        String abc = "";
        abc = abc + "     " + str.substring(0, 74)
                + System.lineSeparator();
        str = str.substring(75);
        int m = str.length() / 80;
        while (j <= m) {
            if (j < m) {
                
                abc = abc + str.substring(80 * j, (80 * j + 80) - 1)
                        + System.lineSeparator();

                //  abc.format("%-10s",abc);
            } else {
                abc = abc + str.substring(80 * j, str.length())
                        + System.lineSeparator();
                
            }
            j++;
        }
        return abc;
    }

    public static String BlankLine(String str) {
        int j = 0;
        int m = str.length() / 80;
        str = str.substring(1);
        String abc = "";
        while (j <= m) {
            if (j < m) {
                abc = abc + str.substring(80 * j, (80 * j + 80) - 1) + System.lineSeparator();
                //  abc.format("%-10s",abc);
            } else {
                abc = abc + str.substring(80 * j, str.length())
                        + System.lineSeparator() + System.lineSeparator();
                
            }
            j++;
        }
        return abc;
    }

    public static String DoubleSpaceing(String str) {
        int j = 0;
        int m = str.trim().length() / 80;
        String abc = "";
        while (j <= m) {
            if (j < m) {
                abc = abc + str.substring(80 * j, (80 * j + 80) - 1)
                        + System.lineSeparator() + System.lineSeparator();
                //  abc.format("%-10s",abc);
            } else {
                abc = abc + str.substring(80 * j, str.length())
                        + System.lineSeparator() + System.lineSeparator();
                
            }
            j++;
        }
        return abc;
    }
}
