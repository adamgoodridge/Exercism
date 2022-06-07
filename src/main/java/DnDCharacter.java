import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

class DnDCharacter {
    private final int STRENGTH_INDEX = 0, DEXTERITY_INDEX = 1, CONSTITUTION_INDEX = 2, INTELLIGENCE_INDEX = 3, WISDOM_INDEX = 4, CHARISMA_INDEX = 5;
    private int hitpoints;
    private int[] abilities;
    public DnDCharacter(){
        abilities = new int[6];
        for(int i = 0;i < abilities.length; i++) {
            abilities[i] = roll();
        }
        this.hitpoints= 10 + modifier(abilities[CONSTITUTION_INDEX]);
    }
    private int roll() {
        int[] rolls = new int[4];
        for(int i = 0;i < rolls.length; i++) {
            // 1 is inclusive whereas 7 is not
            rolls[i] = ThreadLocalRandom.current().nextInt(1,7);
        }
        Arrays.sort(rolls);
        int sum = 0;
        //don't count [0] as it is the lowest number
        for(int i = 1;i < rolls.length; i++) {
            sum += rolls[i];
        }
        return sum;
    }
    int ability() {
        int randomIndex = ThreadLocalRandom.current().nextInt(0,abilities.length);
        return abilities[randomIndex];
    }

    int modifier(int input) {
        return (int)Math.floor((input-10.0)/2);
    }

    int getStrength() {
        return this.abilities[STRENGTH_INDEX];
    }

    int getDexterity() {
        return this.abilities[DEXTERITY_INDEX];
    }

    int getConstitution() {
        return this.abilities[CONSTITUTION_INDEX];
    }

    int getIntelligence() {
        return this.abilities[INTELLIGENCE_INDEX];
    }

    int getWisdom() {
        return this.abilities[WISDOM_INDEX];
    }

    int getCharisma() {
        return this.abilities[CHARISMA_INDEX];
    }

    int getHitpoints() {
        return this.hitpoints;
    }

}
