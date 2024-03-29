package beans.propertyeditors;

import util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Locale;

public class LocaleEditor extends PropertyEditorSupport {

    public void setAsText(String text) {
        String[] parts = StringUtils.delimitedListToStringArray(text, "_");
        String language = parts.length > 0 ? parts[0] : "";
        String country = parts.length > 1 ? parts[1] : "";
        String variant = parts.length > 2 ? parts[2] : "";
        setValue(language.length() > 0 ? new Locale(language, country, variant) : null);
    }

}
