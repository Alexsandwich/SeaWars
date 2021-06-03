package com.seawars.util;

import java.util.Arrays;
import java.util.List;

public class RainbowText {
    private int place = 0;

    private String text = "You did not provide any text.";

    private String fancyText = "did not provide any text";

    /**
     *             RainbowText text = new RainbowText("Test");
     *             player.sendMessage(text.getText());
     *
     *             Example method of calling and using rainbow text
     */

    private static final List<String> RAINBOW = Arrays.asList(new String[] {
            "§4", "§c", "§6", "§e", "§a",
            "§2", "§b", "§3", "§9", "§1", "§5", "§d"});

    private List<String> rainbowArray = null;

    private String prefix = "";

    public RainbowText(String text) {
        this.place = 0;
        if (text != null)
            this.text = text;
        if (this.rainbowArray == null)
            this.rainbowArray = RAINBOW;
        updateFancy();
    }

    public RainbowText(String text, String formatCode) {
        this.place = 0;
        if (text != null)
            this.text = text;
        if (formatCode != null)
            this.prefix = formatCode;
        if (this.rainbowArray == null)
            this.rainbowArray = RAINBOW;
        updateFancy();
    }

    public RainbowText(String text, List<String> rainbowArray) {
        this.place = 0;
        if (text != null)
            this.text = text;
        if (this.rainbowArray == null)
            this.rainbowArray = rainbowArray;
        updateFancy();
    }

    private void updateFancy() {
        int spot = this.place;
        String fancyText = "";
        byte b;
        int i;
        char[] arrayOfChar;
        for (i = (arrayOfChar = this.text.toCharArray()).length, b = 0; b < i; ) {
            char l = arrayOfChar[b];
            String letter = Character.toString(l);
            String t1 = fancyText;
            if (!letter.equalsIgnoreCase(" ")) {
                fancyText = String.valueOf(t1) + (String)this.rainbowArray.get(spot) + this.prefix + letter;
                if (spot == this.rainbowArray.size() - 1) {
                    spot = 0;
                } else {
                    spot++;
                }
            } else {
                fancyText = String.valueOf(t1) + letter;
            }
            b++;
        }
        this.fancyText = fancyText;
    }

    public void moveRainbow() {
        if (this.rainbowArray.size() - 1 == this.place) {
            this.place = 0;
        } else {
            this.place++;
        }
        updateFancy();
    }

    public void moveRainbowRight() {
        if (this.place == 0) {
            this.place = this.rainbowArray.size() - 1;
        } else {
            this.place--;
        }
        updateFancy();
    }

    public String getOrigonalText() {
        return this.text;
    }

    public String getText() {
        return this.fancyText;
    }

    public void setPlace(int place) {
        if (place > RAINBOW.size() - 1 || place < 0)
            return;
        this.place = place;
        updateFancy();
    }

    public int getPlace() {
        return this.place;
    }

    public List<String> getRainbow() {
        return this.rainbowArray;
    }

    public String getFormatPrefix() {
        return this.prefix;
    }

    public void setFormatPrefix(String prefix) {
        this.prefix = prefix;
    }

    public static List<String> getDefaultRainbow() {
        return RAINBOW;
    }
}