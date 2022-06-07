

enum Status {EMPTY_FRAME, SECOND_ROLL, EXTRA_ROLL}
public class BowlingGame {
    private int framesIndex ;
    private Frame[] frames;
    private LastFrame lastFrame;
    private Boolean gameInProcess;
    private Status status;
    public BowlingGame() {
        framesIndex = 0;
        status = Status.EMPTY_FRAME;
        gameInProcess = true;
        frames = new Frame[9];
    }

    //determines if the game is over
    //the only the game is end one point if it is after 20th or 21th
    //21th roll is granted when pinsKnocked= 10 therefore isGameOver will be false
    //the code reaches here if on the last roll, on 21st is the game is over
    //From here the exception should of caught pinsKnocked being over 10
    public void roll(int pin) throws IllegalStateException {
        if (!gameInProcess)
            throw new IllegalStateException("Cannot roll after game is over");
        if (pin < 0)
            throw new IllegalStateException("Negative roll is invalid");
         else if(framesIndex < 9) {
             if(status == Status.EMPTY_FRAME) {
                frames[framesIndex] = new Frame(pin);
                if(frames[framesIndex].isStrike()) {
                    framesIndex++;
                    status = Status.EMPTY_FRAME;
                } else {
                    status = Status.SECOND_ROLL;
                }
             } else {
                 if ((frames[framesIndex].getFirst() + pin) > 10)
                       throw new IllegalStateException("Pin count exceeds pins on the lane");
                 frames[framesIndex].setSecond(pin);
                 //goes on the next frame
                 status = Status.EMPTY_FRAME;
                 framesIndex++;
             }
        } else {
            if (status == Status.EMPTY_FRAME){
                lastFrame = new LastFrame(pin);
                status = Status.SECOND_ROLL;
            } else if (status == Status.SECOND_ROLL) {
                lastFrame.setSecond(pin);
                gameInProcess = lastFrame.isExtraRollGranted();
                status = Status.EXTRA_ROLL;
            } else {
                if ((lastFrame.getLastPinCount() + pin) > 10)
                    throw new IllegalStateException("Pin count exceeds pins on the lane");
                lastFrame.setThird(pin);
                gameInProcess = false;
            }
        }
         System.out.println(framesIndex);
    }
    public int score() throws IllegalStateException {
        if(gameInProcess)
            throw new IllegalStateException("Score cannot be taken until the end of the game");
        int score = 0, next, secondNext;
        for(int i = 0; i < frames.length - 2; i++) {
            next = frames[i+1].getFirst();
            secondNext = frames[i+1].isStrike() ? frames[i+2].getFirst(): frames[i+1].getSecond();
            score += frames[i].frameScore(next, secondNext);
        }
        //8th frame
        next = frames[8].getFirst();
        secondNext = frames[8].isStrike() ? lastFrame.getFirst(): frames[8].getSecond();
        score += frames[7].frameScore(next, secondNext);
        //9th frame
        next = lastFrame.getFirst();
        secondNext = frames[8].isStrike() ? lastFrame.getThird(): lastFrame.getSecond();
        score += frames[7].frameScore(next, secondNext);
        //10th frame
        score += lastFrame.frameScore();
        return score;
    }
}
class Frame {
    private int first, second;
    public Frame(int first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }
    public int frameScore(int next, int secondNext) {
        int score = first + second;
        if(this.isStrike())
            score += next + secondNext;
        else if(this.isSparse())
            score += next;
        return score;
    }
    public boolean isStrike() {
        return first == 10;
    }

    public boolean isSparse() {
        return first + second == 10;
    }

    public int getSecond() {
        return second;
    }

    public int getFirst() {
        return first;
    }

}
class LastFrame extends Frame {
    private int third;
    private int lastPinCount;
    public LastFrame(int first) {
        super(first);
    }

    public boolean isExtraRollGranted() {
        if(super.isStrike()) {
            lastPinCount = super.getSecond() == 10 ? 0 : super.getSecond();
            return true;
        } else if(super.isSparse()) {
            lastPinCount = 0;
            return true;
        }
        return false;
    }

    public void setThird(int third) {
        this.third = third;
    }

    public int getThird() {
        return third;
    }
    public int getLastPinCount() {
        return lastPinCount;
    }

    public int frameScore() {
        return getFirst() + getSecond() + third;
    }
}