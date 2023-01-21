package edu.kis.powp.jobs2d.command.imports.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Command {
    @JsonProperty("type")
    public CommandType type = null;
    @JsonProperty("position")
    public Position position = null;

    @Override
    public String toString() {
        return String.format("command: %s, position: %s", type, position);
    }
}
