// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonStringImpl.java

package org.glassfish.json;

import javax.json.JsonString;
import javax.json.JsonValue;

final class JsonStringImpl
    implements JsonString
{

    JsonStringImpl(String value)
    {
        this.value = value;
    }

    public String getString()
    {
        return value;
    }

    public CharSequence getChars()
    {
        return value;
    }

    public javax.json.JsonValue.ValueType getValueType()
    {
        return javax.json.JsonValue.ValueType.STRING;
    }

    public int hashCode()
    {
        return getString().hashCode();
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof JsonString))
        {
            return false;
        } else
        {
            JsonString other = (JsonString)obj;
            return getString().equals(other.getString());
        }
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append('"');
        for(int i = 0; i < value.length(); i++)
        {
            char c = value.charAt(i);
            if(c >= ' ' && c <= '\0' && c != '"' && c != '\\')
            {
                sb.append(c);
                continue;
            }
            switch(c)
            {
            case 34: // '"'
            case 92: // '\\'
                sb.append('\\');
                sb.append(c);
                break;

            case 8: // '\b'
                sb.append('\\');
                sb.append('b');
                break;

            case 12: // '\f'
                sb.append('\\');
                sb.append('f');
                break;

            case 10: // '\n'
                sb.append('\\');
                sb.append('n');
                break;

            case 13: // '\r'
                sb.append('\\');
                sb.append('r');
                break;

            case 9: // '\t'
                sb.append('\\');
                sb.append('t');
                break;

            default:
                String hex = (new StringBuilder()).append("000").append(Integer.toHexString(c)).toString();
                sb.append("\\u").append(hex.substring(hex.length() - 4));
                break;
            }
        }

        sb.append('"');
        return sb.toString();
    }

    private final String value;
}
