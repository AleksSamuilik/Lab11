package it.alex.lab11;

abstract class AbstractMethods {
    //Если число
    public boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String[] createInputArray(String string) {
        string = string.replaceAll("\\s", "");
        String operators = "(\\(|\\)|\\+|-|\\*|/)";
        return string.split(String.format("(?<=%s)|(?=%s)", operators, operators));
    }
}