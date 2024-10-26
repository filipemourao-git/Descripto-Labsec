package cripto1;



import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class Descripto {

    public static void main(String[] args) {
        try {
            // Mensagem Base64, use a sua string corretamente formatada
            String encryptedMessageBase64 = "GkScVohh5OKsz4sJpkyrtybYsz8vTSBOV2HmTBOdcM051ZUrFoydxfjvq+bc9VmV+4c18bd+w3xyJuwv5++YLHCEmjy95EhqniCcoKr5AeVa00/ZkrKXLTI9SboNM+7dK672GGbUJGkvlsP8koJrQxMtAKZUfbbjCCe39rGTRwnXOPJAy1OC3KNEraHbg9H091JWPyumCnfs7O0IucydrPSFp5kirLo9pwZyN58uR/EfY2omVcfcAqws5Omyi7hyNOY+QCmDqi+G7YsM1isKm8rCw9D42DQvZwMvKBSHzR3zH7w9cMEmIUG+9ff+XQGNfotJAaDyTwyhM81Dneav1DqPoSgFwHwaWclpMVjM9F1cFzR5zTtr+IuNPAa/wv5H2vutn4dquVok8/CgjaMH5qLMGftF1G6GRO6GEtjsmh/XXQS8a+Nq8DeRHABKkBtazN20FvU/vD5dXpBoYgE0Ubs0hANBhAGg6UKkpSAE03g8ehXiX8cjnC3hFUiot5OLTLvTi7L6Ew9SCaQtVZUd32v9SIZixS4JIaLYEFaVaYtAUj8iBNf8v3Pe/n7U6aGZT0lJDKNnbfFGrlMjE8IWsAjt6LtRldVMyzXpKY9P41+wg2iCPQ3JazvOekIQ3JgYkjT38KV/gERl3ILkmTpazj2aSXa/7/yippkjNvxP7NA=";
            // Carrega o certificado .pem e extrai a chave pública
            PublicKey publicKey = loadPublicKeyFromCertificate("C:/Users/filip/Desktop/Pré-desafio Labsec/certificadoRato");

            // Descriptografa a mensagem base64 usando a chave pública
            byte[] decryptedMessage = decryptMessageWithPublicKey(Base64.getDecoder().decode(encryptedMessageBase64), publicKey);
            System.out.println("Mensagem descriptografada: " + new String(decryptedMessage, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PublicKey loadPublicKeyFromCertificate(String certPath) throws Exception {
        try (FileInputStream fis = new FileInputStream(certPath)) {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certFactory.generateCertificate(fis);
            return certificate.getPublicKey();
        }
    }

    public static byte[] decryptMessageWithPublicKey(byte[] encryptedMessage, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(encryptedMessage);
    }
}