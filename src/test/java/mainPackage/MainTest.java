package mainPackage;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
        assertEquals("Command line should not be empty, enter -help for reference", buff.toString());
    }

    @Test
    public void emptyFileNameTest() {
        String args[] = {""};
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buff));
        Main.main(args);
        assertEquals("Command line should not be empty, enter -help for reference", buff.toString());
    }

    @Test
    public void optionsTest1() {
        String args[] = {"Rules.txt"};
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buff));
        Main.main(args);
        String resultString = buff.toString();
        assertTrue(resultString.contains("Error:"));
    }

    @Test
    public void optionsTest2() {
        String args[] = {"-f"};
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buff));
        Main.main(args);
        assertEquals("Missing argument for option: f", buff.toString());
    }

    @Test
    public void optionsTest3() {
        String args[] = {"-b"};
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buff));
        Main.main(args);
        assertEquals("Missing argument for option: b", buff.toString());
    }

    @Test
    public void optionsTest4() {
        String args[] = {"-help"};
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buff));
        Main.main(args);
        String resultString = buff.toString();
        assertTrue(resultString.contains("to launch the application you can use the following commands:"));
    }

    @Test
    public void optionsTest5() {
        String args[] = {"-ftarget/test_resources/true_Format_File_Tests/trueRecordFileTest3", "-btarget/test_resources/true_Format_File_Tests/trueRecordFileTest3"};
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buff));
        Main.main(args);
        String result = buff.toString();
        assertEquals("The option 'b' was specified but an option from this group has already been selected: 'f'", buff.toString());

    }
    @Test
    public void optionsTest6() {
        String args[] = {"-ftarget/test_resources/true_Format_File_Tests/trueRecordFileTest3"};
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buff));
        Main.main(args);
        String result = buff.toString();
        assertEquals("TestFact1,TestFact3", buff.toString());

    }
    @Test
    public void optionsTest7() {
        String args[] = {"-btarget/test_resources/db_parser_rule_mapper_tests/postgresql_mapper_tests.jdbc.properties"};
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buff));
        Main.main(args);
        String result = buff.toString();
        assertEquals("A,C,D,E", buff.toString());

    }


}