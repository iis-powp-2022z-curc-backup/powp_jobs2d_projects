package edu.kis.powp.jobs2d.command.imports;

public enum FileExtensions {
    txt("txt"),
    json("json");

    private final String extension;

    FileExtensions(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return this.extension;
    }
}
