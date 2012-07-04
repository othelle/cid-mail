import sun.misc.BASE64Decoder
import java.security.MessageDigest

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 04.07.12
 * Time: 0:22
 * To change this template use File | Settings | File Templates.
 */
class PasswordCodec {
    static encode = {str ->
        MessageDigest md = MessageDigest.getInstance('SHA')
        md.update(str.getBytes('UTF-8'))
        return (new BASE64Decoder()).encodeAsBase64(md.digest())

    }
}
