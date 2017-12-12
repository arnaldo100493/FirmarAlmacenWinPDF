package firmaralmacenwinpdf;

import co.com.andesscd.pki.clases.CMS;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.X509Certificate;

public class FirmarPDF
{
  public void firmarPDF(X509Certificate x509, PrivateKey privKey, Provider provider, InputStream entrada, ByteArrayOutputStream salida, String firmaTSA, String datosTSA)
    throws UnknownError, Exception
  {
    //CMS.iniciarComponente();
    CMS cms = new CMS(entrada);
    CMS.setFuenteHorariaLocal();
    System.out.println(firmaTSA);
    if (firmaTSA.equalsIgnoreCase("true"))
    {
      String[] tsa = datosTSA.split("--");
      CMS.setFuenteHorariaTSA(tsa[0], tsa[1], tsa[2]);
    }
    cms.firmar(x509, privKey, provider, salida);                  

  }
}
