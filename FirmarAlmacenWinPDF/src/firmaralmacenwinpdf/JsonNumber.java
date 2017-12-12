package firmaralmacenwinpdf;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;

public final class JsonNumber
  extends Number
{
  private final String rawValue;
  
  public JsonNumber(String s)
  {
    if (s == null) {
      throw new NullPointerException();
    }
    if (!SYNTAX.matcher(s).matches()) {
      throw new IllegalArgumentException("Invalid number syntax");
    }
    this.rawValue = s;
  }
  
  public byte byteValue()
  {
    return Byte.parseByte(this.rawValue);
  }
  
  public short shortValue()
  {
    return Short.parseShort(this.rawValue);
  }
  
  public int intValue()
  {
    return Integer.parseInt(this.rawValue);
  }
  
  public long longValue()
  {
    return Long.parseLong(this.rawValue);
  }
  
  public float floatValue()
  {
    return Float.parseFloat(this.rawValue);
  }
  
  public double doubleValue()
  {
    return Double.parseDouble(this.rawValue);
  }
  
  public BigInteger bigIntegerValue()
  {
    return new BigInteger(this.rawValue);
  }
  
  public BigDecimal bigDecimalValue()
  {
    String[] parts = this.rawValue.split("[eE]");
    BigDecimal result = new BigDecimal(parts[0]);
    if (parts.length == 2) {
      result = result.movePointRight(Integer.parseInt(parts[1]));
    }
    return result;
  }
  
  public boolean equals(Object obj)
  {
    if (!(obj instanceof JsonNumber)) {
      return false;
    }
    return this.rawValue.equals(((JsonNumber)obj).rawValue);
  }
  
  public int hashCode()
  {
    return this.rawValue.hashCode();
  }
  
  public String toString()
  {
    return this.rawValue;
  }
  
  static Pattern SYNTAX = Pattern.compile("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?");
}
