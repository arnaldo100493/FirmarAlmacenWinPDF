// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QRCodeWriter.java

package co.com.pdf.text.pdf.qrcode;

import java.util.Map;

// Referenced classes of package co.com.pdf.text.pdf.qrcode:
//            ErrorCorrectionLevel, QRCode, ByteMatrix, WriterException, 
//            EncodeHintType, Encoder

public final class QRCodeWriter
{

    public QRCodeWriter()
    {
    }

    public ByteMatrix encode(String contents, int width, int height)
        throws WriterException
    {
        return encode(contents, width, height, null);
    }

    public ByteMatrix encode(String contents, int width, int height, Map hints)
        throws WriterException
    {
        if(contents == null || contents.length() == 0)
            throw new IllegalArgumentException("Found empty contents");
        if(width < 0 || height < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Requested dimensions are too small: ").append(width).append('x').append(height).toString());
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
        if(hints != null)
        {
            ErrorCorrectionLevel requestedECLevel = (ErrorCorrectionLevel)hints.get(EncodeHintType.ERROR_CORRECTION);
            if(requestedECLevel != null)
                errorCorrectionLevel = requestedECLevel;
        }
        QRCode code = new QRCode();
        Encoder.encode(contents, errorCorrectionLevel, hints, code);
        return renderResult(code, width, height);
    }

    private static ByteMatrix renderResult(QRCode code, int width, int height)
    {
        ByteMatrix input = code.getMatrix();
        int inputWidth = input.getWidth();
        int inputHeight = input.getHeight();
        int qrWidth = inputWidth + 8;
        int qrHeight = inputHeight + 8;
        int outputWidth = Math.max(width, qrWidth);
        int outputHeight = Math.max(height, qrHeight);
        int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
        int leftPadding = (outputWidth - inputWidth * multiple) / 2;
        int topPadding = (outputHeight - inputHeight * multiple) / 2;
        ByteMatrix output = new ByteMatrix(outputWidth, outputHeight);
        byte outputArray[][] = output.getArray();
        byte row[] = new byte[outputWidth];
        for(int y = 0; y < topPadding; y++)
            setRowColor(outputArray[y], (byte)-1);

        byte inputArray[][] = input.getArray();
        for(int y = 0; y < inputHeight; y++)
        {
            for(int x = 0; x < leftPadding; x++)
                row[x] = -1;

            int offset = leftPadding;
            for(int x = 0; x < inputWidth; x++)
            {
                byte value = ((byte)(inputArray[y][x] != 1 ? -1 : 0));
                for(int z = 0; z < multiple; z++)
                    row[offset + z] = value;

                offset += multiple;
            }

            offset = leftPadding + inputWidth * multiple;
            for(int x = offset; x < outputWidth; x++)
                row[x] = -1;

            offset = topPadding + y * multiple;
            for(int z = 0; z < multiple; z++)
                System.arraycopy(row, 0, outputArray[offset + z], 0, outputWidth);

        }

        int offset = topPadding + inputHeight * multiple;
        for(int y = offset; y < outputHeight; y++)
            setRowColor(outputArray[y], (byte)-1);

        return output;
    }

    private static void setRowColor(byte row[], byte value)
    {
        for(int x = 0; x < row.length; x++)
            row[x] = value;

    }

    private static final int QUIET_ZONE_SIZE = 4;
}
