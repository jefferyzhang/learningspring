package beans;

/**
 * 用于持有单个Property的信息
 * @author  Rod Johnson
 * @since 13 May 2001
 * @version $Id: PropertyValue.java,v 1.1.1.1 2003/02/11 08:10:11 johnsonr Exp $
 */
public class PropertyValue {

    //---------------------------------------------------------------------
    // Instance data
    //---------------------------------------------------------------------
    /** Property name */
    private String	name;

    /** Value of the property */
    private Object value;

    //---------------------------------------------------------------------
    // Constructors
    //---------------------------------------------------------------------
    /**
     * Creates new PropertyValue
     * @param name name of the property
     * @param value value of the property (posibly before type conversion)
     */
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }


    //---------------------------------------------------------------------
    // Public methods
    //---------------------------------------------------------------------
    /**
     * Return the name of the property
     * @return the name of the property
     */
    public String getName() {
        return name;
    }

    /**
     * Return the value of the property
     * @return the value of the property. Type conversion
     * will probably not have occurred. It is the responsibility
     * of BeanWrapper implementations to perform type conversion.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Diagnostic method
     */
    public String toString() {
        return "PropertyValue: name='" + name + "'; value=[" + value + "]";
    }

    /**
     * @see Object#equals(Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof PropertyValue))
            return false;
        PropertyValue pvOther = (PropertyValue) other;
        return this == other ||
                (this.name == pvOther.name && this.value == pvOther.value);
    }

}	// class PropertyValue
