import java.math.BigInteger;
import java.util.Random;
public class DiffieHellman{
    public DiffieHellman(){

    }
    public BigInteger publicKey(BigInteger primeA,BigInteger primeB, BigInteger privateKey) {
        return primeB.modPow(privateKey,primeA);
    }
    public BigInteger secret(BigInteger primeA,BigInteger otherPublicKey, BigInteger privateKey) {
        return otherPublicKey.modPow(privateKey, primeA);
    }
    public BigInteger privateKey(BigInteger prime){
        // source http://www.java2s.com/Tutorials/Java/Algorithms_How_to/Random/Generate_a_random_BigInteger_value.htm
        BigInteger minValue = new BigInteger("2");
        int maxLen = prime.bitLength();
        //generate a random number between 1 and the bit required inclusive for the prime, e.g 23 (10111) so max is 31 (11111)
        BigInteger randomBigInt = new BigInteger(maxLen,new Random());
        if(randomBigInt.compareTo(minValue) < 0)
            //true if random is below the minValue
            randomBigInt = randomBigInt.add(minValue);
        if(randomBigInt.compareTo(prime)>=0)
            //bigger than prime, if the random number is currently prime, it will become 2 (minValue)
            randomBigInt = randomBigInt.mod(prime).add(minValue);
        return randomBigInt;
    }
}