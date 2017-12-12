// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FixASCIIControlsReader.java

package co.com.pdf.xmp.impl;

import java.io.*;

// Referenced classes of package co.com.pdf.xmp.impl:
//            Utils

public class FixASCIIControlsReader extends PushbackReader
{

    public FixASCIIControlsReader(Reader in)
    {
        super(in, 8);
        state = 0;
        control = 0;
        digits = 0;
    }

    public int read(char cbuf[], int off, int len)
        throws IOException
    {
        int readAhead = 0;
        int read = 0;
        int pos = off;
        char readAheadBuffer[] = new char[8];
        boolean available = true;
        do
        {
            if(!available || read >= len)
                break;
            available = super.read(readAheadBuffer, readAhead, 1) == 1;
            if(available)
            {
                char c = processChar(readAheadBuffer[readAhead]);
                if(state == 0)
                {
                    if(Utils.isControlChar(c))
                        c = ' ';
                    cbuf[pos++] = c;
                    readAhead = 0;
                    read++;
                } else
                if(state == 5)
                {
                    unread(readAheadBuffer, 0, readAhead + 1);
                    readAhead = 0;
                } else
                {
                    readAhead++;
                }
            } else
            if(readAhead > 0)
            {
                unread(readAheadBuffer, 0, readAhead);
                state = 5;
                readAhead = 0;
                available = true;
            }
        } while(true);
        return read <= 0 && !available ? -1 : read;
    }

    private char processChar(char ch)
    {
        switch(state)
        {
        case 0: // '\0'
            if(ch == '&')
                state = 1;
            return ch;

        case 1: // '\001'
            if(ch == '#')
                state = 2;
            else
                state = 5;
            return ch;

        case 2: // '\002'
            if(ch == 'x')
            {
                control = 0;
                digits = 0;
                state = 3;
            } else
            if('0' <= ch && ch <= '9')
            {
                control = Character.digit(ch, 10);
                digits = 1;
                state = 4;
            } else
            {
                state = 5;
            }
            return ch;

        case 4: // '\004'
            if('0' <= ch && ch <= '9')
            {
                control = control * 10 + Character.digit(ch, 10);
                digits++;
                if(digits <= 5)
                    state = 4;
                else
                    state = 5;
            } else
            {
                if(ch == ';' && Utils.isControlChar((char)control))
                {
                    state = 0;
                    return (char)control;
                }
                state = 5;
            }
            return ch;

        case 3: // '\003'
            if('0' <= ch && ch <= '9' || 'a' <= ch && ch <= 'f' || 'A' <= ch && ch <= 'F')
            {
                control = control * 16 + Character.digit(ch, 16);
                digits++;
                if(digits <= 4)
                    state = 3;
                else
                    state = 5;
            } else
            {
                if(ch == ';' && Utils.isControlChar((char)control))
                {
                    state = 0;
                    return (char)control;
                }
                state = 5;
            }
            return ch;

        case 5: // '\005'
            state = 0;
            return ch;
        }
        return ch;
    }

    private static final int STATE_START = 0;
    private static final int STATE_AMP = 1;
    private static final int STATE_HASH = 2;
    private static final int STATE_HEX = 3;
    private static final int STATE_DIG1 = 4;
    private static final int STATE_ERROR = 5;
    private static final int BUFFER_SIZE = 8;
    private int state;
    private int control;
    private int digits;
}
