package org.tyrest.asi.face.service.enums;

/** 
 * /pre&gt;
 */
public enum ASIType {

    TABLE(0),
    FORM(1);
    private int value;

    /**
     *
     * @param type
     */
    private ASIType(int type)
    {
        this.value = type;
    }

    /**
     *
     * @return
     */
    public int getValue()
    {
        return value;
    }

    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        String aSITypes;
        switch (this)
        {
            case FORM:
                aSITypes = "表单";
                break;
            case TABLE:
                aSITypes = "表格";
                break;
            default:
                aSITypes = "";
        }

        return aSITypes;
    }

    public static ASIType getASIType(String str)
    {
    	ASIType asiTypes = null;
        for (ASIType ut : ASIType.values())
        {
            if (ut.name().equals( str))
            {
                asiTypes = ut;
                break;
            }
        }
        return asiTypes;
    }

    public String getProcessorName()
    {
        String name;
        switch (this)
        {
            case FORM:
                name = "form";
                break;
            case TABLE:
                name = "table";
                break;
            default:
                name = "";
        }
        return name;
    }
}
