package lab.logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Logger 
{
	//static final String logFileName = "D:\\Eclipse Project\\PPlab3\\BattleLog.txt";
	static FileWriter fileWriter = null;
	static BufferedWriter bufferedWriter = null;
	static FileReader fileReader = null;
    static BufferedReader bufferedReader = null;
	
    public static void openBufferRead() 
    {
		try {
			fileReader = new FileReader(System.getProperty("user.dir") + "\\BattleLog.txt");
			bufferedReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public static void closeBufferRead()
	{
		try {
			fileReader.close();
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	public static void closeBufferWrite()
	{
		try {
			fileWriter.close();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readInfoFromLog() 
	{
		String info = new String("");
		String line;
        try {
			while ((line = bufferedReader.readLine()) != null) {
				line += "\n";
				info += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        return info;
	}
	
	public static void openBufferWrite() 
	{
		try {
			fileWriter = new FileWriter(System.getProperty("user.dir") + "\\BattleLog.txt");
	        bufferedWriter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addInfoInLog(String info) 
	{
        try {
        	fileWriter.write(info + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
