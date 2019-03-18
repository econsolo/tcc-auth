package br.com.consolo.auth.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.core.env.Environment;

public class Util {

	private static final String KEY_SESSION_TIME = "auth.session.expiration_time";
	private static final String KEY_PASSWORD_SALT = "auth.user.password.salt";
	private static final String SECRET_KEY = "auth.secret_key";
	
	private static final int ITERATION = 10000;
    private static final int KEY_SIZE = 256;

    public static Date addMinutesToData(Date date, Environment env){
        final long ONE_MINUTE_IN_MILLIS = 60000;
        Integer minutos = Integer.valueOf(env.getProperty(KEY_SESSION_TIME));

        long curTimeInMs = date.getTime();
        Date newData = new Date(curTimeInMs + (minutos * ONE_MINUTE_IN_MILLIS));
        return newData;
    }
    
    public static String encrypt(String passwd, Environment env) {
    	String salt = env.getProperty(KEY_PASSWORD_SALT);
    	byte[] securePassword = hash(passwd.toCharArray(), salt.getBytes(), env);
        return Base64.getEncoder().encodeToString(securePassword);
    }
    
    public static byte[] hash(char[] passwd, byte[] salt, Environment env) {
        PBEKeySpec spec = new PBEKeySpec(passwd, salt, ITERATION, KEY_SIZE);
        Arrays.fill(passwd, Character.MIN_VALUE);
        String secretKey = env.getProperty(SECRET_KEY);
        
        try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance(secretKey);
            
            return skf.generateSecret(spec).getEncoded();
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        	
            throw new AssertionError("Erro ao encriptar senha: " + e.getMessage(), e);
            
        } finally {
            spec.clearPassword();
        }
    }
    
    public static boolean isSenhaValida(String senhaInformada, String senhaProtegida, Environment env) {
        String novaSenhaProtegida = encrypt(senhaInformada, env);
        return novaSenhaProtegida.equalsIgnoreCase(senhaProtegida);
    }
    
}
