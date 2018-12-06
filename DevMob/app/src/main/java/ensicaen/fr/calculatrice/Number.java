package ensicaen.fr.calculatrice;
import android.text.TextUtils;

import static android.text.TextUtils.isDigitsOnly;

public class Number {
    private boolean hasDot;
    private double value;
    private String string;
    private char op;

    public Number()
    {
        value = 0;
        string = "";
    }

    public Number(char op)
    {
        value = 0;
        string = "";
        this.op = op;
    }

    public boolean isHasDot() {
        return hasDot;
    }

    public double getValue() {
        return value;
    }

    public char getOp() {
        return op;
    }

    public void setOp(char op){
        this.op = op;
    }

    public void setValue(double value) {
        this.value = value;
        this.string = String.valueOf(value);
    }

    public void buildValue(CharSequence carac){
        System.out.println(carac);
        if(value == 0 && isDigitsOnly(carac))
        {
            string = carac.toString();
            value = Double.parseDouble(string);
        }
        else if(value == 0 && carac.equals("."))
        {
            string = string + "0.";
            hasDot = true;
        }
        else if(value == 0 && carac.equals("-"))
        {
            string = string + "-";
        }
        else if(value != 0 && isDigitsOnly(carac))
        {
            string = string + carac;
            value = Double.parseDouble(string);
        }
        else if(value == 0  && carac.equals("e"))
        {
            string = string + String.valueOf(Math.E);
            System.out.printf(string);
            value = Double.parseDouble(string);
        }
    }
}
