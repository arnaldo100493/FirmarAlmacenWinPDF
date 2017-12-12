// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Glyph.java

package co.com.pdf.text.pdf;


public class Glyph
{

    public Glyph(int code, int width, String chars)
    {
        this.code = code;
        this.width = width;
        this.chars = chars;
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (chars != null ? chars.hashCode() : 0);
        result = 31 * result + code;
        result = 31 * result + width;
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Glyph other = (Glyph)obj;
        if(chars == null)
        {
            if(other.chars != null)
                return false;
        } else
        if(!chars.equals(other.chars))
            return false;
        if(code != other.code)
            return false;
        return width == other.width;
    }

    public String toString()
    {
        return (new StringBuilder()).append(co/com/pdf/text/pdf/Glyph.getSimpleName()).append(" [id=").append(code).append(", width=").append(width).append(", chars=").append(chars).append("]").toString();
    }

    public final int code;
    public final int width;
    public final String chars;
}
