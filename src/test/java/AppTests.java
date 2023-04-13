import org.example.AppParser;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public  class AppTests {

    @Test
    public void test1() throws IOException {
        String[] args = {"журавли", "src/test/files/test1"};
        FileOutputStream f1 = new FileOutputStream("f1.txt");
        System.setOut(new PrintStream(f1));
        AppParser appParser = new AppParser();
        appParser.launch(args);
        BufferedReader br = new BufferedReader(new FileReader("f1.txt"));
        List<String> actual = br.lines().toList();
        List<String> expected = List.of("журавли летят на юг по белому небу");
        br.close();
        assertEquals(expected, actual);
    }

    @Test
    public void test2() throws IOException {
        String[] args = {"\\d{4}", "src/test/files/test2"};
        FileOutputStream f2 = new FileOutputStream("f2.txt");
        System.setOut(new PrintStream(f2));
        AppParser appParser = new AppParser();
        appParser.launch(args);
        BufferedReader br = new BufferedReader(new FileReader("f2.txt"));
        List<String> actual = br.lines().toList();
        List<String> expected = List.of("1223 - битва на реке Калке", "1380 - Куликовская битва",
                "1703 - основание Санкт-Петербурга", "1812 - Бородинская битва", "1922 - образование СССР");
        br.close();
        assertEquals(expected, actual);
    }

    @Test
    public void test3() throws IOException {
        String[] args = {"-i", "яблоки", "src/test/files/test3"};
        FileOutputStream f3 = new FileOutputStream("f3.txt");
        System.setOut(new PrintStream(f3));
        AppParser appParser = new AppParser();
        appParser.launch(args);
        BufferedReader br = new BufferedReader(new FileReader("f3.txt"));
        List<String> actual = br.lines().toList();
        List<String> expected = List.of("Груши и яблоки", "Яблоки разных цветов лежат в ящике",
                "ЯБЛОКИ НА СНЕГУ");
        br.close();
        assertEquals(expected, actual);
    }

    @Test
    public void test4() throws IOException {
        String[] args = {"-v", "Вася", "src/test/files/test4"};
        FileOutputStream f4 = new FileOutputStream("f4.txt");
        System.setOut(new PrintStream(f4));
        AppParser appParser = new AppParser();
        appParser.launch(args);
        BufferedReader br = new BufferedReader(new FileReader("f4.txt"));
        List<String> actual = br.lines().toList();
        List<String> expected = List.of("999", "Привет мир");
        br.close();
        assertEquals(expected, actual);
    }

    @Test
    public void test5() throws IOException {
        String[] args = {"-v", "-i", "лебеди", "src/test/files/test5"};
        FileOutputStream f5 = new FileOutputStream("f5.txt");
        System.setOut(new PrintStream(f5));
        AppParser appParser = new AppParser();
        appParser.launch(args);
        BufferedReader br = new BufferedReader(new FileReader("f5.txt"));
        List<String> actual = br.lines().toList();
        List<String> expected = List.of("йцукен", "qwerty", "777");
        br.close();
        assertEquals(expected, actual);
    }

    @Test
    public void test6() throws IOException {
        String[] args = {"-v", "\\d{4}", "src/test/files/test2"};
        FileOutputStream f6 = new FileOutputStream("f6.txt");
        System.setOut(new PrintStream(f6));
        AppParser appParser = new AppParser();
        appParser.launch(args);
        BufferedReader br = new BufferedReader(new FileReader("f6.txt"));
        List<String> actual = br.lines().toList();
        List<String> expected = List.of("Шпаргалка по истории", "Школьный курс");
        br.close();
        assertEquals(expected, actual);
    }

    @Test
    public void testException1() throws IOException {
        FileOutputStream f7 = new FileOutputStream("f7.txt");
        System.setErr(new PrintStream(f7));
        String[] args = {"abc", "input/file"};
        AppParser appParser = new AppParser();
        appParser.launch(args);
        BufferedReader br = new BufferedReader(new FileReader("f7.txt"));
        String message = br.readLine();
        br.close();
        assertEquals("Such file not found", message);
    }

    @Test
    public void testException2() throws IOException {
        FileOutputStream f8 = new FileOutputStream("f8.txt");
        System.setErr(new PrintStream(f8));
        String[] args = {"s", "-r", "t", "hh", "777", "src/test/files/test1"};
        AppParser appParser = new AppParser();
        appParser.launch(args);
        BufferedReader br = new BufferedReader(new FileReader("f8.txt"));
        String message = br.readLine();
        br.close();
        assertEquals("Incorrect command line", message);
    }
}
