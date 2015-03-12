import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.io.StringReader;

import org.junit.Test;


public class mainTest {

	@Test
	public void testMain() throws NumberFormatException, IOException {
		String[] args = new String[1];
		String testInput = "1\n5 12 1 12 4 4\nacccdadceb\n2";
		InputStream in = new ByteArrayInputStream(testInput.getBytes());
		System.setIn(in);
		main.main(args);
		fail("Not yet implemented");
	}

}
