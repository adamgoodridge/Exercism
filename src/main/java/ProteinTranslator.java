import java.util.LinkedList;
import java.util.List;
import java.util.Map;
class ProteinTranslator {
    List<String> translate(String rnaSequence) {
        List<String> output = new LinkedList<String>();
        outer:
        for(int i = 0; i < rnaSequence.length(); i += 3){
            String value = "";
            switch (rnaSequence.substring(i,i+3)) {
                case "AUG" :
                    value = "Methionine";
                    break;
                case "UUU":
                case "UUC":
                    value = "Phenylalanine";
                    break;
                case "UUA":
                case "UUG":
                    value = "Leucine";
                    break;
                case "UCU":
                case "UCC":
                case "UCA":
                case "UCG":
                    value = "Serine";
                    break;
                case "UAU":
                case "UAC":
                    value = "Tyrosine";
                    break;
                case "UGU":
                case "UGC":
                    value ="Cysteine";
                    break;
                case "UGG":
                    value ="Tryptophan";
                    break;
                case "UAA":
                case "UAG":
                case "UGA":
                    break outer;
                default:
                    value = "";
            }
            output.add(value);
        }
        return output;
    }
}
