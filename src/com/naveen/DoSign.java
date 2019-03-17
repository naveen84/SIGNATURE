package com.naveen;

import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

public class DoSign {

	public static void main(String[] args) throws Exception {
		String text = "HARE KRISHNA HARE RAMA";

		// GENERATE KEYPAIR

		KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("DSA");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		 
		pairGenerator.initialize(1024, random);
		KeyPair pair = pairGenerator.genKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		System.out.println("PRIVATE KEY : " + privateKey + " " + "PUBLIC KEY" + publicKey);

		// CREATE SIGNATURE KEY

		Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
		dsa.initSign(privateKey);
		dsa.update(text.getBytes());

		/*
		 * Now that all the data to be signed has been read in, generate a
		 * signature for it
		 */

		byte[] realSig = dsa.sign();

		/* Save the signature in a file */
		FileOutputStream sigfos = new FileOutputStream("D:\\OTHERS\\sign.text");
		sigfos.write(realSig);

	}

}
