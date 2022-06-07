import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Sieve {
    private List<Integer> primes;
    Sieve(int maxPrime) {
        this.primes = new ArrayList<Integer>();
        for(int i = 2; i <= maxPrime; i++) {
            primes.add(i);
        }
        for(int i= 0; i < primes.size(); i++) {
            int number =  primes.get(i);
            for(int k = number*2; k <= maxPrime; k += number) {
                primes.remove(Integer.valueOf(k));
            }
        }
    }

    List<Integer> getPrimes() {
        return this.primes;
    }
}
