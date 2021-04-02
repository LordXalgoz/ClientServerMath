package server;

import java.util.Random;

public class Controller
{
    private Random rnd = new Random();

    private char[] mathSigns;
    private int firstNumber = rnd.nextInt(10);;
    private int secondNumber = rnd.nextInt(10);
    private int mathSign;

    public Controller()
    {
        mathSigns = new char[]{'+', '-', '*', '/'};
        mathSign = rnd.nextInt(mathSigns.length);
    }

    public String GenerateMathExample()
    {
        String output = "";
        output += firstNumber+" "+mathSigns[mathSign]+" "+secondNumber+" = ?";
        System.out.println(output);
        return output;
    }

    private int Calculate(int sign)
    {
        int result;
        switch (sign)
        {
            case 0:
                result=firstNumber+secondNumber;
                break;
            case 1:
                result=firstNumber-secondNumber;
                break;
            case 2:
                result=firstNumber*secondNumber;
                break;
            case 3:
                result=firstNumber/secondNumber;
                break;
        }

        return sign;
    }

    public String CheckAnswer(String answer)
    {
        int result = Integer.getInteger(answer);
        if(result == Calculate(mathSigns[mathSign]))
        {
            System.out.println("Correct");
        }
        else
        {
            System.out.println("Incorrect");
        }
        return answer;
    }
}
