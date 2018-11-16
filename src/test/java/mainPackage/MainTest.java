package mainPackage;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void noFileNameTest() {

        String args[] = {};

        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buff));

        Main.main(args);
        assertEquals("File name must not be empty", buff.toString());
    }

    @Test
    public void emptyFileNameTest() {

        String args[] = {""};

        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buff));

        Main.main(args);
        assertEquals("File name must not be empty", buff.toString());
    }
}