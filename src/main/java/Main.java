import com.google.gson.Gson;

public class Main {
    private static final Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        System.err.println("Logs from your program will appear here!");
        var decoder = new Decoder();

        String command = args[0];
        if("decode".equals(command)) {
            String bencodedValue = args[1];
            Decoder.DecodedValue decoded;
            try {
                decoded = decoder.decodeBencode(bencodedValue);
            } catch(RuntimeException e) {
                System.out.println(e.getMessage());
                return;
            }

            if (decoded instanceof Decoder.DecodedInteger decodedInteger) {
                System.out.println(decodedInteger.value());
            } else if (decoded instanceof Decoder.DecodedString decodedString) {
                System.out.println(gson.toJson(decodedString.value()));
            }
        } else {
            System.out.println("Unknown command: " + command);
        }
    }
}
