public class Decoder {
    public String decodeBencode(String bencodedString) {
        if (bencodedString == null) {
            throw new NullPointerException("bencodedString must not be null");
        }

        var firstCharacter = bencodedString.charAt(0);
        if (firstCharacter >= '1' && firstCharacter <= '9') {
            return this.decodeBencodedString(bencodedString);
        }
        return switch (firstCharacter) {
            case 'i' -> "Integer";
            case 'l' -> "Lists";
            case 'd' -> "Dictionaries";
            default -> throw new RuntimeException("Invalid string format");
        };
    }

    private String decodeBencodedString(String bencodedString) {
        int firstColonIndex = bencodedString.indexOf(':');
        return bencodedString.substring(firstColonIndex + 1);
    }
}
