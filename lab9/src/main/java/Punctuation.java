public class Punctuation implements Lexeme {
    private final Symbol symbol;

    public Punctuation(char c) {
        this.symbol = new Symbol(c);
    }

    @Override
    public String getText() {
        return String.valueOf(symbol.getValue());
    }
}