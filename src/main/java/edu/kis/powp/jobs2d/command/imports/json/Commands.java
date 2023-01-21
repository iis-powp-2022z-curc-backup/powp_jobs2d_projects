package edu.kis.powp.jobs2d.command.imports.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Commands {
    @JsonProperty("commands")
    private List<Command> commands = null;

    public List<Command> getCommands() {
        return this.commands;
    }
}
