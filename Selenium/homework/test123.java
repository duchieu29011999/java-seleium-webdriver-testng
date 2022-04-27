package homework;

import java.util.ArrayList;
import java.util.List;

public class test123 {

	public static double getAverage (List<? extends Number> numberList)
	{
		double total = 0.0; 
		for (Number number : numberList)
		total += number.doubleValue();
		return total / numberList.size();
	}
	public static void main(String[] args) {
		
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
	}
}
