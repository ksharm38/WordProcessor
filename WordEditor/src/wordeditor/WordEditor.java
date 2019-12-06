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
 * @author Kunal Sharma
 */
public class WordEditor {

    /**
     * @param args the command line arguments
     */
    String history = "";

    public String readFileAsString(String fileName) throws Exception {
        String data;
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;//.replaceAll("\r\n", "");
    }

    public String FormatString(String data) {
        try {
            char c;
            List<String> operation = new ArrayList<String>();
            String str;
            String output = "";
            String splittedStringArray[] = data.split("\n-");
            int count = splittedStringArray.length;
            for (int i = 0; i < count; i++) {
                str = splittedStringArray[i].replace("\r", "").replace("-", "");
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
            return output;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "";
    }

    public String ChangeFormatOfString(List<String> list, String text) throws InvalidCommandError {
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
                        text = SingleLine(text);
                        break;
                    case 'd':
                        text = DoubleSpaceing(text);
                        break;
                    case 'n':
                        text = AlignLeft(text);
                        break;
                    case '1':
                        text = AlignLeft(text);
                        break;
                    case 'l':
                        text = AlignLeft(text);
                        break;
                    case 't':
                        text = Title(text);
                        break;
                    case '2':
                        text = TwoColumns(text);
                        break;

                    default:
                        throw new InvalidCommandError("Invalid Input Command");

                }
            }
            return text;
        } catch (InvalidCommandError error) {
            return error.getMessage();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "";
    }

