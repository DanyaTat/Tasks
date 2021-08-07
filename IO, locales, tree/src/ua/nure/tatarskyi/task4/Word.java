package ua.nure.tatarskyi.task4;

public class Word {

	private String content;
    private int frequency;
    
    public Word(String content, int frequency) {
        this.content = content;
        this.frequency = frequency;
    }

    public String getContent() {
        return content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
