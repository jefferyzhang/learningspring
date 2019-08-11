package beans.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * 自定义日期编辑器
 */
public class CustomDateEditor extends PropertyEditorSupport {

    private final DateFormat dateFormat;

    private final boolean allowEmpty;

    /**
     * Create a new instance, using the given DateFormat for
     * parsing and rendering.
     * <p>The allowEmpty parameter states if an empty String should
     * be allowed for parsing, i.e. get interpreted as null value.
     * Else, an IllegalArgumentException gets thrown in that case.
     * @param dateFormat DateFormat to use for parsing and rendering
     * @param allowEmpty if empty strings should be allowed
     */
    public CustomDateEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    /**
     * Parse the Date from the given text, using the specified DateFormat.
     */
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && text.trim().equals("")) {
            // treat empty String as null value
            setValue(null);
        }
        else {
            try {
                setValue(this.dateFormat.parse(text));
            }
            catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage());
            }
        }
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    public String getAsText() {
        return this.dateFormat.format((Date) getValue());
    }

}
