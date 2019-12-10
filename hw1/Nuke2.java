/*
  Nuke2.java
  print out input string without second character
*/

import java.io.*;
import java.util.Arrays;

public class Nuke2{

	public static void main(String[] arg) throws IOException /*throw IOException is kind of mandatory if you are not going to handle it by yourself*/{
	
	System.out.println("Key in a string:");
	System.out.flush();
	BufferedReader keyboard;
	String inputLine;

	keyboard = new BufferedReader(new InputStreamReader(System.in));
	inputLine = keyboard.readLine();

	char[] charArray = inputLine.toCharArray();
	/*Character[] charObjectArray = ArrayUtils.toObject(charArray);*/

	int i = 0;
	while (i < charArray.length){
		if (i == 1){
			i++; /*continue won't work, as later i++ won't be executed*/
		}
		System.out.print(charArray[i]);
		i++;
	}

}

}
