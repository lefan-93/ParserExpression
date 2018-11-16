package exception;

public class ParserException extends Exception {
    private int line;
    private int pos;

    public ParserException(String message, int line, int pos) {
        super(message);
        this.line = line;
        this.pos = pos;
    }

    public ParserException(String message, int line) {
        this(message, line, -1);
    }

    public ParserException(String message) {
        this(message, -1, -1);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
        this.line = -1;
        this.pos = -1;

    }

    public ParserException(Throwable cause) {
        super(cause);
        this.line = -1;
        this.pos = -1;

    }

    public ParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.line = -1;
        this.pos = -1;

    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (line != -1)
            message = message + " in line " + Integer.toString(line);
        if (pos != -1)
            message = message + " (pos: " + Integer.toString(pos) + ")";
        return message;
    }
}
