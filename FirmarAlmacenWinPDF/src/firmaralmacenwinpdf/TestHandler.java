package firmaralmacenwinpdf;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class TestHandler
  implements HttpHandler
{
  public void handle(HttpExchange t)
    throws IOException
  {
    String response = "Test page";
    int responseLong = response.getBytes("UTF-8").length;
    FirmarAlmacenWinPDF.setHeaders(t.getResponseHeaders());
    t.sendResponseHeaders(200, responseLong);
    OutputStream os = t.getResponseBody();Throwable localThrowable3 = null;
    try
    {
      os.write(response.getBytes("UTF-8"));
    }
    catch (Throwable localThrowable1)
    {
      localThrowable3 = localThrowable1;throw localThrowable1;
    }
    finally
    {
      if (os != null) {
        if (localThrowable3 != null) {
          try
          {
            os.close();
          }
          catch (Throwable localThrowable2)
          {
            localThrowable3.addSuppressed(localThrowable2);
          }
        } else {
          os.close();
        }
      }
    }
  }
}
