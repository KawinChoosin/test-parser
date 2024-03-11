
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

class ExprTokenizer implements Tokenizer {

    private String src, next;  private int pos;
    public ExprTokenizer(String src) {
        this.src = src;  pos = 0;
        computeNext();
    }

    public boolean hasNextToken()
    { return next != null; }
    public void checkNextToken() {
        if (!hasNextToken()) throw new NoSuchElementException("no more tokens");
    }
    public String peek() {
        checkNextToken();
        return next;
    }
    public boolean peek(String s) {
        if (!hasNextToken()) return false;
        return peek().equals(s);
    }

    public String consume() {
        checkNextToken();
        String result = next;
        computeNext();
        return result;
    }
    public void consume(String s) throws Exception {
        if (peek(s))
            consume();
        else
            throw new Exception(s + " expected");
    }


    private boolean isSpace(char c) {
        return Character.isWhitespace(c);
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private boolean isCharacter(char c) {
        return Character.isLetter(c);
    }

    // Placeholder LexicalError class
    private static class LexicalError extends RuntimeException {
        public LexicalError(String message) {
            super(message);
        }
    }
    //2 + 3
    private void computeNext() {
        StringBuilder s = new StringBuilder();
        while (pos < src.length() && isSpace(src.charAt(pos))) {
            pos++;  // ignore whitespace
        }
            if (pos == src.length()) {
                next = null;
                return;
            }  // no more tokens

            char c = src.charAt(pos);
            if (isDigit(c)) {  // start of number
                s.append(c);
                for (pos++; pos < src.length() && isDigit(src.charAt(pos)); pos++)
                    s.append(src.charAt(pos));
            } else if (isCharacter(c)) {  // start of string
                s.append(c);
                for (pos++; pos < src.length() && isCharacter(src.charAt(pos)); pos++)
                    s.append(src.charAt(pos));
            } else if (c == '+' || c == '{' || c== '}'|| c == '(' || c == ')' || c == '-' || c == '*' || c == '/' || c == '%' || c == '=') {
                s.append(c);
                pos++;
            } else throw new LexicalError("unknown character: " + c);
            next = s.toString();
    }



}