    public int SaveFile(String text, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(text);

            writer.close();
            return 1;
        } catch (IOException e) {
            return 2;
            //e.printStackTrace();
        }

    }

    public String AlignRight(String str) {
        str = str.replace("\n", "");
        int j = 0;
        int m = str.length() / 80;
        int iterator = 0;
        char curr = ' ';

        String abc = "";
        while (j <= m) {
            iterator = 80;
            if (str.length() > 80) {
                j++;
                if (str.charAt(79) == ' ') {
                    abc += str.substring(0, 79) + "\n";
                    str = str.substring(80);
                } else if (str.charAt(80) == ' ') {
                    abc += str.substring(0, 80) + "\n";
                    str = str.substring(81);
                } else {
                    do {
                        iterator--;
                        curr = str.charAt(iterator);
                    } while (curr != ' ');

                    for (int i = 0; i < (80 - iterator); i++) {
                        abc += " ";
                    }
                    abc += str.substring(0, iterator) + "\n";
                    str = str.substring(iterator + 1);
                }
            } else {
                j++;
                for (int i = 0; i < (80 - str.length()); i++) {
                    abc += " ";
                }
                abc += str.substring(0, str.length()) + "\n";
            }
        }

        history = "right";
        return abc;
    }

    public String AlignLeft(String str) {
        str = str.replace("\n", "");
        int j = 0;
        int m = str.length() / 80;
        int iterator = 80;
        char curr = ' ';
        String abc = "";

        while (j <= m) {
            if (str.length() > 80) {
                j++;
                if (str.charAt(79) == ' ') {
                    abc += str.substring(0, 79) + "\n";
                    str = str.substring(80);
                } else if (str.charAt(80) == ' ') {
                    abc += str.substring(0, 80) + "\n";
                    str = str.substring(81);
                } else {
                    do {
                        iterator--;
                        curr = str.charAt(iterator);
                    } while (curr != ' ');

                    abc += str.substring(0, iterator) + "\n";
                    str = str.substring(++iterator);
                }
            } else {
                abc += str + "\n";
                j++;
            }
        }
        history = "left";
        return abc;
    }

    public String AlignCenter(String str) {
        str = str.replace("\n", "");
        int i = 0;
        int j = 0;
        int m = str.length() / 80;
        int iterator = 80;
        char curr = ' ';
        String abc = "";
        String space_to_center = "";
        while (j <= m) {
            if (str.length() > 80) {
                j++;
                if (str.charAt(79) == ' ') {
                    abc += str.substring(0, 79) + "\n";
                    str = str.substring(80);
                } else if (str.charAt(80) == ' ') {
                    abc += str.substring(0, 80) + "\n";
                    str = str.substring(81);
                } else {
                    do {
                        iterator--;
                        curr = str.charAt(iterator);
                    } while (curr != ' ');

                    space_to_center = "";
                    for (i = str.substring(0, iterator).length(); i < 80; i += 2) {
                        space_to_center += " ";
                    }
                    abc += space_to_center + str.substring(0, iterator) + "\n";
                    str = str.substring(++iterator);
                }
            } else {
                space_to_center = "";
                for (i = str.length(); i < 80; i += 2) {
                    space_to_center += " ";
                }
                abc += space_to_center + str + "\n";
                j++;
            }
        }
        history = "center";
        return abc;
    }

    public String NIndentation(String str) {
        str = str.replace("\n", "");
        int j = 0;
        int m = str.length() / 80;
        int iterator = 80;
        char curr = ' ';
        String abc = "";

        while (j <= m) {
            if (str.length() > 80) {
                j++;
                if (str.charAt(79) == ' ') {
                    abc += str.substring(0, 79) + "\n";
                    str = str.substring(80);
                } else if (str.charAt(80) == ' ') {
                    abc += str.substring(0, 80) + "\n";
                    str = str.substring(81);
                } else {
                    do {
                        iterator--;
                        curr = str.charAt(iterator);
                    } while (curr != ' ');

                    abc += str.substring(0, iterator) + "\n";
                    str = str.substring(++iterator);
                }
            } else {
                abc += str + "\n";
                j++;
            }
        }
        history = "left";
        return abc;
    }

    public String BIndentation(String str) {
        str = str.replace("\n", "");
        int j = 0;
        int m = str.length() / 70;
        int iterator = 70;
        char curr = ' ';
        String abc = "";

        while (j <= m) {
            if (str.length() > 70) {
                j++;
                if (str.charAt(69) == ' ') {
                    abc += "          " + str.substring(0, 69) + "\n";
                    str = str.substring(70);
                } else if (str.charAt(70) == ' ') {
                    abc += "          " + str.substring(0, 70) + "\n";
                    str = str.substring(71);
                } else {
                    do {
                        iterator--;
                        curr = str.charAt(iterator);
                    } while (curr != ' ');

                    abc += "          " + str.substring(0, iterator) + "\n";
                    str = str.substring(++iterator);
                }
            } else {
                abc += "          " + str + "\n";
                j++;
            }
        }
        history = "left";
        return abc;
    }

    public String IIndentation(String str) {
        str = str.replace("\n", "");
        str = str.trim();

        int j = 0;
        int m = str.length() / 80;
        int iterator = 75;
        char curr = ' ';
        String abc = "     ";
        //str = str.substring(75);

        while (j <= m) {
            if (str.length() > 80) {
                j++;
                if (str.charAt(74) == ' ') {
                    abc += str.substring(0, 74) + "\n";
                    str = str.substring(75);
                } else if (str.charAt(75) == ' ') {
                    abc += str.substring(0, 75) + "\n";
                    str = str.substring(76);
                } else {
                    do {
                        iterator--;
                        curr = str.charAt(iterator);
                    } while (curr != ' ');
                    abc += str.substring(0, iterator) + "\n";
                    str = str.substring(++iterator);
                }
            } else {
                abc += str + "\n";
                j++;
            }
        }
        history = "left";
        return abc;
    }

    public String BlankLine(String str) {
        String abc = "";
        if (history.equals("right")) {
            abc = "\n" + AlignRight(str);
        }
        if (history.equals("left")) {
            abc = "\n" + AlignLeft(str);
        }
        if (history.equals("center")) {
            abc = "\n" + AlignCenter(str);
        }
        return abc;
    }

    public String DoubleSpaceing(String str) {
                String abc = "";
        try {
        str = str.replace("\n", "");
        int j = 0, i = 0;
        int m = str.length() / 80;
        int iterator = 80;
        char curr = ' ';
        String space_to_center = "";

        if (history.equals("right")) {
            while (j <= m) {
                iterator = 80;
                if (str.length() > 80) {
                    j++;
                    if (str.charAt(79) == ' ') {
                        abc += str.substring(0, 79) + "\n" + "\n";
                        str = str.substring(80);
                    } else if (str.charAt(80) == ' ') {
                        abc += str.substring(0, 80) + "\n" + "\n";
                        str = str.substring(81);
                    } else {
                        do {
                            iterator--;
                            curr = str.charAt(iterator);
                        } while (curr != ' ');

                        for (i = 0; i < (80 - iterator); i++) {
                            abc += " ";
                        }
                        abc += str.substring(0, iterator) + "\n" + "\n";
                        str = str.substring(iterator + 1);
                    }
                } else {
                    j++;
                    for (i = 0; i < (80 - str.length()); i++) {
                        abc += " ";
                    }
                    abc += str.substring(0, str.length()) + "\n" + "\n";
                }
            }
        } else if (history.equals("center")) {
            while (j <= m) {
                if (str.length() > 80) {
                    j++;
                    if (str.charAt(79) == ' ') {
                        abc += str.substring(0, 79) + "\n\n";
                        str = str.substring(80);
                    } else if (str.charAt(80) == ' ') {
                        abc += str.substring(0, 80) + "\n\n";
                        str = str.substring(81);
                    } else {
                        do {
                            iterator--;
                            curr = str.charAt(iterator);
                        } while (curr != ' ');

                        space_to_center = "";
                        for (i = str.substring(0, iterator).length(); i < 80; i += 2) {
                            space_to_center += " ";
                        }
                        abc += space_to_center + str.substring(0, iterator) + "\n\n";
                        str = str.substring(++iterator);
                    }
                } else {
                    space_to_center = "";
                    for (i = str.length(); i < 80; i += 2) {
                        space_to_center += " ";
                    }
                    abc += space_to_center + str + "\n\n";
                    j++;
                }
            }
        } else {
            while (j <= m) {
            if (str.length() > 80) {
                j++;
                if (str.charAt(79) == ' ') {
                    abc += str.substring(0, 79) + "\n\n";
                    str = str.substring(80);
                } else if (str.charAt(80) == ' ') {
                    abc += str.substring(0, 80) + "\n\n";
                    str = str.substring(81);
                } else {
                    do {
                        iterator--;
                        curr = str.charAt(iterator);
                    } while (curr != ' ');

                    abc += str.substring(0, iterator) + "\n\n";
                    str = str.substring(++iterator);
                }
            } else {
                abc += str + "\n\n";
                j++;
            }
        }
        }
        }
    catch (Exception e) {
        System.out.println(e);
    }
        return abc;
    }

    public String TwoColumns(String str) {
        str = str.replace("\n", "");
        boolean var = false;
        String abc = "";
        if (str.length() <= 35) {
            abc = str.substring(0, str.length()) + "\n";
        } else if (str.length() < 70 && str.length() > 35) {
            abc = str.substring(0, 35) + "          " + str.substring(35, str.length()) + "\n";
        } else {
            double j = str.length() / 35.0;
            int m = 0;
            if (j % 1 == 0) {
                m = (int) j;
                var = true;
            } else {
                m = (int) (j + 1);
            }
            int n = m / 2;
            String[] array = new String[m];
            if (str.length() < 35) {
                array[0] = str.substring(0, str.length());
            } else {
                for (int i = 0; i < m; i++) {

                    if (str.length() - (i * 35) <= 35) {
                        array[i] = str.substring(i * 35, str.length());
                    } else {
                        array[i] = str.substring(i * 35, i * 35 + 35);
                    }
                }
            }
            if (var) {
                for (int i = 0; i <= n - 1; i++) {

                    if (array[i] != null) {
                        abc += array[i] + "          ";
                        if (n + i < array.length) {
                            abc += array[n + i] + "\n";
                        }
                    }
                }
            } else {
                for (int i = 0; i <= n; i++) {

                    if (array[i] != null) {
                        abc += array[i] + "          ";
                        if (n + i < array.length - 1) {
                            abc += array[n + i + 1] + "\n";
                        }
                    }
                }
            }
            if (array.length % 2 != 0) {
                abc += "\n";
            }
        }
        return abc;
    }

    public String Title(String str) {
        int j = 0;
        int m = str.length() / 80;
        String abc = "";

        abc = str.split("\n")[1];
        abc = StringUtils.center(abc.substring(0, abc.length()), 80) + System.lineSeparator();
        str = str.substring(abc.length() - 1, str.length());
        abc += AlignLeft(str);

        return abc;
    }

    public String SingleLine(String str) {
        String abc = "";
        if (history.equals("right")) {
            abc = AlignRight(str);
        }
        if (history.equals("left")) {
            abc = AlignLeft(str);
        }
        if (history.equals("center")) {
            abc = AlignCenter(str);
        }
        return abc;
    }
}
