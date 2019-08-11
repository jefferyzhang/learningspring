import beans.propertyeditors.CustomDateEditor;
import beans.propertyeditors.CustomNumberEditor;
import beans.propertyeditors.PropertiesEditor;
import beans.propertyeditors.PropertyValuesEditor;

import java.text.DateFormat;
import java.text.NumberFormat;

public class Main {

    public static void main(String[] args) {
        PropertyValuesEditor propertyValuesEditor = new PropertyValuesEditor();
        propertyValuesEditor.setAsText("name=jeffery\r\nage=20");

        System.out.println(propertyValuesEditor.getValue());
    }
}
