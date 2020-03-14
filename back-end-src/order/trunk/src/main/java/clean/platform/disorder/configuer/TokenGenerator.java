package clean.platform.disorder.configuer;

import java.security.SecureRandom;

/**
 * Web Token 生成
 *
 * @author ZX
 * @create 2020-01-04 14:29
 **/

public class TokenGenerator {

    protected static SecureRandom random = new SecureRandom();

    public static synchronized String generateToken( String username ) {
        long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 16 );
        return ( username + ":" + random );
    }
}
