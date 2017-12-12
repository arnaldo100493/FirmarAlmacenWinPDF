// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonPrettyGeneratorImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;

// Referenced classes of package org.glassfish.json:
//            JsonGeneratorImpl

public class JsonPrettyGeneratorImpl extends JsonGeneratorImpl
{

    public JsonPrettyGeneratorImpl(Writer writer, BufferPool bufferPool)
    {
        super(writer, bufferPool);
    }

    public JsonPrettyGeneratorImpl(OutputStream out, BufferPool bufferPool)
    {
        super(out, bufferPool);
    }

    public JsonPrettyGeneratorImpl(OutputStream out, Charset encoding, BufferPool bufferPool)
    {
        super(out, encoding, bufferPool);
    }

    public JsonGenerator writeStartObject()
    {
        super.writeStartObject();
        indentLevel++;
        return this;
    }

    public JsonGenerator writeStartObject(String name)
    {
        super.writeStartObject(name);
        indentLevel++;
        return this;
    }

    public JsonGenerator writeStartArray()
    {
        super.writeStartArray();
        indentLevel++;
        return this;
    }

    public JsonGenerator writeStartArray(String name)
    {
        super.writeStartArray(name);
        indentLevel++;
        return this;
    }

    public JsonGenerator writeEnd()
    {
        writeNewLine();
        indentLevel--;
        writeIndent();
        super.writeEnd();
        return this;
    }

    private void writeIndent()
    {
        for(int i = 0; i < indentLevel; i++)
            writeString("    ");

    }

    protected void writeComma()
    {
        super.writeComma();
        writeChar('\n');
        writeIndent();
    }

    private void writeNewLine()
    {
        writeChar('\n');
    }

    public volatile void close()
    {
        super.close();
    }

    public volatile JsonGenerator writeNull()
    {
        return super.writeNull();
    }

    public volatile JsonGenerator write(boolean x0)
    {
        return super.write(x0);
    }

    public volatile JsonGenerator write(BigDecimal x0)
    {
        return super.write(x0);
    }

    public volatile JsonGenerator write(BigInteger x0)
    {
        return super.write(x0);
    }

    public volatile JsonGenerator write(double x0)
    {
        return super.write(x0);
    }

    public volatile JsonGenerator write(long x0)
    {
        return super.write(x0);
    }

    public volatile JsonGenerator write(int x0)
    {
        return super.write(x0);
    }

    public volatile JsonGenerator write(String x0)
    {
        return super.write(x0);
    }

    public volatile JsonGenerator write(String x0, JsonValue x1)
    {
        return super.write(x0, x1);
    }

    public volatile JsonGenerator write(JsonValue x0)
    {
        return super.write(x0);
    }

    public volatile JsonGenerator writeNull(String x0)
    {
        return super.writeNull(x0);
    }

    public volatile JsonGenerator write(String x0, boolean x1)
    {
        return super.write(x0, x1);
    }

    public volatile JsonGenerator write(String x0, BigDecimal x1)
    {
        return super.write(x0, x1);
    }

    public volatile JsonGenerator write(String x0, BigInteger x1)
    {
        return super.write(x0, x1);
    }

    public volatile JsonGenerator write(String x0, double x1)
    {
        return super.write(x0, x1);
    }

    public volatile JsonGenerator write(String x0, long x1)
    {
        return super.write(x0, x1);
    }

    public volatile JsonGenerator write(String x0, int x1)
    {
        return super.write(x0, x1);
    }

    public volatile JsonGenerator write(String x0, String x1)
    {
        return super.write(x0, x1);
    }

    public volatile void flush()
    {
        super.flush();
    }

    private int indentLevel;
    private static final String INDENT = "    ";
}
