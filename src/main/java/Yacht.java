import java.util.Arrays;

class Yacht {
    private int score;
    Yacht(int[] dice, YachtCategory yachtCategory) {
        switch (yachtCategory) {
            case ONES:
                score = numberMatch(1, dice);
                break;
            case TWOS:
                score = numberMatch(2, dice);
                break;
            case THREES:
                score = numberMatch(3, dice);
                break;
            case FOURS:
                score = numberMatch(4, dice);
                break;
            case FIVES:
                score = numberMatch(5, dice);
                break;
            case SIXES:
                score = numberMatch(6, dice);
                break;
            case YACHT:
                score = yacht(dice);
                break;
            case FULL_HOUSE:
                score = fullHouse(dice);
                break;
            case FOUR_OF_A_KIND:
                score = fourOfKind(dice);
                break;
            case LITTLE_STRAIGHT:
                score = littleStraight(dice);
                break;
            case BIG_STRAIGHT:
                score = bigStraight(dice);
                break;
            case CHOICE:
                score = choice(dice);
                break;
        }
    }
    int score() {
        return this.score;
    }
    private int numberMatch(int number, int[] dice) {
        int totalScore = 0;
        for(int i = 0; i < dice.length; i++) {
            if(dice[i] == number)
                totalScore += number;
        }
        return totalScore;
    }
    private int fullHouse(int[] dice) {
        int a = dice[0], b = 0, iA = 1, iB = 0, sum = dice[0];
        for (int i = 1; i < dice.length; i++) {
            sum += dice[i];
            if (dice[i] == a)
                iA++;
            else if (dice[i] == b) {
                iB++;
            }
            else if (b == 0) {
                b = dice[i];
                iB += 1;
            }
            else
                //amount of unique number has reached three therefore there is no full house
                return 0;
        }
        return (iA == 2 && iB == 3) || (iA == 3 && iB == 2) ? sum : 0;
    }
    private int fourOfKind(int[] dice) {
        //four of kind = 2 different kind
        int[] possibilities = {dice[0], 0, 0}, count = {1, 0, 0};
        Boolean isAllocated;
        for (int i = 1; i < dice.length; i++) {
            isAllocated = false;
            for (int k = 0; k < possibilities.length; k++) {
                if (possibilities[k] == 0) {
                    possibilities[k] = dice[i];
                    count[k] += 1;
                    isAllocated = true;
                    break;
                } else if (possibilities[k] == dice[i]) {
                    count[k] += 1;
                    isAllocated = true;
                    break;
                }
            }
                if(!isAllocated) {
                    return 0;
                }
            }
            for (int k = 0; k < possibilities.length; k++) {
                if (count[k] >= 4) {
                    return possibilities[k] * 4;
                }
            }
            return 0;
        }

    private int littleStraight(int[] dice) {
        Arrays.sort(dice);
        for(int i = 0; i < dice.length; i++) {
            if(dice[i] != (1 + i))
                return 0;
        }
        return 30;
    }
    private int bigStraight(int[] dice) {
        Arrays.sort(dice);
        for(int i = 0; i < dice.length; i++) {
            if(dice[i] != (2 + i))
                return 0;
        }
        return 30;
    }
    private int choice(int[] dice) {
        int sum = 0;
        for(int roll : dice) {
            sum += roll;
        }
        return sum;
    }
    private int yacht(int[] dice) {
        int match = dice[0];
        for(int i = 1; i < dice.length; i++) {
            if(dice[i] != match)
                return 0;
        }
        return 50;
    }

}
