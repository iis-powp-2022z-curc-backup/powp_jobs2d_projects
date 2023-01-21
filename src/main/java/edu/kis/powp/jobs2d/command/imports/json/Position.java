package edu.kis.powp.jobs2d.command.imports.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Position {
    @JsonProperty("x")
    public Integer x = 0;
    @JsonProperty("y")
    public Integer y = 0;

    @Override
    public String toString() {
        return String.format("(x: %s, y: %s)", x, y);
    }
}
