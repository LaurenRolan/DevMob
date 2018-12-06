package ensicaen.fr.calculatrice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isDigitsOnly;

public class Calculatrice extends Activity implements View.OnClickListener {
    List<Number> numbers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatrice);

        Button zero = (Button) findViewById(R.id.chiffre0);
        zero.setOnClickListener(this);
        Button one = (Button) findViewById(R.id.chiffre1);
        one.setOnClickListener(this);
        Button two = (Button) findViewById(R.id.chiffre2);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.chiffre3);
        three.setOnClickListener(this);
        Button four = (Button) findViewById(R.id.chiffre4);
        four.setOnClickListener(this);
        Button five = (Button) findViewById(R.id.chiffre5);
        five.setOnClickListener(this);
        Button six = (Button) findViewById(R.id.chiffre6);
        six.setOnClickListener(this);
        Button seven = (Button) findViewById(R.id.chiffre7);
        seven.setOnClickListener(this);
        Button eight = (Button) findViewById(R.id.chiffre8);
        eight.setOnClickListener(this);
        Button nine = (Button) findViewById(R.id.chiffre9);
        nine.setOnClickListener(this);

        Button plus = (Button) findViewById(R.id.symbPlus);
        plus.setOnClickListener(this);
        Button minus = (Button) findViewById(R.id.symbMoins);
        minus.setOnClickListener(this);
        Button div = (Button) findViewById(R.id.symbDiv);
        div.setOnClickListener(this);
        Button mult = (Button) findViewById(R.id.symbMult);
        mult.setOnClickListener(this);
        Button point = (Button) findViewById(R.id.symbPoint);
        point.setOnClickListener(this);

        Button log = (Button) findViewById(R.id.log);
        log.setOnClickListener(this);
        Button pow = (Button) findViewById(R.id.pow);
        pow.setOnClickListener(this);
        Button fat= (Button) findViewById(R.id.fatorial);
        fat.setOnClickListener(this);
        Button exp = (Button) findViewById(R.id.exp);
        exp.setOnClickListener(this);

        Button equals = (Button) findViewById(R.id.equals);
        equals.setOnClickListener(this);

        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(numbers.isEmpty())
            numbers.add(new Number());
        Number current = numbers.get(numbers.size()-1);
        TextView visor = (TextView) findViewById(R.id.visor);
        switch (v.getId()) {
            case R.id.chiffre0:
                if(!visor.getText().equals("0"))
                {
                    visor.append("0");
                    current.buildValue("0");
                    numbers.set(numbers.size()-1, current);
                }
                break;
            case R.id.chiffre1:
                if(!visor.getText().equals("0"))
                    visor.append("1");
                else visor.setText("1");
                current.buildValue("1");
                numbers.set(numbers.size()-1, current);
                break;
            case R.id.chiffre2:
                if(!visor.getText().equals("0"))
                    visor.append("2");
                else visor.setText("2");
                current.buildValue("2");
                numbers.set(numbers.size()-1, current);
                break;
            case R.id.chiffre3:
                if(!visor.getText().equals("0"))
                    visor.append("3");
                else visor.setText("3");
                current.buildValue("3");
                numbers.set(numbers.size()-1, current);
                break;
            case R.id.chiffre4:
                if(!visor.getText().equals("0"))
                    visor.append("4");
                else visor.setText("4");
                current.buildValue("4");
                numbers.set(numbers.size()-1, current);
                break;
            case R.id.chiffre5:
                if(!visor.getText().equals("0"))
                    visor.append("5");
                else visor.setText("5");
                current.buildValue("5");
                numbers.set(numbers.size()-1, current);
                break;
            case R.id.chiffre6:
                if(!visor.getText().equals("0"))
                    visor.append("6");
                else visor.setText("6");
                current.buildValue("6");
                numbers.set(numbers.size()-1, current);
                break;
            case R.id.chiffre7:
                if(!visor.getText().equals("0"))
                    visor.append("7");
                else visor.setText("7");
                current.buildValue("7");
                numbers.set(numbers.size()-1, current);
                break;
            case R.id.chiffre8:
                if(!visor.getText().equals("0"))
                    visor.append("8");
                else visor.setText("8");
                current.buildValue("8");
                numbers.set(numbers.size()-1, current);
                break;
            case R.id.chiffre9:
                if(!visor.getText().equals("0"))
                    visor.append("9");
                else visor.setText("9");
                current.buildValue("9");
                numbers.set(numbers.size()-1, current);
                break;
            case R.id.exp:
                if(!visor.getText().equals("0"))
                    visor.append("e");
                else visor.setText("e");
                current.buildValue("e");
                numbers.set(numbers.size()-1, current);
                break;
            case R.id.symbDiv:
                if(!visor.getText().toString().substring(visor.length()-1,visor.length()).matches("\\*|/|\\+|\\-|\\^") && !visor.getText().equals("0"))
                {
                    visor.append("/");
                    numbers.set(numbers.size()-1, current);
                    numbers.add(new Number('/'));
                }
                break;
            case R.id.pow:
                if(!visor.getText().toString().substring(visor.length()-1,visor.length()).matches("\\*|/|\\+|\\-|\\^") && !visor.getText().equals("0"))
                {
                    visor.append("^");
                    numbers.set(numbers.size()-1, current);
                    numbers.add(new Number('^'));
                }
                break;
            case R.id.symbMult:
                if(!visor.getText().toString().substring(visor.length()-1,visor.length()).matches("\\*|/|\\+|\\-|\\^") && !visor.getText().equals("0"))
                {
                    visor.append("*");
                    numbers.set(numbers.size()-1, current);
                    numbers.add(new Number('*'));
                }
                break;
            case R.id.symbMoins:
                if(!visor.getText().toString().substring(visor.length()-1,visor.length()).matches("\\*|/|\\+|\\-|\\^") && !visor.getText().equals("0"))
                {
                    visor.append("-");
                    numbers.set(numbers.size()-1, current);
                    numbers.add(new Number('-'));
                }
                else if(visor.getText().equals("0")) {
                    visor.setText("-");
                    current.buildValue("-");
                    numbers.set(numbers.size()-1, current);
                }
                break;
            case R.id.symbPlus:
                if(!visor.getText().toString().substring(visor.length()-1,visor.length()).matches("\\*|/|\\+|\\-|\\^") && !visor.getText().equals("0"))
                {
                    visor.append("+");
                    numbers.set(numbers.size()-1, current);
                    numbers.add(new Number('+'));
                }
                break;
            case R.id.symbPoint:
                if(!visor.getText().toString().substring(visor.length()-1,visor.length()).equals(".") && !visor.getText().equals("0"))
                    if(!current.isHasDot())
                    {
                        visor.append(".");
                        current.buildValue(".");
                        numbers.set(numbers.size()-1, current);
                    }

                break;
            case R.id.equals:
                if(!isDigitsOnly(visor.getText()))
                {
                    double result = calculateAll(numbers);
                    numbers.clear();
                    Number head = new Number();
                    head.setValue(result);
                    numbers.add(head);
                    visor.setText(String.valueOf(result));
                }
                break;
            case R.id.log:
                double result;
                if(!isDigitsOnly(visor.getText()))
                    result = calculateAll(numbers);
                else result = Double.parseDouble(visor.getText().toString());
                result = Math.log(result);
                visor.setText(String.valueOf(result));
                break;
            case R.id.fatorial:
                if(!current.isHasDot()) {
                    result = fatorial(current.getValue());
                    current.setValue(result);
                    visor.setText(String.valueOf(result));
                }
                break;
            case R.id.clear:
                visor.setText("0");
                numbers.clear();
                break;
            default:
                break;
        }
    }
    public double calculateAll(List<Number> numbers){
        char op;
        boolean first = true;
        double result = 0;
        for (Number n : numbers){
            if(first) {
                result = n.getValue();
                first = false;
            }
            else {
                op = n.getOp();
                switch (op) {
                    case '+':
                        result += n.getValue();
                        break;
                    case '-':
                        result -= n.getValue();
                        break;
                    case '*':
                        result *= n.getValue();
                        break;
                    case '/':
                        result /= n.getValue();
                        break;
                    case '^':
                        result = Math.pow(result, n.getValue());
                        break;
                    default:
                        break;
                }

            }
        }
        return result;
    }

    public double fatorial(double result){
        int initial = (int) result;
        int fat = 1;
        for(int i = initial; i > 1; i--)
            fat *= i;
        return fat;
    }
}
