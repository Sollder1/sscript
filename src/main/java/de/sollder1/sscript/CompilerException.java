package de.sollder1.sscript;

public abstract class CompilerException extends RuntimeException {

    protected final transient long line;
    protected final transient long pos;
    protected final transient String issue;

    public CompilerException(long line, long pos, String issue) {
        this.line = line;
        this.pos = pos;
        this.issue = issue;
    }

    public CompilerException(Throwable cause, long line, long pos, String issue) {
        super(cause);
        this.line = line;
        this.pos = pos;
        this.issue = issue;
    }

    public long getLine() {
        return line;
    }

    public long getPos() {
        return pos;
    }

    public String getIssue() {
        return issue;
    }



}
