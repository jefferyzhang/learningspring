import beans.propertyeditors.CustomDateEditor;
import beans.propertyeditors.CustomNumberEditor;
import beans.propertyeditors.PropertiesEditor;

import java.text.DateFormat;
import java.text.NumberFormat;

public class Main {

    public static void main(String[] args) {
        CustomNumberEditor numberEditor = new CustomNumberEditor(Float.class, NumberFormat.getPercentInstance(), false);
        numberEditor.setAsText("10%");

        System.out.println(numberEditor.getValue());
    }
}
