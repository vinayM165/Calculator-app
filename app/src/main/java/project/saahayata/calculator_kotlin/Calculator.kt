package project.saahayata.calculator_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class Calculator : AppCompatActivity() {
    lateinit var display: TextView
    var lastDigit = false
    var error = false
    var preDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display=findViewById(R.id.txtInput)
    }

    fun onDigit(view: View)
    {
     if(error){
         display.text = ""
         display.text = (view as Button).text
     }else{
         display.append((view as Button).text)
     }
        lastDigit = true
    }

    fun onDecimalPoint(view: View)
    {
        if(lastDigit && !preDot && !error){
            display.append(".")
            preDot = true
        }else{
            Toast.makeText(baseContext,"invalid action 404",Toast.LENGTH_LONG).show();
        }

    }

    fun onOperator (view:View)
    {
        if(lastDigit && !error){
            display.append((view as Button).text)
            preDot = false
        }else{
        Toast.makeText(baseContext,"invalid action 404",Toast.LENGTH_LONG).show();
    }
    }


    fun onClear(view:View)
    {
        display.text = ""
        lastDigit = false
         error = false
         preDot = false
    }
    fun onEqual(view:View)
    {
        var str : String = display.text.toString()

        try {
            val exp = ExpressionBuilder(str).build()
            val result = exp.evaluate()
            display.text = result.toString()

        }catch (e : Exception){
            display.text = "error"
            error = true
            }
        }
    fun onDel(view : View){
        if(lastDigit) {
            var index = (display.text).lastIndex - 1
            var res = (display.text).substring(0..index)
            display.text = res
        }else{
            Toast.makeText(baseContext,"invalid action 404",Toast.LENGTH_LONG).show()
        }
    }

    }

