class Acronym {
    private String acronym;
    Acronym(String phrase) {
        phrase = phrase.toUpperCase().replaceAll("[-_]"," ").replaceAll("[^A-Z\\s]","") .replaceAll(" +", " ");
        String[] words = phrase.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String word:words) {
            sb.append(word.charAt(0));
        }
        this.acronym=sb.toString();
    }

    String get() {
        return this.acronym;
    }
}