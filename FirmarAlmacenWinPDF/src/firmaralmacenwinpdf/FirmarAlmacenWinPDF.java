package firmaralmacenwinpdf;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import sun.misc.BASE64Decoder;

public class FirmarAlmacenWinPDF {

    private static KeyManagerFactory kmf;
    private static TrustManagerFactory tmf;
    public static String keyPass = "localPasswd";
    public static String dataSign = "";
    public static String errorMsg = "";
    public static JTextArea textLog = null;

    public static void main(String[] args) {
        FirmarAlmacenWinPDF pki = new FirmarAlmacenWinPDF();
        pki.createInterface();
        System.out.println("Servidor iniciado");
    }

    public FirmarAlmacenWinPDF() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            loadCertificateKey();
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            HttpsServer httpsserver = HttpsServer.create(new InetSocketAddress(8448), 0);
            httpsserver.setHttpsConfigurator(new HttpsConfigurator(sslContext));
            httpsserver.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                public void configure(HttpsParameters params) {
                    try {
                        SSLContext c = SSLContext.getDefault();
                        SSLEngine engine = c.createSSLEngine();
                        params.setNeedClientAuth(false);
                        params.setCipherSuites(engine.getEnabledCipherSuites());
                        params.setProtocols(engine.getEnabledProtocols());
                        SSLParameters defaultSSLParameters = c.getDefaultSSLParameters();
                        params.setSSLParameters(defaultSSLParameters);
                    } catch (Exception ex) {
                        String message = FirmarAlmacenWinPDF.textLog.getText();
                        message = message + "\nhttps " + ex.getMessage();
                        FirmarAlmacenWinPDF.textLog.setText(message);
                        System.out.println("Failed to create HTTPS server");
                    }
                }
            });
            httpsserver.createContext("/test", new TestHandler());
            HttpContext context = httpsserver.createContext("/pki", new DoSign());
            context.getFilters().add(new ParameterFilter());
            httpsserver.start();
        } catch (KeyManagementException | NoSuchAlgorithmException | IOException ex) {
            String message = textLog.getText();
            message = message + "\nPkiAutenticacion " + ex.getMessage();
            textLog.setText(message);
            Logger.getLogger(FirmarAlmacenWinPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadCertificateKey()
            throws NoSuchAlgorithmException {
        String stringcert = Resource.getCert();
        String key = Resource.getPrivKey();
        BASE64Decoder b64 = new BASE64Decoder();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
            Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(b64.decodeBuffer(stringcert)));
            KeyFactory kf = KeyFactory.getInstance("RSA");
            KeySpec privateKeySpec = new PKCS8EncodedKeySpec(b64.decodeBuffer(key));
            PrivateKey privKey = kf.generatePrivate(privateKeySpec);
            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(null, null);
            X509Certificate[] chain = new X509Certificate[1];
            chain[0] = ((X509Certificate) certificate);
            keystore.setKeyEntry("localServer", privKey, keyPass.toCharArray(), chain);
            kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(keystore, keyPass.toCharArray());
            tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(keystore);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | CertificateException | KeyStoreException | UnrecoverableKeyException ex) {
            String message = textLog.getText();
            message = message + "\nLoadCertificateKey " + ex.getMessage();
            textLog.setText(message);
            Logger.getLogger(FirmarAlmacenWinPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createInterface() {
        try {
            JFrame frame = new JFrame("Firmar Almacen Windows Andes SCD");
            frame.setLocationRelativeTo(null);
            frame.setExtendedState(3);
            frame.setDefaultCloseOperation(3);
            frame.setSize(new Dimension(400, 300));
            ImageIcon icon = new ImageIcon(getClass().getResource("/firmaralmacenwinpdf/images/LogoAndesFinal.png"));
            String message = "<html><body>Agente Iniciado Version: 1.0.0 <br/>Recibiendo solicitudes en el puerto 8448 </body></html>";
            JLabel label = new JLabel(message, icon, 0);
            label.setVerticalTextPosition(3);
            label.setHorizontalTextPosition(0);
            Container content = frame.getContentPane();
            content.setBackground(new Color(206, 227, 246));
            content.add(label, "North");
            textLog = new JTextArea(5, 10);
            JScrollPane scrollPane = new JScrollPane(textLog);
            textLog.setEditable(false);
            content.add(textLog, "Center");
            JButton button = new JButton("Detener ");
            button.setBounds(10, 10, 20, 30);
            ActionListener listener = new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            };
            button.addActionListener(listener);
            content.add(button, "South");
            frame.show();
        } catch (Exception ex) {
            errorMsg = textLog.getText();
            errorMsg = errorMsg + "\nOcurrió un error: PkiAutenticacion " + ex.getMessage();
            textLog.setText(errorMsg);
            Logger.getLogger(FirmarAlmacenWinPDF.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ocurrió un error: " + ex.getMessage());
        }
    }

    public static void setHeaders(Headers headers) {
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
    }
}
