package beans.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;
import java.text.ParseException;

public class CustomNumberEditor extends PropertyEditorSupport {

    private Class numberClass;

    private NumberFormat numberFormat;

    private final boolean allowEmpty;

    /**
     * Create a new instance, using the given NumbreFormat for
     * parsing and rendering.
     * <p>The allowEmpty parameter states if an empty String should
     * be allowed for parsing, i.e. get interpreted as null value.
     * Else, an IllegalArgumentException gets thrown in that case.
     * @param numberClass Number subclass to generate
     * @param numberFormat NumberFormat to use for parsing and rendering
     * @param allowEmpty if empty strings should be allowed
     * @throws IllegalArgumentException if an invalid numberClass has been specified
     */
    public CustomNumberEditor(Class numberClass, NumberFormat numberFormat, boolean allowEmpty)
            throws IllegalArgumentException {
        if (!Number.class.isAssignableFrom(numberClass)) {
            throw new IllegalArgumentException("Property class must be a subclass of Number");
        }
        this.numberClass = numberClass;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && text.trim().equals("")) {
            setValue(null);
        }
        else {
            try {
                Number number = this.numberFormat.parse(text);
                if (this.numberClass.isInstance(number)) {
                    setValue(number);
                }
                else if (this.numberClass.equals(Long.class)) {
                    setValue(new Long(number.longValue()));
                }
                else if (this.numberClass.equals(Integer.class)) {
                    setValue(new Integer(number.intValue()));
                }
                else if (this.numberClass.equals(Double.class)) {
                    setValue(new Double(number.doubleValue()));
                }
                else if (this.numberClass.equals(Float.class)) {
                    setValue(new Float(number.floatValue()));
                }
                else {
                    throw new IllegalArgumentException("Cannot convert [" + text + "] to [" + this.numberClass + "]");
                }
            }
            catch (ParseException ex) {
                throw new IllegalArgumentException("Cannot parse number: " + ex.getMessage());
            }
        }
    }

    public String getAsText() {
        return this.numberFormat.format(getValue());
    }

}
