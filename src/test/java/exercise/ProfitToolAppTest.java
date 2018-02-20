package exercise;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class ProfitToolAppTest {

	@Test
	public void testMAinFailed() {

        String expectedErrorMessage = ProfitToolApp.USAGE_MESSAGE;
        //System.out.println(expectedErrorMessage.toString());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        String[] args = {"some incorrect params"};
        ProfitToolApp.main(args);
      
        assertEquals(expectedErrorMessage, outContent.toString());
        System.out.println(outContent.toString());
	}
	
	@Test
	public void testMainTrue() {

        String expectedErrorMessage = ProfitToolApp.USAGE_MESSAGE;
        //System.out.println(expectedErrorMessage.toString());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        String[] args = {"-file \"C:\\Workspaces\\AQR-Exercise\\arman\\src\\main\\resources\\TestData.csv\" -header true -datastartrow 9"};
        ProfitToolApp.main(args);
      
        assertEquals(expectedErrorMessage, outContent.toString());
        System.out.println(outContent.toString());
	}

}
