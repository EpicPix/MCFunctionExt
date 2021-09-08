package ga.epicpix.mcfext.nbt;

import ga.epicpix.mcfext.command.CommandStringIterator;

import java.util.ArrayList;

public class NBTSelector {

    private final NBTSelectorElement[] elements;

    public NBTSelector(NBTSelectorElement... elements) {
        this.elements = elements;
    }

    private static NBTSelectorElement toSelectorElement(String el) {
        CommandStringIterator iter = new CommandStringIterator(el);
        StringBuilder name = new StringBuilder();
        StringBuilder indexs = new StringBuilder();
        boolean indexb = false;
        while(iter.hasNext()) {
            char c = iter.nextChar();
            if(c == '[') {
                indexb = true;
            }else if(c == ']') {
                if(indexb) {
                    break;
                }
            }else {
                if(indexb) {
                    indexs.append(c);
                }else {
                    name.append(c);
                }
            }
        }
        return new NBTSelectorElement(name.toString(), (indexs.length() == 0) ? null : Integer.parseInt(indexs.toString()));
    }

    public static NBTSelector nextSelector(CommandStringIterator iter) {
        iter.removeNextWhitespace();
        boolean inString = false;
        StringBuilder str = new StringBuilder();
        ArrayList<NBTSelectorElement> els = new ArrayList<>();
        while(iter.hasNext()) {
            char next = iter.nextChar();
            if(next == '\"') {
                inString = !inString;
            }
            if(!inString && next == ' ') {
                break;
            }
            if(!inString && next == '.') {
                els.add(toSelectorElement(str.toString()));
                str = new StringBuilder();
                continue;
            }
            str.append(next);
        }
        if(str.length() > 0) {
            els.add(toSelectorElement(str.toString()));
        }
        return new NBTSelector(els.toArray(new NBTSelectorElement[0]));
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(NBTSelectorElement element : elements) {
            str.append(".").append(element.toString());
        }
        return str.substring(1);
    }
}
