public class Decoder {
    private enum BencodedStringType {
        STRING,
        INTEGER,
        LIST,
        DICTIONARY,
    }

    public String decodeBencode(String bencodedString) {
        if (bencodedString == null) {
            throw new NullPointerException("bencodedString must not be null");
        }

        BencodedStringType type = this.getBencodedType(bencodedString);
        return switch (type) {
            case BencodedStringType.STRING -> this.decodeBencodedString(bencodedString);
            case BencodedStringType.INTEGER -> "INTEGER";
            case BencodedStringType.LIST  -> "LIST";
            case BencodedStringType.DICTIONARY -> "DICTIONARY";
        };
    }

    private String decodeBencodedString(String bencodedString) {
        int firstColonIndex = bencodedString.indexOf(':');
        return bencodedString.substring(firstColonIndex + 1);
    }

    private BencodedStringType getBencodedType(String bencodedString) {
        var firstCharacter = bencodedString.charAt(0);
        var lastCharacter = bencodedString.charAt(bencodedString.length() - 1);

        if (firstCharacter >= '1' && firstCharacter <= '9') {
            return BencodedStringType.STRING;
        }
        if (firstCharacter == 'i' && lastCharacter == 'e') {
            return BencodedStringType.INTEGER;
        }
        if (firstCharacter == 'l' && lastCharacter == 'e') {
            return BencodedStringType.LIST;
        }
        if (firstCharacter == 'd' && lastCharacter == 'e') {
            return BencodedStringType.DICTIONARY;
        }
        throw new RuntimeException("Invalid string format");
    }
}
