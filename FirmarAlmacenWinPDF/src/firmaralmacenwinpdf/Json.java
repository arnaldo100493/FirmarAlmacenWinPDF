package firmaralmacenwinpdf;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public final class Json
{
  public static String serialize(Object obj)
  {
    StringBuilder sb = new StringBuilder();
    try
    {
      serializeJson(obj, sb);
    }
    catch (IOException e)
    {
      throw new AssertionError(e);
    }
    return sb.toString();
  }
  
  private static void serializeJson(Object obj, Appendable out)
    throws IOException
  {
    if ((obj == null) || ((obj instanceof Boolean)))
    {
      out.append(String.valueOf(obj));
    }
    else if ((obj instanceof Number))
    {
      if (((obj instanceof Float)) || ((obj instanceof Double)))
      {
        double x = ((Number)obj).doubleValue();
        if ((Double.isInfinite(x)) || (Double.isNaN(x))) {
          throw new IllegalArgumentException("Cannot serialize infinite/NaN floating-point value");
        }
      }
      String temp = obj.toString();
      if (!JsonNumber.SYNTAX.matcher(temp).matches()) {
        throw new IllegalArgumentException("Number string cannot be serialized as JSON: " + temp);
      }
      out.append(temp);
    }
    else
    {
      int i;
      if ((obj instanceof CharSequence))
      {
        if (((obj instanceof List)) || ((obj instanceof Map))) {
          throw new IllegalArgumentException("Ambiguous object is both charseq and list/map");
        }
        CharSequence str = (CharSequence)obj;
        out.append('"');
        for (i = 0; i < str.length(); i++)
        {
          char c = str.charAt(i);
          switch (c)
          {
          case '\b': 
            out.append("\\b"); break;
          case '\f': 
            out.append("\\f"); break;
          case '\n': 
            out.append("\\n"); break;
          case '\r': 
            out.append("\\r"); break;
          case '\t': 
            out.append("\\t"); break;
          case '"': 
            out.append("\\\""); break;
          case '\\': 
            out.append("\\\\"); break;
          default: 
            if ((c >= ' ') && (c < '')) {
              out.append(c);
            } else {
              out.append(String.format("\\u%04X", new Object[] { Integer.valueOf(c) }));
            }
            break;
          }
        }
        out.append('"');
      }
      else
      {
        Object sub;
        if ((obj instanceof List))
        {
         if(obj instanceof Map)
                throw new IllegalArgumentException("Ambiguous object is both list and map");
            out.append('[');
            boolean head = true;
            for(Iterator iterator = ((List)obj).iterator(); iterator.hasNext(); serializeJson(sub, out))
            {
                sub = iterator.next();
                if(head)
                    head = false;
                else
                    out.append(", ");
            }

            out.append(']');
        }
        else if ((obj instanceof Map))
        {
          out.append('{');
          boolean head = true;
          Map map = (Map)obj;
          for (sub = map.entrySet().iterator(); ((Iterator)sub).hasNext();)
          {
            Object temp = ((Iterator)sub).next();
            Map.Entry entry = (Map.Entry)temp;
            Object key = entry.getKey();
            if (!(key instanceof CharSequence)) {
              throw new IllegalArgumentException("Map key must be a String/CharSequence object");
            }
            if (head) {
              head = false;
            } else {
              out.append(", ");
            }
            serializeJson(key, out);
            out.append(": ");
            serializeJson(entry.getValue(), out);
          }
          out.append('}');
        }
        else
        {
          throw new IllegalArgumentException("Unrecognized value: " + obj.getClass() + " " + obj.toString());
        }
      }
    }
  }
  
  public static Object parse(String str)
  {
    StringStream ss = new StringStream(str);
    Object result = parseGeneral(ss);
    if ((result instanceof Symbol)) {
      throw new IllegalArgumentException("Malformed JSON");
    }
    if (!isSymbol(parseGeneral(ss), -1)) {
      throw new IllegalArgumentException("Malformed JSON");
    }
    return result;
  }
  
  private static Object parseGeneral(StringStream ss)
  {
    ss.skipWhitespace();
    ss.mark();
    int c = ss.nextChar();
    switch (c)
    {
    case 123: 
      return parseObject(ss);
    case 91: 
      return parseArray(ss);
    case 34: 
      return parseString(ss);
    case 102: 
    case 110: 
    case 116: 
      return parseConstant(ss);
    case -1: 
    case 44: 
    case 58: 
    case 93: 
    case 125: 
      return new Symbol(c);
    }
    if (((c >= 48) && (c <= 57)) || (c == 45)) {
      return parseNumber(ss);
    }
    throw new IllegalArgumentException("Malformed JSON");
  }
  
  private static SortedMap<String, Object> parseObject(StringStream ss)
  {
    SortedMap<String, Object> result = new SafeTreeMap();
    boolean head = true;
    for (;;)
    {
      Object key = parseGeneral(ss);
      if (isSymbol(key, 125)) {
        break;
      }
      if (head)
      {
        head = false;
      }
      else
      {
        if (!isSymbol(key, 44)) {
          throw new IllegalArgumentException("Malformed JSON");
        }
        key = parseGeneral(ss);
      }
      if (!(key instanceof String)) {
        throw new IllegalArgumentException("Malformed JSON");
      }
      if (result.containsKey(key)) {
        throw new IllegalArgumentException("JSON object contains duplicate key");
      }
      if (!isSymbol(parseGeneral(ss), 58)) {
        throw new IllegalArgumentException("Malformed JSON");
      }
      Object value = parseGeneral(ss);
      if ((value instanceof Symbol)) {
        throw new IllegalArgumentException("Malformed JSON");
      }
      result.put((String)key, value);
    }
    return result;
  }
  
  private static List<Object> parseArray(StringStream ss)
  {
    List<Object> result = new ArrayList();
    boolean head = true;
    for (;;)
    {
      Object obj = parseGeneral(ss);
      if (isSymbol(obj, 93)) {
        break;
      }
      if (head)
      {
        head = false;
      }
      else
      {
        if (!isSymbol(obj, 44)) {
          throw new IllegalArgumentException("Malformed JSON");
        }
        obj = parseGeneral(ss);
      }
      if ((obj instanceof Symbol)) {
        throw new IllegalArgumentException("Malformed JSON");
      }
      result.add(obj);
    }
    return result;
  }
  
  private static String parseString(StringStream ss)
  {
            StringBuilder sb = new StringBuilder();
label0:
        do
        {
            int c = ss.nextChar();
            switch(c)
            {
            case 34: // '"'
                break label0;

            case 92: // '\\'
                c = ss.nextChar();
                switch(c)
                {
                case 34: // '"'
                case 47: // '/'
                case 92: // '\\'
                    sb.append((char)c);
                    break;

                case 98: // 'b'
                    sb.append('\b');
                    break;

                case 102: // 'f'
                    sb.append('\f');
                    break;

                case 110: // 'n'
                    sb.append('\n');
                    break;

                case 114: // 'r'
                    sb.append('\r');
                    break;

                case 116: // 't'
                    sb.append('\t');
                    break;

                case 117: // 'u'
                    int w = ss.nextChar();
                    int x = ss.nextChar();
                    int y = ss.nextChar();
                    int z = ss.nextChar();
                    if(z == -1 || w == 43 || w == 45)
                        throw new IllegalArgumentException("Malformed JSON");
                    String hex = (new StringBuilder()).append("").append((char)w).append((char)x).append((char)y).append((char)z).toString();
                    sb.append((char)Integer.parseInt(hex, 16));
                    break;

                case -1: 
                default:
                    throw new IllegalArgumentException("Malformed JSON");
                }
                break;

            default:
                if(c >= 32)
                    sb.append((char)c);
                else
                    throw new IllegalArgumentException("Malformed JSON");
                break;
            }
        } while(true);
        return sb.toString();
  }
  
  private static Boolean parseConstant(StringStream ss)
  {
    for (;;)
    {
      int c = ss.nextChar();
      if (c == -1) {
        break;
      }
      if ((c < 97) || (c > 122))
      {
        ss.previous();
        break;
      }
    }
    String val = ss.substring();
    if (val.equals("null")) {
      return null;
    }
    if (val.equals("false")) {
      return Boolean.FALSE;
    }
    if (val.equals("true")) {
      return Boolean.TRUE;
    }
    throw new IllegalArgumentException("Malformed JSON");
  }
  
  private static JsonNumber parseNumber(StringStream ss)
  {
    for (;;)
    {
      int c = ss.nextChar();
      if (c == -1) {
        break;
      }
      if (((c < 48) || (c > 57)) && (c != 43) && (c != 45) && (c != 46) && (c != 101) && (c != 69))
      {
        ss.previous();
        break;
      }
    }
    return new JsonNumber(ss.substring());
  }
  
  private static final class StringStream
  {
    private final String string;
    private int index;
    private int start;
    
    public StringStream(String s)
    {
      if (s == null) {
        throw new NullPointerException();
      }
      this.string = s;
      this.index = 0;
      this.start = -1;
    }
    
    public int nextChar()
    {
      if (this.index >= this.string.length()) {
        return -1;
      }
      char result = this.string.charAt(this.index);
      this.index += 1;
      return result;
    }
    
    public void previous()
    {
      if (this.index <= 0) {
        throw new IllegalStateException();
      }
      this.index -= 1;
    }
    
    public void skipWhitespace()
    {
      while (this.index < this.string.length())
      {
        char c = this.string.charAt(this.index);
        if ((c != ' ') && (c != '\n') && (c != '\r') && (c != '\t')) {
          break;
        }
        this.index += 1;
      }
    }
    
    public void mark()
    {
      this.start = this.index;
    }
    
    public String substring()
    {
      if (this.start == -1) {
        throw new IllegalStateException("Not marked");
      }
      return this.string.substring(this.start, this.index);
    }
  }
  
  private static class Symbol
  {
    public final int charValue;
    
    public Symbol(int chr)
    {
      if ((chr < -1) || (chr > 65535)) {
        throw new IllegalArgumentException();
      }
      this.charValue = chr;
    }
  }
  
  private static boolean isSymbol(Object obj, int chr)
  {
    return ((obj instanceof Symbol)) && (((Symbol)obj).charValue == chr);
  }
  
  public static Object parseFromFile(File file)
    throws IOException
  {
    return parseFromFile(file, Charset.forName("UTF-8"));
  }
  
  public static Object parseFromFile(File file, Charset cs)
    throws IOException
  {
    InputStream in = new FileInputStream(file);
    try
    {
      return parseFromStream(in, cs);
    }
    finally
    {
      in.close();
    }
  }
  
  public static Object parseFromUrl(URL url)
    throws IOException
  {
    return parseFromUrl(url, Charset.forName("UTF-8"));
  }
  
  public static Object parseFromUrl(URL url, Charset cs)
    throws IOException
  {
    InputStream in = url.openStream();
    try
    {
      return parseFromStream(in, cs);
    }
    finally
    {
      in.close();
    }
  }
  
  private static Object parseFromStream(InputStream in, Charset cs)
    throws IOException
  {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    byte[] buf = new byte['?'];
    for (;;)
    {
      int n = in.read(buf);
      if (n == -1) {
        break;
      }
      bout.write(buf, 0, n);
    }
    return parse(new String(bout.toByteArray(), cs));
  }
  
  public static void serializeToFile(Object obj, File file)
    throws IOException
  {
    serializeToFile(obj, file, Charset.forName("UTF-8"));
  }
  
  public static void serializeToFile(Object obj, File file, Charset cs)
    throws IOException
  {
    Writer out = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file)), cs);
    try
    {
      serializeJson(obj, out);
    }
    finally
    {
      out.close();
    }
  }
  
  public static Object getObject(Object root, Object... path)
  {
    Object node = root;
    for (Object key : path) {
      if ((key instanceof String))
      {
        if (!(node instanceof Map)) {
          throw new IllegalArgumentException("Expected a map");
        }
        Map<String, Object> map = (Map)node;
        if (!map.containsKey(key)) {
          throw new IllegalArgumentException("Map key not found: " + key);
        }
        node = map.get(key);
      }
      else if ((key instanceof Integer))
      {
        if (!(node instanceof List)) {
          throw new IllegalArgumentException("Expected a list");
        }
        List<Object> list = (List)node;
        int index = ((Integer)key).intValue();
        if ((index < 0) || (index >= list.size())) {
          throw new IndexOutOfBoundsException(key.toString());
        }
        node = list.get(index);
      }
      else
      {
        if (key == null) {
          throw new NullPointerException();
        }
        throw new IllegalArgumentException("Invalid path component: " + key);
      }
    }
    return node;
  }
  
  public static boolean getBoolean(Object root, Object... path)
  {
    return ((Boolean)getObject(root, path)).booleanValue();
  }
  
  public static int getInt(Object root, Object... path)
  {
    return ((Number)getObject(root, path)).intValue();
  }
  
  public static long getLong(Object root, Object... path)
  {
    return ((Number)getObject(root, path)).longValue();
  }
  
  public static float getFloat(Object root, Object... path)
  {
    return ((Number)getObject(root, path)).floatValue();
  }
  
  public static double getDouble(Object root, Object... path)
  {
    return ((Number)getObject(root, path)).doubleValue();
  }
  
  public static String getString(Object root, Object... path)
  {
    String result = (String)getObject(root, path);
    if (result == null) {
      throw new NullPointerException();
    }
    return result;
  }
  
  public static List<Object> getList(Object root, Object... path)
  {
    List<Object> result = (List)getObject(root, path);
    if (result == null) {
      throw new NullPointerException();
    }
    return result;
  }
  
  public static Map<String, Object> getMap(Object root, Object... path)
  {
    Map<String, Object> result = (Map)getObject(root, path);
    if (result == null) {
      throw new NullPointerException();
    }
    return result;
  }
  
  private static final class SafeTreeMap<K, V>
    extends TreeMap<K, V>
  {
    public V get(Object key)
    {
      if (!containsKey(key)) {
        throw new IllegalArgumentException("Key does not exist: " + key);
      }
      return (V)super.get(key);
    }
  }
}
